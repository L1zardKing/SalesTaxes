import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receipt {

    private final static String INPUT_PATTERN = "^([0-9])+[ ](.*)$";

    private List<Good> goodsList = new ArrayList<>();
    private BigDecimal totalAmount = new BigDecimal("0.00");
    private BigDecimal salesTaxesAmount = new BigDecimal("0.00");

    public void addEntryToReceipt(String goods) throws Exception{
        Pattern p = Pattern.compile(INPUT_PATTERN);

        Integer pos = findPriceStartingPos (goods);
        String priceStripped = goods.substring(0, pos);
        BigDecimal price = new BigDecimal(goods.substring(pos));

        Matcher m = p.matcher(priceStripped);

        if (m.find()) {
            addGood(m, price);
        } else {
            throw new WrongFormatException("Input string format is wrong");
        }
    }

    private Integer findPriceStartingPos(String goods) {
        Integer pos = 0;
        for (int i = goods.length()-1; i>0; i--) {
            if (Character.isDigit(goods.charAt(i)) || goods.charAt(i)=='.'){
                pos = i;
            } else {
                return pos;
            }
        }
        return pos;
    }

    private void addGood(Matcher m, BigDecimal price) {
        BigDecimal quantity = new BigDecimal(m.group(1));
        String name = m.group(2);
        Good newGood = new Good(quantity, name, price);
        newGood.checkTaxFares();
        totalAmount = totalAmount.add(newGood.getTotalPrice());
        salesTaxesAmount = salesTaxesAmount.add(newGood.getTotalTaxAmount());
        goodsList.add (newGood);
    }

    String printReceipt () {
        String receipt = "";
        StringBuilder stringBuffer = new StringBuilder(receipt);

        for (Good good : goodsList) {
            stringBuffer.append(good.getQuantity())
            .append(" ")
            .append(good.getName())
            .append(": ")
            .append(good.getTotalPrice())
            .append("\n");
        }

        stringBuffer.append("Sales Taxes: ").append(salesTaxesAmount).append("\n");
        stringBuffer.append("Total: ").append(totalAmount);
        receipt = stringBuffer.toString();

        System.out.println(receipt);

        return receipt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getSalesTaxesAmount() {
        return salesTaxesAmount;
    }
}
