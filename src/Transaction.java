import java.util.Date;

public class Transaction
{
    private static final double GST = 0.05, PST = 0.07;
    
    private final int ID, SALES_ID;
    
    private String date;
    private int inventoryId, customerId, amount;
    private double price, discount;

    public Transaction(int id, int salesId)
    {
        this.ID = id;   //auto-generated
        this.SALES_ID = salesId;    //whoever uses system at the moment
        
        //current date of adding
        this.date = HelperClass.GENERAL_DATE_FORMAT.format(new Date());
    }
    
    public int getId()
    {
        return this.ID;
    }
    
    public int getSalesId()
    {
        return this.SALES_ID;
    }
    
    public String getDate()
    {
        return this.date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public int getInventoryId()
    {
        return this.inventoryId;
    }
    
    public void setInventoryId(int inventoryId)
    {
        this.inventoryId = inventoryId;
    }
    
    public int getCustomerId()
    {
        return this.customerId;
    }
    
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }
    
    public int getAmount()
    {
        return this.amount;
    }
    
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getDiscount()
    {
        return this.discount;
    }
    
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
    
    public double getTotalPrice()
    {
        double total = this.price * this.amount;
        return total - total * this.discount;
    }
    
    public double getGST()
    {
        return this.getTotalPrice() * GST;
    }
    
    public double getPST()
    {
        return this.getTotalPrice() * PST;
    }
    
    public double getTotalAfterTax()
    {
        return this.getTotalPrice() + this.getTotalPrice() * (GST + PST);
    }
}