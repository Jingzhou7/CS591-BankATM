import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * this GUI is used to create account
 */
public class OpenAccountGUI extends ATMService {
    //this class is used for customers to create accounts after sign in
    //so it extends ATMService which has cache
    private JFrame frame;
    private JLabel lblCurrencyUnit;
    private JLabel lblCurrencyAmount;
    private JLabel lblAccountType;
    private JLabel lblMessage;
    private JTextField tfAmount;
    private JComboBox cbCurrencyUnit;
    private JComboBox cbAccountType;
    private JPanel pane;
    private JPanel pane1;
    private JButton btnOpenAccount;
    private JButton btnBack;
    private UserType userType = UserType.CUSTOMER;


    public OpenAccountGUI(Bank bank, Cache cache) {
        super(bank, cache);
        this.frame = new JFrame("Create Account [Customer]");
        this.frame.setSize(500, 500);
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
        //AccountType lable
        this.lblAccountType = new JLabel();
        lblAccountType.setText("Account Type: ");
//        this.pane.add(lblAccountType);
        //AccountType ComboBox
        this.cbAccountType = new JComboBox(AccountType.values());
        this.cbAccountType.setEditable(true);
//        this.pane.add(cbAccountType);

        //currency amount label
        this.lblCurrencyAmount = new JLabel();
        lblCurrencyAmount.setText("Initial Deposit Amount: ");
//        this.pane.add(lblCurrencyAmount);
        //amount txt
        this.tfAmount = new JTextField(10);
//        this.pane.add(tfAmount);

        //currency unit label
        this.lblCurrencyUnit = new JLabel();
        this.lblCurrencyUnit.setText("Choose Currency");
//        this.pane.add(lblCurrencyUnit);
        //currency unit comboBox
        this.cbCurrencyUnit = new JComboBox(CurrencyUnit.values());
        this.cbAccountType.setEditable(true);
//        this.pane.add(cbCurrencyUnit);

        //lblMessage
        this.lblMessage = new JLabel();
        this.lblMessage.setVisible(false);
//        this.pane.add(lblMessage);

//        this.btnPane = new JPanel(new GridLayout(0,2));

        //btnBack
        this.btnBack = new JButton("Go Back");
        this.btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //ge back to CustomerSignGUI
                CustomerGUI customerGUI = new CustomerGUI(getBank(), getCache());
                frame.dispose();
                customerGUI.show();
            }
        });
//        this.btnPane.add(btnBack);



        this.btnOpenAccount = new JButton("Open Account");
        this.btnOpenAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Currency initDeposit = new Currency(Double.parseDouble(tfAmount.getText()), (CurrencyUnit) cbCurrencyUnit.getSelectedItem());
                AccountType accType = (AccountType) cbAccountType.getSelectedItem();
                boolean openAcc = getBank().openAccount(initDeposit, accType, getCache());
                if(openAcc) {
                    //Open account succeed
                    lblMessage.setText("OpenAccount Notice: Open account succeed");
                    lblMessage.setVisible(true);
                    //go back to CustomerGUI

                } else {
                    //Open account failed
                    lblMessage.setText("OpenAccount Notice: Open account failed");
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
        this.pane.add(lblAccountType, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        this.pane.add(lblCurrencyAmount, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        this.pane.add(lblCurrencyUnit, gc);
        // Second Column

        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 1;
        gc.gridy = 1;
        this.pane.add(cbAccountType, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        this.pane.add(tfAmount, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        this.pane.add(cbCurrencyUnit, gc);

        //Third Column
        gc.weighty = 2;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 1;
        gc.gridy = 4;
        this.pane.add(btnOpenAccount, gc);



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
