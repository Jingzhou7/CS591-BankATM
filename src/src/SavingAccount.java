public class SavingAccount extends Account {
    private AccountType accountType = AccountType.SAVINGACCOUNT;
    public SavingAccount(Currency currency) {
        super(currency);
    }

    @Override
    public String getAccountType() {
        return this.accountType.toString();
    }


}
