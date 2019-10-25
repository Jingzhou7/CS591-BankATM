import javax.swing.*;

public class CheckingAccountGUI extends ATMService{
    private JFrame frame;
    public CheckingAccountGUI(Bank bank, Cache cache) {
        super(bank, cache);
    }

    @Override
    public void show() {
        this.frame.setVisible(true);
    }
}
