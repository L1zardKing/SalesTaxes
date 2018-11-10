import org.junit.Assert;
import org.junit.Test;

public class testTest {


    @Test
    public void firstTest() throws  Exception {
        Receipt salesTaxes = new Receipt();

        String expectedFirstOutput = "2 book: 24.98\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 42.32";
        salesTaxes.addEntryToReceipt("2 book at 12.49");
        salesTaxes.addEntryToReceipt("1 music CD at 14.99");
        salesTaxes.addEntryToReceipt("1 chocolate bar at 0.85");

        String receipt = salesTaxes.printReceipt();
        Assert.assertEquals("First output", receipt, expectedFirstOutput);

    }


    @Test
    public void secondTest() throws  Exception {
        Receipt salesTaxes = new Receipt();

        String expectedFirstOutput = "1 imported box of chocolates: 10.50\n" +
                "1 imported bottle of perfume: 54.65\n" +
                "Sales Taxes: 7.65\n" +
                "Total: 65.15";

        salesTaxes.addEntryToReceipt("1 imported box of chocolates at 10.00");
        salesTaxes.addEntryToReceipt("1 imported bottle of perfume at 47.50");

        String receipt = salesTaxes.printReceipt();
        Assert.assertEquals("Second output", receipt, expectedFirstOutput);

    }


    @Test
    public void thirdTest() throws  Exception {
        Receipt salesTaxes = new Receipt();

        String expectedFirstOutput = "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "3 imported box of chocolates: 35.55\n" +
                "Sales Taxes: 7.90\n" +
                "Total: 98.38";

        salesTaxes.addEntryToReceipt("1 imported bottle of perfume at 27.99\n");
        salesTaxes.addEntryToReceipt("1 bottle of perfume at 18.99\n");
        salesTaxes.addEntryToReceipt("1 packet of headache pills at 9.75\n");
        salesTaxes.addEntryToReceipt("3 box of imported chocolates at 11.25\n");

        String receipt = salesTaxes.printReceipt();
        Assert.assertEquals("Third output", receipt, expectedFirstOutput);

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
}