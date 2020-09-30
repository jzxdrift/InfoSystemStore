import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.AbstractTableModel;

public class TransactionInfoForm extends javax.swing.JFrame implements ReadWriteFile
{
    private static final File FILE = new File(System.getProperty(HelperClass.HOME_DIRECTORY), "transactions.txt");
    private static final String ADD_TITLE = "Add transaction";
    
    private final String[] columns = {"ID", "Inventory ID", "Sales ID",
        "Customer ID", "Date", "Price ($)", "Amount", "Discount (%)",
        "Total Price ($)", "Total after Tax ($)"};
    private final TransactionTableModel model;
    
    private static List<Transaction> transactions;
    private int transactionId;
    
    private final String CURRENT_USER;
    private final int SALES_ID;
    
    private ManagerHomeForm managerForm;
    private StaffHomeForm staffForm;
    
    private BufferedReader inF;
    private BufferedWriter outF;
    
    private class TransactionTableModel extends AbstractTableModel
    {
        @Override
        public int getRowCount()
        {
            return transactions.size();
        }

        @Override
        public int getColumnCount()
        {
            return 10;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            Transaction transaction = transactions.get(rowIndex);
            switch(columnIndex)
            {
                case 0:
                    return transaction.getId();
                case 1:
                    return transaction.getInventoryId();
                case 2:
                    return transaction.getSalesId();
                case 3:
                    return transaction.getCustomerId();
                case 4:
                    return transaction.getDate();
                case 5:
                    return HelperClass.CURRENCY.format(transaction.getPrice());
                case 6:
                    return transaction.getAmount();
                case 7:
                    return HelperClass.DISCOUNT.format(transaction.getDiscount() * 100);
                case 8:
                    return HelperClass.CURRENCY.format(transaction.getTotalPrice());
                case 9:
                    return HelperClass.CURRENCY.format(transaction.getTotalAfterTax());
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column)
        {
            return columns[column];
        }
    }
    
    public TransactionInfoForm(String user, int id, Object obj)
            throws IOException
    {
        CURRENT_USER = user;   //manager or staff
        SALES_ID = id;   //whoever uses system at the moment
        
        //checking which form opened this form
        if(CURRENT_USER.equals("manager"))
            managerForm = (ManagerHomeForm)obj;
        else if(CURRENT_USER.equals("staff"))
            staffForm = (StaffHomeForm)obj;
        
        model = new TransactionTableModel();
        transactions = new ArrayList<>();
        
        readFile();
        
        initComponents();
    }
    
    public static List<Transaction> getTransactionsList()
    {
        return transactions;
    }
    
    @Override
    public final void readFile()
            throws IOException
    {
        if(FILE.exists())
        {
            inF = new BufferedReader(new FileReader(FILE));
            String id = inF.readLine();
            
            while(id != null && !id.isEmpty())
            {
                Transaction transaction = new Transaction(Integer.parseInt(id),
                        Integer.parseInt(inF.readLine()));
                transaction.setDate(inF.readLine());
                transaction.setInventoryId(Integer.parseInt(inF.readLine()));
                transaction.setCustomerId(Integer.parseInt(inF.readLine()));
                transaction.setAmount(Integer.parseInt(inF.readLine()));
                transaction.setPrice(Double.parseDouble(inF.readLine()));
                transaction.setDiscount(Double.parseDouble(inF.readLine()));
                transactions.add(transaction);
                
                inF.readLine();
                
                id = inF.readLine();
            }
            inF.close();
        }
    }
    
    @Override
    public void writeFile()
            throws IOException
    {
        outF = new BufferedWriter(new FileWriter(FILE));
        
        for(Transaction transaction : transactions)
        {
            outF.write(Integer.toString(transaction.getId()));
            outF.newLine();
            outF.write(Integer.toString(transaction.getSalesId()));
            outF.newLine();
            outF.write(transaction.getDate());
            outF.newLine();
            outF.write(Integer.toString(transaction.getInventoryId()));
            outF.newLine();
            outF.write(Integer.toString(transaction.getCustomerId()));
            outF.newLine();
            outF.write(Integer.toString(transaction.getAmount()));
            outF.newLine();
            outF.write(Double.toString(transaction.getPrice()));
            outF.newLine();
            outF.write(Double.toString(transaction.getDiscount()));
            
            //separating records with blank line
            outF.newLine();
            outF.newLine();
        }
        outF.close();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        transactionScrollPane = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField()
        {
            public Point getToolTipLocation(MouseEvent e)
            {
                return new Point(0, -30);
            }
        }
        ;
        searchButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        transactionLbl = new javax.swing.JLabel();
        logOffButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Transaction Information");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        transactionTable.setModel(model);
        transactionTable.getTableHeader().setReorderingAllowed(false);
        transactionScrollPane.setViewportView(transactionTable);
        transactionTable.setAutoCreateRowSorter(true);
        transactionTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        transactionTable.getColumnModel().getColumn(1).setPreferredWidth(45);
        transactionTable.getColumnModel().getColumn(2).setPreferredWidth(35);
        transactionTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        transactionTable.getColumnModel().getColumn(4).setPreferredWidth(70);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteButtonActionPerformed(evt);
            }
        });

        searchField.setToolTipText("Enter phone");
        searchField.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                searchFieldKeyPressed(evt);
            }
        });
        searchField.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent e)
                {
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                }
            });

            searchButton.setText("Search");
            searchButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    searchButtonActionPerformed(evt);
                }
            });

            homeButton.setText("Home");
            homeButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    homeButtonActionPerformed(evt);
                }
            });

            transactionLbl.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
            transactionLbl.setText("Transaction Information");

            logOffButton.setText("Log Off");
            logOffButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    logOffButtonActionPerformed(evt);
                }
            });

            printButton.setText("Print");
            printButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    printButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(searchField)
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                            .addComponent(transactionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(logOffButton)))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGap(414, 414, 414)
                    .addComponent(transactionLbl)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(logOffButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(transactionLbl)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(transactionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(deleteButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(printButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(searchButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(homeButton)))
                    .addContainerGap())
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void logOffButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_logOffButtonActionPerformed
    {//GEN-HEADEREND:event_logOffButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, HelperClass.CONFIRM_MESSAGE,
                HelperClass.CONFIRM_TITLE, JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)
        {
            this.dispose();
            
            //checking which home form was opened
            if(CURRENT_USER.equals("manager"))
                managerForm.dispose();
            else if(CURRENT_USER.equals("staff"))
                staffForm.dispose();
            
            try
            {
                writeFile();
                new LogInForm().setVisible(true);
            }
            catch(IOException ex) {}
        }
    }//GEN-LAST:event_logOffButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_homeButtonActionPerformed
    {//GEN-HEADEREND:event_homeButtonActionPerformed
        this.setVisible(false); //keeping alive to use form's resources
        try
        {
            writeFile();
        }
        catch(IOException ex) {}
        
        //checking which home form to open
        if(CURRENT_USER.equals("manager"))
            managerForm.setVisible(true);
        else if(CURRENT_USER.equals("staff"))
            staffForm.setVisible(true);
    }//GEN-LAST:event_homeButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        int option = JOptionPane.showConfirmDialog(this, HelperClass.CONFIRM_MESSAGE,
                HelperClass.BACK_HOME_TITLE, JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)
            homeButton.doClick();
    }//GEN-LAST:event_formWindowClosing

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addButtonActionPerformed
    {//GEN-HEADEREND:event_addButtonActionPerformed
        if(transactions.isEmpty())
            transactionId = 1;  //no transaction with ID of 0
        else
            transactionId = transactions.get(transactions.size() - 1).getId() + 1;
        
        //extracting item names to use in drop-down menu
        List<String> itemNames = new ArrayList<>();
        InventoryInfoForm.getInventoryList().stream().forEach((item) ->
        {
            itemNames.add(item.getName());
        });
        Collections.sort(itemNames);    //sorting in ascending order
        Object[] items = itemNames.toArray();
        
        //creating Transaction object and adding to list only if all fields are filled
        String input = (String)JOptionPane.showInputDialog(this, "Select item", ADD_TITLE,
                JOptionPane.PLAIN_MESSAGE, null, items, items[0]);
        if(input != null && !"".equals(input))
        {
            //obtaining inventory ID from inventory list
            int inventoryId = findInventoryId(input);
            if(inventoryId > 0)
            {
                input = JOptionPane.showInputDialog(this, "Enter customer's phone",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                if(input != null && !"".equals(input))
                {
                    //obtaining customer ID from customers list
                    int customerId = findCustomerId(input);
                    if(customerId > 0)
                    {
                        input = JOptionPane.showInputDialog(this, "Enter amount",
                                ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                        if(input != null && !"".equals(input))
                        {
                            //checking for valid input
                            int amount = checkAmount(input, inventoryId);
                            if(amount > 0)
                            {
                                input = JOptionPane.showInputDialog(this, "Enter discount",
                                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                                if(input != null && !"".equals(input))
                                {
                                    //checking for valid input
                                    double discount = checkDiscount(input);
                                    if(discount >= 0)
                                    {
                                        Transaction transaction = new Transaction(transactionId, SALES_ID);
                                        updateTotalSales(SALES_ID);
                                        transaction.setInventoryId(inventoryId);
                                        transaction.setCustomerId(customerId);
                                        transaction.setPrice(findPrice(inventoryId));
                                        transaction.setAmount(amount);
                                        updateInventoryStock(inventoryId, amount);
                                        transaction.setDiscount(discount);
                                        transactions.add(transaction);
                                        
                                        model.fireTableDataChanged();
                                        //auto-scrolling to added entry
                                        transactionTable.scrollRectToVisible(transactionTable.getCellRect
                                            (transactionTable.getRowCount() - 1, 0, true));
                                    }
                                }
                            }
                        }
                    }
                }
            }  
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchButtonActionPerformed
    {//GEN-HEADEREND:event_searchButtonActionPerformed
        boolean found = false;
        transactionTable.clearSelection();
        
        ListSelectionModel selectionModel = transactionTable.getSelectionModel();
        selectionModel.clearSelection();
        
        //searching by customer's phone number
        String phone = searchField.getText();
        int index = 0;
        for(Customer customer : CustomerInfoForm.getCustomersList())
        {
            if(customer.getPhone().equals(phone))
            {
                for(Transaction transaction : transactions)
                {
                    if(transaction.getCustomerId() == customer.getId())
                    {
                        index = transactions.indexOf(transaction);
                        selectionModel.addSelectionInterval(index, index);
                    }
                }
                
                found = true;
                break;
            }
        }
        
        if(!found && !phone.equals(""))
            JOptionPane.showMessageDialog(this, "Not found", "Error", JOptionPane.ERROR_MESSAGE);
        
        //auto-scrolling to last item found
        transactionTable.scrollRectToVisible(transactionTable.getCellRect(index, 0, true));
        
        searchField.setText(null);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteButtonActionPerformed
    {//GEN-HEADEREND:event_deleteButtonActionPerformed
        if(!transactions.isEmpty() && transactionTable.getSelectedRow() >= 0)
            transactions.remove(transactionTable.getSelectedRow());
        model.fireTableDataChanged();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_searchFieldKeyPressed
    {//GEN-HEADEREND:event_searchFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchButton.doClick();
    }//GEN-LAST:event_searchFieldKeyPressed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printButtonActionPerformed
    {//GEN-HEADEREND:event_printButtonActionPerformed
        if(!transactions.isEmpty() && transactionTable.getSelectedRow() >= 0)
        {
            //extracting data from selected row
            int row = transactionTable.getSelectedRow();
            List<Object> data = new ArrayList<>();
            data.add((int)transactionTable.getModel().getValueAt(row, 0));  //transaction ID
            data.add((int)transactionTable.getModel().getValueAt(row, 1));  //inventory ID
            data.add((int)transactionTable.getModel().getValueAt(row, 6));  //amount
            data.add((String)transactionTable.getModel().getValueAt(row, 5));   //price
            data.add((String)transactionTable.getModel().getValueAt(row, 8));   //total
            data.add((String)transactionTable.getModel().getValueAt(row, 9));   //total after tax
            data.add((String)transactionTable.getModel().getValueAt(row, 4));   //transaction date
            data.add(findCustomerFullName((int)transactionTable.getModel().getValueAt(row, 3)));    //customer's full name
            data.add(findCustomerPhone((int)transactionTable.getModel().getValueAt(row, 3)));   //customer's phone
            data.add(findInventoryName((int)data.get(1)));  //inventory name
            data.add(findGST((int)data.get(0))); //transaction GST
            data.add(findPST((int)data.get(0))); //transaction PST
            
            PrinterJob printJob = PrinterJob.getPrinterJob();
            if(printJob.printDialog())
            {
                try
                {
                    printJob.setPrintable(new Receipt(data));
                    printJob.print();
                }
                catch(PrinterException ex) {}
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Select transaction", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_printButtonActionPerformed
    
    private int findInventoryId(String input)
    {
        for(Inventory item : InventoryInfoForm.getInventoryList())
        {
            if(item.getName().equals(input))
                return item.getId();
        }
        return 0;
    }
    
    private int findCustomerId(String input)
    {
        while(input != null && !"".equals(input))
        {
            boolean found = false;
            for(Customer customer : CustomerInfoForm.getCustomersList())
            {
                if(customer.getPhone().equals(input))
                    return customer.getId();
            }
            
            //no match found
            if(!found)
            {
                JOptionPane.showMessageDialog(this, "Not found", "Error", JOptionPane.ERROR_MESSAGE);
                input = JOptionPane.showInputDialog(this, "Enter customer's phone",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
            }
        }
        return 0;
    }
    
    private int checkAmount(String input, int inventoryId)
    {
        while(input != null && !"".equals(input))
        {
            try
            {
                int amount = Integer.parseInt(input);
                
                for(Inventory item : InventoryInfoForm.getInventoryList())
                {
                    if(item.getId() == inventoryId)
                    {
                        if(item.getInStock() == 0)
                        {
                            JOptionPane.showMessageDialog(this, "No items left",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return 0;
                        }
                        else if(amount > item.getInStock())
                        {
                            JOptionPane.showMessageDialog(this, item.getInStock() + " items left",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            input = JOptionPane.showInputDialog(this, "Enter amount",
                                    ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                        else
                            return amount;
                    }
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                input = JOptionPane.showInputDialog(this, "Enter amount",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
            }
        }
        return 0;
    }
    
    private double checkDiscount(String input)
    {
        while(input != null && !"".equals(input))
        {
            try
            {
                return Double.parseDouble(input) / 100;
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                input = JOptionPane.showInputDialog(this, "Enter discount",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
            }
        }
        return -1;
    }
    
    private double findPrice(int inventoryId)
    {
        for(Inventory item : InventoryInfoForm.getInventoryList())
        {
            if(item.getId() == inventoryId)
                return item.getPrice();
        }
        return 0;
    }
    
    private void updateTotalSales(int salesId)
    {
        for(SalesPerson member : SalesInfoForm.getSalesPersonsList())
        {
            if(member.getId() == salesId)
            {
                member.setTotalSales(member.getTotalSales() + 1);
                break;
            }
        }
    }
    
    private void updateInventoryStock(int inventoryId, int amount)
    {
        for(Inventory item : InventoryInfoForm.getInventoryList())
        {
            if(item.getId() == inventoryId)
            {
                item.setInStock(item.getInStock() - amount);
                break;
            }
        }
    }
    
    private String findCustomerFullName(int customerId)
    {
        for(Customer customer : CustomerInfoForm.getCustomersList())
        {
            if(customer.getId() == customerId)
                return customer.getFirstName() + " " + customer.getLastName();
        }
        return null;
    }
    
    private String findCustomerPhone(int customerId)
    {
        for(Customer customer : CustomerInfoForm.getCustomersList())
        {
            if(customer.getId() == customerId)
                return customer.getPhone();
        }
        return null;
    }
    
    private String findInventoryName(int inventoryId)
    {
        for(Inventory item : InventoryInfoForm.getInventoryList())
        {
            if(item.getId() == inventoryId)
                return item.getName();
        }
        return null;
    }
    
    private String findGST(int idTransaction)
    {
        for(Transaction transaction : transactions)
        {
            if(transaction.getId() == idTransaction)
                return String.valueOf(transaction.getGST());
        }
        return null;
    }
    
    private String findPST(int idTransaction)
    {
        for(Transaction transaction : transactions)
        {
            if(transaction.getId() == idTransaction)
                return String.valueOf(transaction.getPST());
        }
        return null;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton logOffButton;
    private javax.swing.JButton printButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel transactionLbl;
    private javax.swing.JScrollPane transactionScrollPane;
    private javax.swing.JTable transactionTable;
    // End of variables declaration//GEN-END:variables
}