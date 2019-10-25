import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * this GUI is the page after user pick a roll as Customer.
 * it provides either signIn or SignUp functions to Customer
 *
 */
public class CustomerSignGUI extends BankATM {
    private JFrame frame;
    private JLabel lblName;
    private JLabel lblPwd;
    private JLabel lblErrorMessage;
    private JTextField tfName;
    private JPasswordField pfPwd;
    private JPanel pane;
    private JPanel pane1;
    private JButton btnSignIn;
    private JButton btnSignUp;
    private JButton btnBack;
    private UserType userType = UserType.CUSTOMER;

    public CustomerSignGUI(Bank bank) {
        super(bank);
        this.frame = new JFrame("Bank Customer SignIn");
        this.frame.setSize(500,400);
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

    private void addActiveComponent() {
      //lblName
        this.lblName = new JLabel();
        lblName.setText("UserName: ");
        //this.pane.add(lblName);
        //tfName
        this.tfName = new JTextField(10);
        //this.pane.add(tfName);

        //lblPwd
        this.lblPwd = new JLabel();
        lblPwd.setText("Password: ");
        //this.pane.add(lblPwd);
        //tfPwd
        this.pfPwd = new JPasswordField(10);
        //this.pane.add(pfPwd);//button panel

        //lblErrorMessage
        this.lblErrorMessage = new JLabel();
        //this.pane.add(lblErrorMessage);


        //Back to previous button
        this.btnBack = new JButton("Go Back");
        this.btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PickRollGUI pickRollGUI = new PickRollGUI(getBank());
                frame.dispose();
                pickRollGUI.show();
            }
        });
        //this.btnPane.add(btnBack);

        //signIn button
        this.btnSignIn = new JButton("SignIn");
        this.btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean signIn = getBank().signIn(tfName.getText(), new String(pfPwd.getPassword()), userType);
                if(signIn) {
                    //signIn succeed
                    //go to Customer's BankATM page
                    Cache cache = new Cache(tfName.getText(), new String(pfPwd.getPassword()), userType);
                    CustomerGUI customerGUI = new CustomerGUI(getBank(), cache);
                    frame.dispose();
                    customerGUI.show();
                } else {
                    //signIn failed
                    lblErrorMessage.setText("SignIn Failed. Plaese try again");
                    lblErrorMessage.setVisible(true);
                }
            }
        });
        //this.btnPane.add(this.btnSignIn);
        //signUp
        this.btnSignUp = new JButton("SignUp");
        this.btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //go to signUp page
                SignUpGUI signUpGUI = new SignUpGUI(getBank(), userType);
                frame.dispose();
                signUpGUI.show();
            }
        });
        //this.btnPane.add(this.btnSignUp);

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

        // Second Column

        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 1;
        gc.gridy = 1;
        this.pane.add(tfName, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        this.pane.add(pfPwd, gc);

        //Third Column
        gc.weighty = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;
        this.pane.add(btnSignUp, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        this.pane.add(btnSignIn, gc);



        this.pane.setBackground(new Color(50,115,220,30));

        this.pane1 = new JPanel();
        this.pane1.setBackground(new Color(50,115,220,60));
        this.pane1.add(lblErrorMessage);
        this.frame.add(pane1, BorderLayout.SOUTH);
        this.frame.add(pane, BorderLayout.CENTER);

    }

    public void show() {
        this.frame.setVisible(true);
    }
}
