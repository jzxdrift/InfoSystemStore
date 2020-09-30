//this class contains public static constants and methods

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class HelperClass
{
    public static final String CONFIRM_MESSAGE = "Are you sure?",
            CONFIRM_TITLE = "Confirm log off", CLOSE_TITLE = "Confirm exit",
            BACK_HOME_TITLE = "Back to home page";
    public static final String PHONE_FORMAT = "(%s) %s-%s";
    
    public static final DateFormat GENERAL_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public static final DateFormat INVENTORY_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm:ssa");
    
    public static final DecimalFormat CURRENCY = new DecimalFormat("###,###.00");
    public static final DecimalFormat DISCOUNT = new DecimalFormat("#.##");
    
    public static final String HOME_DIRECTORY = "user.home";
    
    public static String checkPhoneLength(String input)
    {
        while(input.length() != 10)
        {
            JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog(null, "Enter 10-digit phone number",
                    "Add phone", JOptionPane.PLAIN_MESSAGE);
            if(input == null || "".equals(input))
                return null;
        }
        return input;
    }
    
    public static String formatTax(String value)
    {
        return CURRENCY.format(Double.parseDouble(value.replace(",", ""))); 
    }
}