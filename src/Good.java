import java.math.BigDecimal;
import java.math.RoundingMode;

public class Good {

    private BigDecimal quantity;
    private String name;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private BigDecimal taxAmount;
    private BigDecimal totalTaxAmount;

    public Good(BigDecimal quantity, String name, BigDecimal price) {
        name = computeName (name);
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    private String computeName(String name) {
        name = name.replaceAll(" at $", "");
        if (name.contains("imported") && name.indexOf("imported") != 0) {
            name = name.replaceAll("(.*)(imported )(.*)", "$2$1$3");
        }
        return name;
    }

    void checkTaxFares() {

        BigDecimal taxes = new BigDecimal("0.00");

        if (isExempted(name) && isImported(name)) {
            taxes = new BigDecimal("5.00");
        }
        if (!isExempted(name) && isImported(name)) {
            taxes = new BigDecimal("15.00");
        }
        if (!isExempted(name) && !isImported(name)) {
            taxes = new BigDecimal("10.00");
        }
        if (isExempted(name) && !isImported(name)) {
            totalTaxAmount = new BigDecimal("0.00");
            totalPrice = price.multiply(quantity);
            return;
        }

        setNewPrice(taxes);

    }

    private boolean isExempted(String name) {
        return (name.contains("book") || name.contains("pills") || name.contains("chocolate"));
    }

    private void setNewPrice (BigDecimal taxPercentage) {
        BigDecimal taxImport = price.divide(new BigDecimal("100"), 5, RoundingMode.UP).multiply(taxPercentage);
        BigDecimal divided = taxImport.divide(new BigDecimal("0.05"), 0, RoundingMode.UP);
        taxImport = divided.multiply(new BigDecimal("0.05"));
        BigDecimal newPrice = price.add(taxImport);
        price = newPrice;
        totalPrice = newPrice.multiply(quantity);
        taxAmount = taxImport;
        totalTaxAmount = taxAmount.multiply(quantity);
    }

    private boolean isImported(String name) {
        return name.contains("imported");
    }

    public BigDecimal getQuantity() {
        return quantity;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public BigDecimal getTaxAmount() {
        return taxAmount;
    }


    public BigDecimal getTotalPrice () {
        return totalPrice;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }


}
