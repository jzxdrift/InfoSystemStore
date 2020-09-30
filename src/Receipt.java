import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class Receipt implements Printable
{
    private static final Font TITLE_FONT = new Font("Calibri", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("Calibri", Font.PLAIN, 12);
    private static final Font BOLD_FONT = new Font("Calibri", Font.BOLD, 12);
    
    private final List<Object> DATA;
    
    public Receipt(List<Object> data)
    {
        this.DATA = new ArrayList<>(data);
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException
    {
        if(pageIndex > 0)
            return NO_SUCH_PAGE;
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        
        //title
        graphics.setFont(TITLE_FONT);
        graphics.drawString("FCS", 50, 50);
        graphics.drawString("Invoice", 450, 50);
        
        //company's information
        graphics.setFont(NORMAL_FONT);
        graphics.drawString("1024 Victoria St", 50, 80);
        graphics.drawString("New Westminster, BC A2B 3C5", 50, 100);
        graphics.drawString("Future Communication Store", 50, 120);
        graphics.drawString("Email:", 50, 140);
        
        //formatting email
        AttributedString email = new AttributedString("fcs@fcs.com");
        email.addAttribute(TextAttribute.FONT, NORMAL_FONT);
        email.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, 11);
        graphics.setColor(Color.BLUE);
        graphics.drawString(email.getIterator(), 83, 140);
        
        graphics.setColor(Color.BLACK);
        graphics.drawString("Tel: " + String.format(HelperClass.PHONE_FORMAT, 604, 123, 9876), 50, 160);
        //end of company's information
        
        //transaction details
        graphics.setFont(BOLD_FONT);
        graphics.drawString("Invoice Number: #", 50, 200);
        graphics.setFont(NORMAL_FONT);
        graphics.drawString(String.valueOf(DATA.get(0)), 142, 200); //transaction ID
        
        graphics.setFont(BOLD_FONT);
        graphics.drawString("Order Date:", 50, 220);
        graphics.setFont(NORMAL_FONT);
        graphics.drawString((String)DATA.get(6), 110, 220); //transaction date
        
        graphics.setFont(BOLD_FONT);
        graphics.drawString("Payment Method:", 50, 240);
        graphics.setFont(NORMAL_FONT);
        graphics.drawString("Credit Card", 142, 240);
        
        //customer's details
        graphics.setFont(BOLD_FONT);
        graphics.drawString("Bill To:", 50, 280);
        graphics.setFont(NORMAL_FONT);
        graphics.drawString((String)DATA.get(7), 87, 280);  //customer's full name
        
        String phone = (String)DATA.get(8); //customer's phone
        graphics.drawString("Tel: " + String.format(HelperClass.PHONE_FORMAT,
                phone.substring(0, 3),
                phone.substring(3, 6),
                phone.substring(6, 10)), 87, 300);
        
        //inventory details
        graphics.drawString("Item #", 50, 340);
        graphics.drawString("Description", 110, 340);
        graphics.drawString("Quantity", 250, 340);
        graphics.drawString("Unit Price", 330, 340);
        graphics.drawString("Total", 450, 340);
        graphics.drawLine(50, 345, 500, 345);
        graphics.drawString(String.valueOf(DATA.get(1)), 50, 360);  //inventory ID
        graphics.drawString((String)DATA.get(9), 110, 360); //item name
        graphics.drawString(String.valueOf(DATA.get(2)), 250, 360); //amount
        graphics.drawString("$" + (String)DATA.get(3), 330, 360);   //price
        graphics.drawString("$" + (String)DATA.get(4), 450, 360);   //total
        graphics.drawLine(50, 365, 500, 365);
        
        //taxes
        graphics.drawString("Sub Total:", 330, 400);
        graphics.drawString("$" + (String)DATA.get(4), 450, 400); //same as total
        graphics.drawString("GST:", 330, 420);
        graphics.drawString("$" + HelperClass.formatTax((String)DATA.get(10)), 450, 420);  //transaction GST
        graphics.drawString("PST:", 330, 440);
        
        graphics.drawString("$" + HelperClass.formatTax((String)DATA.get(11)), 450, 440);  //transaction PST
        graphics.drawString("Total:", 330, 460);
        graphics.drawString("$" + (String)DATA.get(5), 450, 460);   //total after tax
        
        //page bottom
        graphics.drawString("Thanks for your Business", (int)pageFormat.getImageableWidth() / 2 - 75,
                (int)pageFormat.getImageableHeight() - 90);
        graphics.drawString("If you have any questions, please do not hesitate to contact us",
                (int)pageFormat.getImageableWidth() / 2 - 165, (int)pageFormat.getImageableHeight() - 70);
        graphics.drawString(String.format(HelperClass.PHONE_FORMAT, 604, 123, 9876),
                (int)pageFormat.getImageableWidth() / 2 - 50, (int)pageFormat.getImageableHeight() - 50);
        
        return PAGE_EXISTS;
    }
}