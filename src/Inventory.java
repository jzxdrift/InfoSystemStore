import java.util.Date;

public class Inventory
{
    private final int ID;
    
    private int total, inStock;
    private double price;
    private String name;
    private String upgradeDate;
    
    public Inventory(int id)
    {
        this.ID = id;   //auto-generated
        
        upgradeDate();
    }
    
    public int getId()
    {
        return this.ID;
    }
    
    public int getTotal()
    {
        return this.total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public int getInStock()
    {
        return this.inStock;
    }
    
    public void setInStock(int inStock)
    {
        this.inStock = inStock;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getUpgradeDate()
    {
        return this.upgradeDate;
    }
    
    public void setUpgradeDate(String upgradeDate)
    {
        this.upgradeDate = upgradeDate;
    }
    
    //current date and time of upgrading
    public final void upgradeDate()
    {
        this.upgradeDate = HelperClass.INVENTORY_DATE_FORMAT.format(new Date());
    }
}