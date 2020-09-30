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

public class SalesInfoForm extends javax.swing.JFrame implements ReadWriteFile
{
    private static final File FILE = new File(System.getProperty(HelperClass.HOME_DIRECTORY), "sales.txt");
    private static final String ADD_TITLE = "Add member";
    
    private final String[] columns = {"ID", "First Name", "Last Name", "Phone",
        "Staff Since", "Total Sales"};
    private final SalesTableModel model;
    
    private static List<SalesPerson> members;
    private int memberId;
    
    private final String CURRENT_USER;
    
    private ManagerHomeForm managerForm;
    private StaffHomeForm staffForm;
    
    private BufferedReader inF;
    private BufferedWriter outF;
    
    private class SalesTableModel extends AbstractTableModel
    {
        @Override
        public int getRowCount()
        {
            return members.size();
        }

        @Override
        public int getColumnCount()
        {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            SalesPerson member = members.get(rowIndex);
            switch(columnIndex)
            {
                case 0:
                    return member.getId();
                case 1:
                    return member.getFirstName();
                case 2:
                    return member.getLastName();
                case 3:
                    return String.format(HelperClass.PHONE_FORMAT,
                        member.getPhone().substring(0, 3),
                        member.getPhone().substring(3, 6),
                        member.getPhone().substring(6, 10));
                case 4:
                    return member.getStaffSince();
                case 5:
                    return member.getTotalSales();
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
            return columnIndex == 1 || columnIndex == 2 || columnIndex == 3;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            SalesPerson member = members.get(rowIndex);
            switch(columnIndex)
            {
                case 1:
                    member.setFirstName(aValue.toString());
                    break;
                case 2:
                    member.setLastName(aValue.toString());
                    break;
                case 3:
                    if(aValue.toString().length() == 10)
                        member.setPhone(aValue.toString());
                    break;
            }
        }
    }
    
    public SalesInfoForm(String user, Object obj)
            throws IOException
    {
        CURRENT_USER = user;   //manager or staff
        
        //checking which form opened this form
        if(CURRENT_USER.equals("manager"))
            managerForm = (ManagerHomeForm)obj;
        else if(CURRENT_USER.equals("staff"))
            staffForm = (StaffHomeForm)obj;
        
        model = new SalesTableModel();
        members = new ArrayList<>();
        
        readFile();
        
        initComponents();
    }
    
    public static List<SalesPerson> getSalesPersonsList()
    {
        return members;
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
                SalesPerson member = new SalesPerson(Integer.parseInt(id), inF.readLine());
                member.setTotalSales(Integer.parseInt(inF.readLine()));
                member.setFirstName(inF.readLine());
                member.setLastName(inF.readLine());
                member.setPhone(inF.readLine());
                member.setStaffSince(inF.readLine());
                members.add(member);
                
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
        
        for(SalesPerson member : members)
        {
            outF.write(Integer.toString(member.getId()));
            outF.newLine();
            outF.write(member.getPassword());
            outF.newLine();
            outF.write(Integer.toString(member.getTotalSales()));
            outF.newLine();
            outF.write(member.getFirstName());
            outF.newLine();
            outF.write(member.getLastName());
            outF.newLine();
            outF.write(member.getPhone());
            outF.newLine();
            outF.write(member.getStaffSince());
            
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

        salesLbl = new javax.swing.JLabel();
        logOffButton = new javax.swing.JButton();
        salesScrollPane = new javax.swing.JScrollPane();
        salesTable = new javax.swing.JTable();
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
        setTitle("Sales Information");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        salesLbl.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        salesLbl.setText("Sales Information");

        logOffButton.setText("Log Off");
        logOffButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logOffButtonActionPerformed(evt);
            }
        });

        salesTable.setModel(model);
        salesTable.getTableHeader().setReorderingAllowed(false);
        salesScrollPane.setViewportView(salesTable);
        salesTable.setAutoCreateRowSorter(true);
        salesTable.getColumnModel().getColumn(0).setPreferredWidth(15);

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
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(salesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(logOffButton)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(salesLbl)
                            .addGap(291, 291, 291))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(logOffButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(salesLbl)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(salesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(deleteButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
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
        if(members.isEmpty())
            memberId = 1;   //no staff member with ID of 0
        else
            memberId = members.get(members.size() - 1).getId() + 1;
        
        //creating SalesPerson object and adding to list only if all fields are filled
        String input = JOptionPane.showInputDialog(this, "Enter position (manager/staff)",
                ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
        if(input != null && !"".equals(input))
        {
            //checking for valid position
            String position = checkPosition(input);
            if(position != null && !"".equals(position))
            {
                input = JOptionPane.showInputDialog(this, "Enter first name", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
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
                                SalesPerson member = new SalesPerson(memberId, position);
                                member.setFirstName(firstName);
                                member.setLastName(lastName);
                                member.setPhone(phone);
                                members.add(member);
                        
                                model.fireTableDataChanged();
                                //auto-scrolling to added entry
                                salesTable.scrollRectToVisible(salesTable.getCellRect
                                    (salesTable.getRowCount() - 1, 0, true)); 
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
        salesTable.clearSelection();
        
        //searching by phone number
        String phone = searchField.getText();
        for(SalesPerson member : members)
        {
            if(member.getPhone().equals(phone))
            {
                int index = members.indexOf(member);
                salesTable.setRowSelectionInterval(index, index);
                //auto-scrolling to search result
                salesTable.scrollRectToVisible(salesTable.getCellRect(index, 0, true));
                
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
        if(!members.isEmpty() && salesTable.getSelectedRow() >= 0)
            members.remove(salesTable.getSelectedRow());
        model.fireTableDataChanged();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_searchFieldKeyPressed
    {//GEN-HEADEREND:event_searchFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchButton.doClick();
    }//GEN-LAST:event_searchFieldKeyPressed

    private String checkPosition(String input)
    {
        while(!input.equalsIgnoreCase("manager") && !input.equalsIgnoreCase("staff"))
        {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            input = JOptionPane.showInputDialog(this, "Enter position (manager/staff)",
                ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
            if(input == null || "".equals(input))
                return null;
        }
        return input.toLowerCase();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton logOffButton;
    private javax.swing.JLabel salesLbl;
    private javax.swing.JScrollPane salesScrollPane;
    private javax.swing.JTable salesTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}