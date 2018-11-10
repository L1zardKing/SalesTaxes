import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Receipt {

    private List<Good> goodsList = new ArrayList<>();
    private BigDecimal totalAmount = new BigDecimal("0.00");
    private BigDecimal salesTaxesAmount = new BigDecimal("0.00");

    public void addEntryToReceipt(String goods) throws Exception{
        Pattern p = Pattern.compile("([0-9])+[ ]([A-z, ]+)([0-9,.]+)");
        Matcher m = p.matcher(goods);
        if (m.find()) {
            addGood(m);
        } else {
            throw new Exception("Input string format is wrong");
        }
    }

    private void addGood(Matcher m) {
        BigDecimal quantity = new BigDecimal(m.group(1));
        BigDecimal price = new BigDecimal(m.group(3));
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
        return receipt;
    }


}
