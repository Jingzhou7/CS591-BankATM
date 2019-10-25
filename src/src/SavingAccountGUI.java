import javax.swing.*;

public class SavingAccountGUI extends ATMService {
    private JFrame frame;
    public SavingAccountGUI(Bank bank, Cache cache) {
        super(bank, cache);
    }

    @Override
    public void show() {
        this.frame.setVisible(true);
    }
}
