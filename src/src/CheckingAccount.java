import java.util.Map;

public class CheckingAccount extends Account {
    private AccountType accountType;
    private Loan loans;
    public CheckingAccount(Currency currency) {
        super(currency);
        accountType = AccountType.CHECHINGACCOUNT;
        loans = new Loan();
    }

    public void addOneLoan(Currency c) {
        loans.addLoan(c);
    }

    public void removeOneLoan(Currency c) {
        loans.removeLoan(c);
    }

    public boolean hasLoan() {
        return loans.hasLoan();
    }

    public Loan getLoans() {
        return loans;
    }

    public String loansToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------Loans-------\n");
        if(loans.hasLoan()) {
            sb.append(loans.toString());
        } else {
            sb.append("No loans\n");
        }
        return sb.toString();
    }

    @Override
    public String getAccountType() {
        return this.accountType.toString();
    }
}
