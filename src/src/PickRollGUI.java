import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PickRollGUI extends BankATM {
    //this is the very first frame showed
    //main will show this frame to start the bankATM
    private JFrame frame;
    //private JLabel lbl;
    private JButton btnCustomer;
    private JButton btnManager;
    private JPanel pnlCustomer;
    private JPanel pnlManager;

    public PickRollGUI(Bank bank) {
        super(bank);
        this.frame = new JFrame("My Fancy Bank");
        this.frame.setSize(500, 300);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.frame.setVisible(false);
        //this.lbl = new JLabel("**************************Welcome to My Fancy Bank*************************");
        //this.frame.add(lbl);
        addActiveComponent();
        this.frame.setLayout(new GridLayout(1, 2));
        this.frame.setLocationRelativeTo(null);
    }

    private void addActiveComponent() {
        //customer button
        this.btnCustomer = new JButton("For Customer");
        this.btnCustomer.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CustomerSignGUI customerSignGUI = new CustomerSignGUI(getBank());
                frame.dispose();
                customerSignGUI.show();
            }
        });

        //manager button
        this.btnManager = new JButton("For Manager");
        this.btnManager.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.btnManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManagerSignGUI managerSignGUI = new ManagerSignGUI(getBank());
                frame.dispose();
                managerSignGUI.show();
            }
        });

        this.pnlCustomer = new JPanel(new BorderLayout());
        this.pnlManager = new JPanel(new BorderLayout());
        this.pnlCustomer.add(btnCustomer, BorderLayout.CENTER);
        this.pnlManager.add(btnManager, BorderLayout.CENTER);


        this.frame.add(pnlCustomer);
        this.frame.add(pnlManager);


    }

    public void show() {
        this.frame.setVisible(true);
    }

}
