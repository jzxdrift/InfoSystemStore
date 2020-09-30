import java.io.IOException;
import javax.swing.JOptionPane;

public class ManagerHomeForm extends javax.swing.JFrame
{
    private final int SALES_ID;
    
    private final CustomerInfoForm customerInfoForm;
    private final InventoryInfoForm inventoryInfoForm;
    private final SalesInfoForm salesInfoForm;
    private final TransactionInfoForm transactionInfoForm;
    
    public ManagerHomeForm(int id)
            throws IOException
    {
        SALES_ID = id;   //whoever uses system at the moment
        
        initComponents();
        
        customerInfoForm = new CustomerInfoForm("manager", SALES_ID, this);
        inventoryInfoForm = new InventoryInfoForm("manager", this);
        salesInfoForm = new SalesInfoForm("manager", this);
        transactionInfoForm = new TransactionInfoForm("manager", SALES_ID, this);
        
        //transactions can not be added until at least one inventory item and customer is added
        if(InventoryInfoForm.getInventoryList().isEmpty() || CustomerInfoForm.getCustomersList().isEmpty())
            transactionButton.setEnabled(false);
    }
    
    public javax.swing.JButton getCustomerButton()
    {
        return this.customerButton;
    }
    
    public javax.swing.JButton getTransactionButton()
    {
        return this.transactionButton;
    }
    
    public javax.swing.JButton getInventoryButton()
    {
        return this.inventoryButton;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        mainLbl = new javax.swing.JLabel();
        logOffButton = new javax.swing.JButton();
        customerButton = new javax.swing.JButton();
        inventoryButton = new javax.swing.JButton();
        salesButton = new javax.swing.JButton();
        transactionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Manager Home Page");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        mainLbl.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        mainLbl.setText("Future Communication Store");

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

        customerButton.setText("Customer Information");
        customerButton.setFocusPainted(false);
        customerButton.setFocusable(false);
        customerButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                customerButtonActionPerformed(evt);
            }
        });

        inventoryButton.setText("Inventory Information");
        inventoryButton.setFocusPainted(false);
        inventoryButton.setFocusable(false);
        inventoryButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                inventoryButtonActionPerformed(evt);
            }
        });

        salesButton.setText("Sales Information");
        salesButton.setFocusPainted(false);
        salesButton.setFocusable(false);
        salesButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                salesButtonActionPerformed(evt);
            }
        });

        transactionButton.setText("Transaction Information");
        transactionButton.setFocusPainted(false);
        transactionButton.setFocusable(false);
        transactionButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                transactionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(logOffButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mainLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inventoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(salesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(transactionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(206, 206, 206))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(logOffButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainLbl)
                .addGap(18, 18, 18)
                .addComponent(customerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inventoryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transactionButton)
                .addContainerGap(74, Short.MAX_VALUE))
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
            //closing all invisible forms which run in background
            customerInfoForm.dispose();
            inventoryInfoForm.dispose();
            salesInfoForm.dispose();
            transactionInfoForm.dispose();
            this.dispose();
            
            try
            {
                new LogInForm().setVisible(true);
            }
            catch(IOException ex) {}
        }
    }//GEN-LAST:event_logOffButtonActionPerformed

    private void customerButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_customerButtonActionPerformed
    {//GEN-HEADEREND:event_customerButtonActionPerformed
        this.setVisible(false);
        customerInfoForm.setVisible(true);
    }//GEN-LAST:event_customerButtonActionPerformed

    private void inventoryButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inventoryButtonActionPerformed
    {//GEN-HEADEREND:event_inventoryButtonActionPerformed
        this.setVisible(false);
        inventoryInfoForm.setVisible(true);  
    }//GEN-LAST:event_inventoryButtonActionPerformed

    private void salesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_salesButtonActionPerformed
    {//GEN-HEADEREND:event_salesButtonActionPerformed
        this.setVisible(false);
        salesInfoForm.setVisible(true);
    }//GEN-LAST:event_salesButtonActionPerformed

    private void transactionButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_transactionButtonActionPerformed
    {//GEN-HEADEREND:event_transactionButtonActionPerformed
        this.setVisible(false);
        transactionInfoForm.setVisible(true);  
    }//GEN-LAST:event_transactionButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        int option = JOptionPane.showConfirmDialog(this, HelperClass.CONFIRM_MESSAGE,
                HelperClass.CLOSE_TITLE, JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton customerButton;
    private javax.swing.JButton inventoryButton;
    private javax.swing.JButton logOffButton;
    private javax.swing.JLabel mainLbl;
    private javax.swing.JButton salesButton;
    private javax.swing.JButton transactionButton;
    // End of variables declaration//GEN-END:variables
}