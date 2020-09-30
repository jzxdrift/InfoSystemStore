import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LogInForm extends javax.swing.JFrame
{
    private static final File FILE = new File(System.getProperty(HelperClass.HOME_DIRECTORY), "sales.txt");
    private static final String ERROR_TITLE = "Login Failure";
    private static final String LOGIN = "admin", PASSWORD = "admin";
    
    private final List<SalesPerson> users;  //only for authentication
    private BufferedReader inF;
    
    public LogInForm()
            throws IOException
    {
        initComponents();
        
        users = new ArrayList<>();
        readFile();
    }
    
    private void readFile()
            throws IOException
    {
        if(FILE.exists())
        {
            inF = new BufferedReader(new FileReader(FILE));
            String id = inF.readLine();
            
            while(id != null && !id.isEmpty())
            {
                SalesPerson user = new SalesPerson(Integer.parseInt(id), inF.readLine());
                users.add(user);
                
                //skipping unneeded lines
                inF.readLine();
                inF.readLine();
                inF.readLine();
                inF.readLine();
                inF.readLine();
                inF.readLine();
                
                id = inF.readLine();
            }
            inF.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        mainLbl = new javax.swing.JLabel();
        imageLogoLbl = new javax.swing.JLabel();
        userIDLbl = new javax.swing.JLabel();
        passLbl = new javax.swing.JLabel();
        userIDField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FCS");
        setResizable(false);

        mainLbl.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        mainLbl.setText("Future Communication Store");

        imageLogoLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        userIDLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        userIDLbl.setText("User ID");

        passLbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passLbl.setText("Password");

        userIDField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        passwordField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passwordField.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                passwordFieldKeyPressed(evt);
            }
        });

        logInButton.setText("Log In");
        logInButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logInButtonActionPerformed(evt);
            }
        });
        logInButton.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                logInButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addComponent(mainLbl)
                .addGap(204, 204, 204))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(imageLogoLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passLbl)
                            .addComponent(userIDLbl))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(mainLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userIDLbl)
                            .addComponent(userIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passLbl)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageLogoLbl)))
                .addGap(3, 3, 3)
                .addComponent(logInButton)
                .addGap(67, 67, 67))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_logInButtonActionPerformed
    {//GEN-HEADEREND:event_logInButtonActionPerformed
        boolean success = false;
        
        //admin mode
        if(userIDField.getText().equals(LOGIN) && passwordField.getText().equals(PASSWORD))
        {
            try
                {
                    this.dispose();
                    
                    ManagerHomeForm form = new ManagerHomeForm(0);
                    form.getCustomerButton().setEnabled(false);
                    form.getInventoryButton().setEnabled(false);
                    form.getTransactionButton().setEnabled(false);
                    form.setVisible(true);
                }
                catch(IOException ex) {}
        }
        //authenticating user
        else if(!users.isEmpty())
        {
            try
            {
                int id = Integer.parseInt(userIDField.getText());
                String password = passwordField.getText();
            
                for(SalesPerson user : users)
                {
                    if(user.getId() == id && user.getPassword().equals(password))
                    {
                        this.dispose();
                    
                        //checking which home form to open
                        if(user.getPassword().equals("manager"))
                            new ManagerHomeForm(user.getId()).setVisible(true);
                        else if(user.getPassword().equals("staff"))
                            new StaffHomeForm(user.getId()).setVisible(true);
                    
                        success = true;
                        break;
                    }
                }
            
                //ID or password does not match
                if(!success)
                {
                    JOptionPane.showMessageDialog(this, "Invalid ID or password",
                            ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                    userIDField.requestFocus();
                    userIDField.setText(null);
                    passwordField.setText(null);
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid ID", ERROR_TITLE,
                        JOptionPane.ERROR_MESSAGE);
                userIDField.requestFocus();
                userIDField.setText(null);
                passwordField.setText(null);
            }
            catch(IOException ex) {}
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Invalid ID or password",
                    ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            userIDField.requestFocus();
            userIDField.setText(null);
            passwordField.setText(null);
        }
    }//GEN-LAST:event_logInButtonActionPerformed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_passwordFieldKeyPressed
    {//GEN-HEADEREND:event_passwordFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            logInButton.doClick();
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void logInButtonKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_logInButtonKeyPressed
    {//GEN-HEADEREND:event_logInButtonKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            logInButton.doClick();
    }//GEN-LAST:event_logInButtonKeyPressed

    public static void main(String args[])
    {
        try
        {
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() ->
        {
            try
            {
                new LogInForm().setVisible(true);
            }
            catch(IOException ex) {}
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLogoLbl;
    private javax.swing.JButton logInButton;
    private javax.swing.JLabel mainLbl;
    private javax.swing.JLabel passLbl;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField userIDField;
    private javax.swing.JLabel userIDLbl;
    // End of variables declaration//GEN-END:variables
}