import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * this GUI is for manager after signIn
 * any functions that a manager can perform is on this page
 *
 */
public class ManagerGUI extends ATMService {
    private JFrame frame;
    private JLabel lblMessage;
    private JPanel pane;
    private JPanel pane1;
    private JTextField tfCheckUp;
    private JButton btnCheckUp;
    private JButton btnCheckUpPoor;
    private JButton btnCollectReport;
    private JButton btnUpdateProfit;
    private JButton btnBack;

    public ManagerGUI(Bank bank, Cache cache) {
        super(bank, cache);
        this.frame = new JFrame("My Fancy Bank [Manager]");
        this.frame.setSize(500, 400);
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

        this.lblMessage = new JLabel();
        this.lblMessage.setText("");
        this.lblMessage.setVisible(false);


        this.tfCheckUp = new JTextField("Client's UserName");
//        this.pane1.add(tfCheckUp);
        //Check up specific customer button
        this.btnCheckUp = new JButton("CheckUp Client");
        this.btnCheckUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String accountInfo = getBank().checkUpCustomer(tfCheckUp.getText(), getCache());
                printMessage(accountInfo, 13);
            }
        });
//        this.pane1.add(btnCheckUp);

        //Check up the poor button
        this.btnCheckUpPoor = new JButton("CheckUp Poor");
        this.btnCheckUpPoor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String accountInfo = getBank().checkUpThePoor(getCache());
                printMessage(accountInfo, 13);
            }
        });
//        this.pane1.add(btnCheckUpPoor);

        //Collect report button
        this.btnCollectReport = new JButton("Collect Report");
        this.btnCollectReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String report = getBank().collectReport(getCache());
                printMessage(report, 13);
            }
        });
//        this.pane1.add(btnCollectReport);

        //Update profit button
        this.btnUpdateProfit = new JButton("Update Profit");
        this.btnUpdateProfit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String profit = getBank().updateProfit(getCache());
                printMessage(profit, 13);
            }
        });
//        this.pane1.add(btnUpdateProfit);

        //Back to previous button
        this.btnBack = new JButton("Log out");
        this.btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManagerSignGUI managerSignGUI = new ManagerSignGUI(getBank());
                frame.dispose();
                managerSignGUI.show();
            }
        });
//        this.pane1.add(btnBack);
//        this.pane1.add(lblMessage);
//
//        this.frame.add(pane1, BorderLayout.CENTER);
        this.pane = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // First Column

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        this.pane.add(btnBack, gc);


        gc.gridx = 0;
        gc.gridy = 1;
        this.pane.add(btnCheckUp, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        this.pane.add(btnCheckUpPoor, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        this.pane.add(btnUpdateProfit, gc);
        // Second Column



        gc.gridx = 1;
        gc.gridy = 1;
        this.pane.add(tfCheckUp, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        this.pane.add(btnCollectReport, gc);


//        this.pane.add(lblMessage, gc);
        this.pane1 = new JPanel();
        this.pane.setBackground(new Color(50,115,220,30));
        this.pane1.setBackground(new Color(50,115,220,60));
        this.pane1.add(lblMessage);
        this.frame.setLayout(new GridLayout(1,2));
        this.frame.add(pane);
        this.frame.add(pane1);
    }

    //this function is found online to break up a long string and print it in a label.
    //using html style. Text in the label would follow the html rules first
    private void printMessage(String str, int fontsize) {
        lblMessage.setText("");
        Font messageFont = new Font("Verdana",Font.BOLD, fontsize);
        lblMessage.setFont(messageFont);
        String html_head = "<html><body><p>";
        String html_end = "</p></body></html>";
        String res = html_head + str.replaceAll("\n", "<br/>") + html_end;
        lblMessage.setText(res);
        lblMessage.setVisible(true);
    }


    @Override
    public void show() {
        this.frame.setVisible(true);
    }
}
