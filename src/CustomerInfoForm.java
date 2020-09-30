import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.table.AbstractTableModel;

public class CustomerInfoForm extends javax.swing.JFrame implements ReadWriteFile
{
    private static final File FILE = new File(System.getProperty(HelperClass.HOME_DIRECTORY), "customers.txt");
    private static final String ADD_TITLE = "Add customer";
    
    private final String[] columns = {"ID", "First Name", "Last Name", "Phone",
        "Customer Since", "Sales ID", "Content", "Type"};
    private final CustomerTableModel model;
    
    private static List<Customer> customers;
    private int customerId;
    
    private final String CURRENT_USER;
    private final int SALES_ID;
    
    private ManagerHomeForm managerForm;
    private StaffHomeForm staffForm;
    
    private BufferedReader inF;
    private BufferedWriter outF;
    
    private class CustomerTableModel extends AbstractTableModel
    {
        @Override
        public int getRowCount()
        {
            return customers.size();
        }

        @Override
        public int getColumnCount()
        {
            return 8;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            Customer customer = customers.get(rowIndex);
            switch(columnIndex)
            {
                case 0:
                    return customer.getId();
                case 1:
                    return customer.getFirstName();
                case 2:
                    return customer.getLastName();
                case 3:
                    return String.format(HelperClass.PHONE_FORMAT,
                        customer.getPhone().substring(0, 3),
                        customer.getPhone().substring(3, 6),
                        customer.getPhone().substring(6, 10));
                case 4:
                    return customer.getCustomerSince();
                case 5:
                    return customer.getSalesId();
                case 6:
                    return customer.getContent();
                case 7:
                    return customer.getType();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column)
        {
            return columns[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return columnIndex == 1 || columnIndex == 2 || columnIndex == 3
                    || columnIndex == 6 || columnIndex == 7;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            Customer customer = customers.get(rowIndex);
            switch(columnIndex)
            {
                case 1:
                    customer.setFirstName(aValue.toString());
                    break;
                case 2:
                    customer.setLastName(aValue.toString());
                    break;
                case 3:
                    if(aValue.toString().length() == 10)
                        customer.setPhone(aValue.toString());
                    break;
                case 6:
                    customer.setContent(aValue.toString());
                    break;
                case 7:
                    customer.setType(aValue.toString());
                    break;
            }
        }
    }
    
    public CustomerInfoForm(String user, int id, Object obj)
            throws IOException
    {
        CURRENT_USER = user;   //manager or staff
        SALES_ID = id;   //whoever uses system at the moment
        
        //checking which form opened this form
        if(CURRENT_USER.equals("manager"))
            managerForm = (ManagerHomeForm)obj;
        else if(CURRENT_USER.equals("staff"))
            staffForm = (StaffHomeForm)obj;
        
        model = new CustomerTableModel();
        customers = new ArrayList<>();
        
        readFile();
        
        initComponents();
    }
    
    public static List<Customer> getCustomersList()
    {
        return customers;
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
                Customer customer = new Customer(Integer.parseInt(id), Integer.parseInt(inF.readLine()));
                customer.setFirstName(inF.readLine());
                customer.setLastName(inF.readLine());
                customer.setPhone(inF.readLine());
                customer.setCustomerSince(inF.readLine());
                customer.setContent(inF.readLine());
                customer.setType(inF.readLine());
                customers.add(customer);
                
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
        
        for(Customer customer : customers)
        {
            outF.write(Integer.toString(customer.getId()));
            outF.newLine();
            outF.write(Integer.toString(customer.getSalesId()));
            outF.newLine();
            outF.write(customer.getFirstName());
            outF.newLine();
            outF.write(customer.getLastName());
            outF.newLine();
            outF.write(customer.getPhone());
            outF.newLine();
            outF.write(customer.getCustomerSince());
            outF.newLine();
            outF.write(customer.getContent());
            outF.newLine();
            outF.write(customer.getType());
            
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

        customerLbl = new javax.swing.JLabel();
        logOffButton = new javax.swing.JButton();
        customerScrollPane = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Customer Information");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        customerLbl.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        customerLbl.setText("Customer Information");

        logOffButton.setText("Log Off");
        logOffButton.setFocusPainted(false);
        logOffButton.setFocusable(false);
        logOffButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logOffButtonActionPerformed(evt);
            }
        });

        customerTable.setModel(model);
        customerTable.getTableHeader().setReorderingAllowed(false);
        customerScrollPane.setViewportView(customerTable);
        customerTable.setAutoCreateRowSorter(true);
        customerTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        customerTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        customerTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        customerTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        customerTable.getColumnModel().getColumn(5).setPreferredWidth(35);
        customerTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        customerTable.getColumnModel().getColumn(7).setPreferredWidth(35);

        addButton.setText("Add");
        addButton.setFocusPainted(false);
        addButton.setFocusable(false);
        addButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.setFocusable(false);
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
            searchButton.setFocusPainted(false);
            searchButton.setFocusable(false);
            searchButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    searchButtonActionPerformed(evt);
                }
            });

            homeButton.setText("Home");
            homeButton.setFocusPainted(false);
            homeButton.setFocusable(false);
            homeButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    homeButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(searchField)
                                .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(customerScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(logOffButton)
                                    .addContainerGap())
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(customerLbl)
                                    .addGap(291, 291, 291))))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(logOffButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(customerLbl)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(customerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(deleteButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
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
        {
            managerForm.setVisible(true);
            if(!customers.isEmpty() && !InventoryInfoForm.getInventoryList().isEmpty())
                managerForm.getTransactionButton().setEnabled(true);
            else
                managerForm.getTransactionButton().setEnabled(false);
        }
        else if(CURRENT_USER.equals("staff"))
        {
            staffForm.setVisible(true);
            if(!customers.isEmpty() && !InventoryInfoForm.getInventoryList().isEmpty())
                staffForm.getTransactionButton().setEnabled(true);
            else
                staffForm.getTransactionButton().setEnabled(false);
        }
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
        if(customers.isEmpty())
            customerId = 1;  //no customer with ID of 0
        else
            customerId = customers.get(customers.size() - 1).getId() + 1;
        
        //creating Customer object and adding to list only if all fields are filled
        String input = JOptionPane.showInputDialog(this, "Enter first name", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
        if(input != null && !"".equals(input))
        {
            String firstName = input;
            
            input = JOptionPane.showInputDialog(this, "Enter last name", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
            if(input != null && !"".equals(input))
            {
                String lastName = input;
                
                input = JOptionPane.showInputDialog(this, "Enter 10-digit phone number",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                if(input != null && !"".equals(input))
                {
                    //checking for 10-digit phone number
                    String phone = HelperClass.checkPhoneLength(input);
                    if(phone != null && !"".equals(phone))
                    {
                        input = JOptionPane.showInputDialog(this, "Enter content", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                        if(input != null && !"".equals(input))
                        {
                            String content = input;
                        
                            input = JOptionPane.showInputDialog(this, "Enter type", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                            if(input != null && !"".equals(input))
                            {
                                Customer customer = new Customer(customerId, SALES_ID);
                                customer.setFirstName(firstName);
                                customer.setLastName(lastName);
                                customer.setPhone(phone);
                                customer.setContent(content);
                                customer.setType(input);
                                customers.add(customer);
                            
                                model.fireTableDataChanged();
                                //auto-scrolling to added entry
                                customerTable.scrollRectToVisible(customerTable.getCellRect
                                    (customerTable.getRowCount() - 1, 0, true));
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
        customerTable.clearSelection();
        
        //searching by phone number
        String phone = searchField.getText();
        for(Customer customer : customers)
        {
            if(customer.getPhone().equals(phone))
            {
                int index = customers.indexOf(customer);
                customerTable.setRowSelectionInterval(index, index);
                //auto-scrolling to search result
                customerTable.scrollRectToVisible(customerTable.getCellRect(index, 0, true));
                
                found = true;
                break;
            }
        }
        
        if(!found && !phone.equals(""))
            JOptionPane.showMessageDialog(this, "Not found", "Error", JOptionPane.ERROR_MESSAGE);
        
        searchField.setText(null);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteButtonActionPerformed
    {//GEN-HEADEREND:event_deleteButtonActionPerformed
        if(!customers.isEmpty() && customerTable.getSelectedRow() >= 0)
            customers.remove(customerTable.getSelectedRow());
        model.fireTableDataChanged();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_searchFieldKeyPressed
    {//GEN-HEADEREND:event_searchFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchButton.doClick();
    }//GEN-LAST:event_searchFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel customerLbl;
    private javax.swing.JScrollPane customerScrollPane;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton logOffButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}