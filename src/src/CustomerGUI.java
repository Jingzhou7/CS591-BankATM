import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * this GUI is for customer after signIn
 * any functions that a customer can perform is on this page
 *
 */
public class CustomerGUI extends ATMService {
    private JFrame frame;
    private JLabel lblMessage;
    private JPanel pane;
    private JPanel pane2;
    private JButton btnOpenAccount;
    private JButton btnDeposit;
    private JButton btnWithdraw;
    private JButton btnTransfer;
    private JButton btnRequestLoan;
    private JButton btnPayLoan;
    private JButton btnViewAccount;
    private JButton btnBack;

    public CustomerGUI(Bank bank, Cache cache) {
        super(bank, cache);
        this.frame = new JFrame("My Fancy Bank [Customer]");
        this.frame.setSize(600, 800);
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
        this.pane = new JPanel(new GridLayout(4,2,2,2));
        this.pane.setPreferredSize(new Dimension(400, 600));
        this.lblMessage = new JLabel();
        this.lblMessage.setVisible(false);
        //Open Account button
        this.btnOpenAccount = new JButton("Open Account");
        this.btnOpenAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OpenAccountGUI openAccountGUI = new OpenAccountGUI(getBank(), getCache());
                frame.dispose();
                openAccountGUI.show();
            }
        });

        this.pane.add(btnOpenAccount);


        //Deposit button
        this.btnDeposit = new JButton("Deposit");
        this.btnDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DepositGUI depositGUI = new DepositGUI(getBank(), getCache());
                frame.dispose();
                depositGUI.show();

            }
        });
        this.pane.add(btnDeposit);

        //withdraw button
        this.btnWithdraw = new JButton("Withdraw");
        this.btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WithdrawGUI withdrawGUI = new WithdrawGUI(getBank(), getCache());
                frame.dispose();
                withdrawGUI.show();
            }
        });
        this.pane.add(btnWithdraw);

        //Transfer button
        this.btnTransfer = new JButton("Transfer");
        this.btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TransferGUI transferGUI = new TransferGUI(getBank(), getCache());
                frame.dispose();
                transferGUI.show();
            }
        });
        this.pane.add(btnTransfer);

        //Request Loan button
        this.btnRequestLoan = new JButton("Request Loan");
        this.btnRequestLoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lblMessage.setText("View Account function not available");
                lblMessage.setVisible(true);
//                RequestLoanGUI requestLoanGUI = new RequestLoanGUI(getBank(), getCache());
//                frame.dispose();
//                requestLoanGUI.show();
            }
        });
        this.pane.add(btnRequestLoan);

        //Pay Loan button
        this.btnPayLoan = new JButton("Pay Loan");
        this.btnPayLoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //boolean success = getBank().payLoan();
                lblMessage.setText("Pay Loan function not available");
                lblMessage.setVisible(true);
            }
        });
        this.pane.add(btnPayLoan);

        //Account Info button
        this.btnViewAccount = new JButton("View Account");
        this.btnViewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lblMessage.setText("View Account function not available");
                lblMessage.setVisible(true);
            }
        });
        this.pane.add(btnViewAccount);

        //Back to previous button
        this.btnBack = new JButton("Log out");
        this.btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CustomerSignGUI customerSignGUI = new CustomerSignGUI(getBank());
                frame.dispose();
                customerSignGUI.show();
            }
        });
        this.pane.add(btnBack);

        this.pane2 = new JPanel();
        this.pane2.add(lblMessage);

        this.frame.add(pane, BorderLayout.CENTER);
        this.frame.add(pane2, BorderLayout.PAGE_END);
    }


    @Override
    public void show() {
        this.frame.setVisible(true);
    }
}
