import java.util.Date;

public class Customer
{
    private final int ID, SALES_ID;
    
    private String firstName, lastName, phone, customerSince, content, type;

    public Customer(int id, int salesId)
    {
        this.ID = id;   //auto-generated
        this.SALES_ID = salesId;    //whoever uses system at the moment
        
        //current date of adding 
        this.customerSince = HelperClass.GENERAL_DATE_FORMAT.format(new Date());
    }
    
    public int getId()
    {
        return this.ID;
    }
    
    public int getSalesId()
    {
        return this.SALES_ID;
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
    
    public String getCustomerSince()
    {
        return this.customerSince;
    }
    
    public void setCustomerSince(String customerSince)
    {
        this.customerSince = customerSince;
    }
    
    public String getContent()
    {
        return this.content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
}