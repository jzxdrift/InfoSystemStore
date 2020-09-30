import java.util.Date;

public class SalesPerson
{
    private final int ID;
    private final String PASSWORD;
    
    private int totalSales;
    private String firstName, lastName, phone, staffSince;
    
    public SalesPerson(int id, String position)
    {
        this.ID = id;   //auto-generated
        this.PASSWORD = position; //manager or stuff
        
        this.totalSales = 0;    //new staff member has no sales
        //current date of adding
        this.staffSince = HelperClass.GENERAL_DATE_FORMAT.format(new Date());
    }
    
    public int getId()
    {
        return this.ID;
    }
    
    public String getPassword()
    {
        return this.PASSWORD;
    }
    
    public int getTotalSales()
    {
        return this.totalSales;
    }
    
    public void setTotalSales(int totalSales)
    {
        this.totalSales = totalSales;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public String getPhone()
    {
        return this.phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getStaffSince()
    {
        return this.staffSince;
    }
    
    public void setStaffSince(String staffSince)
    {
        this.staffSince = staffSince;
    }
}