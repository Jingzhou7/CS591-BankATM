public class BankATM {
    //BankATM contains a Bank core inside. it can provide all functions of the Bank
    //All other ATM related services must extends BankATM

    private Bank bank;
    public BankATM(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}
