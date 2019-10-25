public class Main {
    public static void main(String Args[]) {
        //main initialize the core for the bank. pass it to the starting frame to continue using the bankATM
        Bank bank = new Bank();
        PickRollGUI pickRollGUI = new PickRollGUI(bank);
        pickRollGUI.show();
    }
}

