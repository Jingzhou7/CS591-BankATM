import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * this is the GUI for signUp form
 * both manager and customer have the same signUp form
 *
 */
public class SignUpGUI extends BankATM{
    private JFrame frame;
    private JLabel lblName;
    private JLabel lblPwd;
    private JLabel lblPwd2;

    private JLabel lblMessage;
    private JTextField tfName;
    private JPasswordField pfPwd;
    private JPasswordField pfPwd2;
    private JPanel pane;
    private JPanel pane1;
    private JButton btnBack;
    private JButton btnClear;
    private JButton btnSubmit;
    private UserType userType;

    public SignUpGUI(Bank bank, UserType userType) {
        super(bank);
        setUserType(userType);
        this.frame = new JFrame("Sign Up Form");
        this.frame.setSize(500,500);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.frame.setVisible(false);
        addActiveComponent();
        this.frame.setLocationRelativeTo(null);
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private void addActiveComponent() {
//        this.pane = new JPanel(new GridLayout(7, 1, 0, 1));

        //lblName
        this.lblName = new JLabel();
        lblName.setText("UserName: ");
//        this.pane.add(lblName);
        //tfName
        this.tfName = new JTextField(10);
//        this.pane.add(tfName);

        //lblPwd
        this.lblPwd = new JLabel();
        lblPwd.setText("Password: ");
//        this.pane.add(lblPwd);
        //pfPwd
        this.pfPwd = new JPasswordField(10);
//        this.pane.add(pfPwd);

        //lblPwd2
        this.lblPwd2 = new JLabel();
        this.lblPwd2.setText("Confirm password: ");
//        this.pane.add(lblPwd2);
        //pfPwd2
        this.pfPwd2 = new JPasswordField(10);
//        this.pane.add(pfPwd2);

        //lblMessage
        this.lblMessage = new JLabel();
        this.lblMessage.setVisible(false);
//        this.pane.add(lblMessage);

//        this.btnPane = new JPanel(new GridLayout(0,3));

        //btnBack
        this.btnBack = new JButton("Go Back");
        this.btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //ge back to ManagerGUi or CustomerSignGUI
                if(userType == UserType.MANAGER) {
                    //go back to ManagerSignGUI
                    ManagerSignGUI managerSignGUI = new ManagerSignGUI(getBank());
                    frame.dispose();
                    managerSignGUI.show();
                } else {
                    //go back to CustomerSignGUI
                    CustomerSignGUI customerSignGUI = new CustomerSignGUI(getBank());
                    frame.dispose();
                    customerSignGUI.show();
                }
            }
        });
//        this.btnPane.add(btnBack);

        this.btnClear = new JButton("Clear the Form");
        this.btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tfName.setText("");
                pfPwd.setText("");
                pfPwd2.setText("");
            }
        });
//        this.btnPane.add(btnClear);

        this.btnSubmit = new JButton("Submit");
        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //check for pwd and pwd2. they have to be the same
                String pwd1 = new String(pfPwd.getPassword());
                String pwd2 = new String(pfPwd2.getPassword());
                if(pwd1.equals(pwd2)) {
                    boolean signUp = getBank().signUp(tfName.getText(), pwd1, userType);
                    if(signUp) {
                        //signUp succeed
                        lblMessage.setText("SignUp form submitted");
                        lblMessage.setVisible(true);
                        //clear the user input after successful Signing up
                        tfName.setText("");
                        pfPwd.setText("");
                        pfPwd2.setText("");

                    } else {
                        //signUp failed. String already existed
                        lblMessage.setText("Duplicated UserName");
                        lblMessage.setVisible(true);
                    }
                } else {
                    lblMessage.setText("Please enter the same password to confirm");
                    lblMessage.setVisible(true);
                }
            }
        });
        this.pane = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // First Column

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        this.pane.add(btnBack, gc);
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 1;
        this.pane.add(lblName, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        this.pane.add(lblPwd, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        this.pane.add(lblPwd2, gc);
        // Second Column

        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 1;
        gc.gridy = 1;
        this.pane.add(tfName, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        this.pane.add(pfPwd, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        this.pane.add(pfPwd2, gc);

        //Third Column
        gc.weighty = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 4;
        this.pane.add(btnClear, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        this.pane.add(btnSubmit, gc);


        this.pane.setBackground(new Color(50,115,220,30));

        this.pane1 = new JPanel();
        this.pane1.setBackground(new Color(50,115,220,60));
        this.pane1.add(lblMessage);
        this.frame.add(pane1, BorderLayout.SOUTH);
        this.frame.add(pane, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }
}
