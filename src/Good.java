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
        name = name.replaceAll(" at $", "");
        if (name.contains("imported") && name.indexOf("imported") != 0) {
            name = name.replaceAll("(.*)(imported )(.*)", "$2$1$3");
        }
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    // to improve
    public void checkTaxFares() {
        BigDecimal taxes = new BigDecimal("15.00");

        if (isExempted(name)) {
            taxes = taxes.subtract(new BigDecimal("10.00"));
        }
        if (isImported(name)) {
            setNewPrice(taxes); // to check
        } else {
            taxes = taxes.subtract(new BigDecimal("5.00"));
            if (taxes.equals(new BigDecimal("0.00"))) {
                totalTaxAmount = new BigDecimal("0.00");
                totalPrice = price.multiply(quantity);
            }
            else {
                setNewPrice(new BigDecimal("10"));
            }
        }
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

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice () {
        return totalPrice;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

}
