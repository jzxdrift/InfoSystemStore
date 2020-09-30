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
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.AbstractTableModel;

public class InventoryInfoForm extends javax.swing.JFrame implements ReadWriteFile
{
    private static final File FILE = new File(System.getProperty(HelperClass.HOME_DIRECTORY), "inventory.txt");
    private static final String ADD_TITLE = "Add item";
    
    private final String[] columns = {"ID", "Inventory Name", "Total Number",
        "Left in Stock", "Price ($)", "Upgrade Date"};
    private final InventoryTableModel model;
    
    private static List<Inventory> inventory;
    private int itemId;
    
    private final String CURRENT_USER;
    
    private ManagerHomeForm managerForm;
    private StaffHomeForm staffForm;
    
    private BufferedReader inF;
    private BufferedWriter outF;
    
    private class InventoryTableModel extends AbstractTableModel
    {
        @Override
        public int getRowCount()
        {
            return inventory.size();
        }

        @Override
        public int getColumnCount()
        {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            Inventory item = inventory.get(rowIndex);
            switch(columnIndex)
            {
                case 0:
                    return item.getId();
                case 1:
                    return item.getName();
                case 2:
                    return item.getTotal();
                case 3:
                    return item.getInStock();
                case 4:
                    return HelperClass.CURRENCY.format(item.getPrice());
                case 5:
                    return item.getUpgradeDate();
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
            return columnIndex == 1 || columnIndex == 2 || columnIndex == 4;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            Inventory item = inventory.get(rowIndex);
            switch(columnIndex)
            {
                case 1:
                    item.setName(aValue.toString());
                    break;
                case 2:
                    int option = JOptionPane.showConfirmDialog(null, "Edit total number?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)
                    {
                        try
                        {
                            item.setTotal(item.getTotal() + Integer.parseInt(aValue.toString()));
                            item.setInStock(item.getInStock() + Integer.parseInt(aValue.toString()));
                            item.upgradeDate();
                            model.fireTableDataChanged();
                        }
                        catch(NumberFormatException ex) {}
                    }
                    break;
                case 4:
                    try
                    {
                        item.setPrice(Double.parseDouble(aValue.toString()));
                        item.upgradeDate();
                        model.fireTableDataChanged();
                    }
                    catch(NumberFormatException ex) {}
                    break;
            }
        }
    }
    
    public InventoryInfoForm(String user, Object obj)
            throws IOException
    {
        CURRENT_USER = user;   //manager or staff
        
        //checking which form opened this form
        if(CURRENT_USER.equals("manager"))
            managerForm = (ManagerHomeForm)obj;
        else if(CURRENT_USER.equals("staff"))
            staffForm = (StaffHomeForm)obj;
        
        model = new InventoryTableModel();
        inventory = new ArrayList<>();
        
        readFile();
        
        initComponents();
    }
    
    public static List<Inventory> getInventoryList()
    {
        return inventory;
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
                Inventory item = new Inventory(Integer.parseInt(id));
                item.setTotal(Integer.parseInt(inF.readLine()));
                item.setInStock(Integer.parseInt(inF.readLine()));
                item.setPrice(Double.parseDouble(inF.readLine()));
                item.setName(inF.readLine());
                item.setUpgradeDate(inF.readLine());
                inventory.add(item);
                
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
        
        for(Inventory item : inventory)
        {
            outF.write(Integer.toString(item.getId()));
            outF.newLine();
            outF.write(Integer.toString(item.getTotal()));
            outF.newLine();
            outF.write(Integer.toString(item.getInStock()));
            outF.newLine();
            outF.write(Double.toString(item.getPrice()));
            outF.newLine();
            outF.write(item.getName());
            outF.newLine();
            outF.write(item.getUpgradeDate());
            
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

        inventoryLbl = new javax.swing.JLabel();
        logOffButton = new javax.swing.JButton();
        inventoryScrollPane = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
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
        setTitle("Inventory Information");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        inventoryLbl.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        inventoryLbl.setText("Inventory Information");

        logOffButton.setText("Log Off");
        logOffButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logOffButtonActionPerformed(evt);
            }
        });

        inventoryTable.setModel(model);
        inventoryTable.getTableHeader().setReorderingAllowed(false);
        inventoryScrollPane.setViewportView(inventoryTable);
        inventoryTable.setAutoCreateRowSorter(true);
        inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(25);
        inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(25);
        inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(25);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addButtonActionPerformed(evt);
            }
        });

        searchField.setToolTipText("Enter item");
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
                                .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addComponent(inventoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(logOffButton)
                                    .addContainerGap())
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(inventoryLbl)
                                    .addGap(291, 291, 291))))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(logOffButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(inventoryLbl)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(inventoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
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
            if(!inventory.isEmpty() && !CustomerInfoForm.getCustomersList().isEmpty())
                managerForm.getTransactionButton().setEnabled(true);
            else
                managerForm.getTransactionButton().setEnabled(false);
        }
        else if(CURRENT_USER.equals("staff"))
        {
            staffForm.setVisible(true);
            if(!inventory.isEmpty() && !CustomerInfoForm.getCustomersList().isEmpty())
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
        if(inventory.isEmpty())
            itemId = 1; //no item with ID of 0
        else
            itemId = inventory.get(inventory.size() - 1).getId() + 1;
        
        //creating Inventory object and adding to list only if all fields are filled
        String input = JOptionPane.showInputDialog(this, "Enter item name", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
        if(input != null && !"".equals(input))
        {
            String itemName = input;

            input = JOptionPane.showInputDialog(this, "Enter total number", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
            if(input != null && !"".equals(input))
            {
                //checking for valid input
                int total = checkTotalNumber(input);
                if(total > 0)
                {
                    input = JOptionPane.showInputDialog(this, "Enter price", ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                    if(input != null && !"".equals(input))
                    {
                        //checking for valid input
                        double price = checkPrice(input);
                        if(price > 0)
                        {
                            Inventory item = new Inventory(itemId);
                            item.setName(itemName);
                            item.setTotal(total);
                            item.setInStock(total);
                            item.setPrice(price);
                            inventory.add(item);
                                
                            model.fireTableDataChanged();
                            //auto-scrolling to added entry
                            inventoryTable.scrollRectToVisible(inventoryTable.getCellRect
                                (inventoryTable.getRowCount() - 1, 0, true));
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchButtonActionPerformed
    {//GEN-HEADEREND:event_searchButtonActionPerformed
        boolean found = false;
        inventoryTable.clearSelection();
        
        ListSelectionModel selectionModel = inventoryTable.getSelectionModel();
        selectionModel.clearSelection();
        
        //searching by inventory name
        String name = searchField.getText();
        int index = 0;
        if(!name.equals(""))
        {
            for(Inventory item : inventory)
            {
                if(item.getName().toLowerCase().contains(name.toLowerCase()))
                {
                    index = inventory.indexOf(item);
                    selectionModel.addSelectionInterval(index, index);
                    found = true;
                }
            }
            
            if(!found)
                JOptionPane.showMessageDialog(this, "Not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //auto-scrolling to last item found
        inventoryTable.scrollRectToVisible(inventoryTable.getCellRect(index, 0, true));
        
        searchField.setText(null);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_searchFieldKeyPressed
    {//GEN-HEADEREND:event_searchFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            searchButton.doClick();
    }//GEN-LAST:event_searchFieldKeyPressed

    private int checkTotalNumber(String input)
    {
        while(true)
        {
            try
            {
                return Integer.parseInt(input);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                input = JOptionPane.showInputDialog(this, "Enter total number",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                if(input == null || "".equals(input))
                    return 0;
            }
        }
    }
    
    private double checkPrice(String input)
    {
        while(true)
        {
            try
            {
                return Double.parseDouble(input);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                input = JOptionPane.showInputDialog(this, "Enter price",
                        ADD_TITLE, JOptionPane.PLAIN_MESSAGE);
                if(input == null || "".equals(input))
                    return 0;
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel inventoryLbl;
    private javax.swing.JScrollPane inventoryScrollPane;
    private javax.swing.JTable inventoryTable;
    private javax.swing.JButton logOffButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}