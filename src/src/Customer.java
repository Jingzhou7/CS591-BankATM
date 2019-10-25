import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends User {
    //each Customer can only have one CheckingAccount and one SavingAccount

    private CheckingAccount checkingAccount;
    private SavingAccount savingAccount;
    private ArrayList<Transaction> transactions;
    private UserType userType;
    private static final Currency HIGH_BALANCE_LINE = new Currency(1000, CurrencyUnit.USD);

    public Customer(String name, String pwd) {
        super(name, pwd);
        userType = UserType.CUSTOMER;
        checkingAccount = null;
        savingAccount = null;
        transactions = new ArrayList<Transaction>();
    }

    public boolean hasHighBalance() {
        if(this.savingAccount != null) {
            if(savingAccount.totalCurrencyInUnit(CurrencyUnit.USD).compareTo(HIGH_BALANCE_LINE) >= 0) {
                //savingAccount has total currency greater or equally to the HIGH_BALANCE_LINE
                return true;
            }
        }
        return false;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public boolean openSavingAccount(Currency currency) {
        if (savingAccount == null) {
            savingAccount = new SavingAccount(currency);
            return true;
        }
        return false;
    }

    public boolean openCheckingAccount(Currency currency) {
        if (checkingAccount == null) {
            checkingAccount = new CheckingAccount(currency);
            return true;
        }
        return false;
    }

    public boolean deposit(Currency currency, AccountType accountType) {
        if(accountType == AccountType.SAVINGACCOUNT) {
            return this.savingAccount.deposit(currency);

        } {
            //CheckingAccount
            return this.checkingAccount.deposit(currency);
        }
    }

    public boolean withdraw(Currency currency, AccountType accountType) {
        if(accountType == AccountType.SAVINGACCOUNT) {
            return this.savingAccount.withdraw(currency);

        } {
            //CheckingAccount
            return this.checkingAccount.withdraw(currency);
        }
    }

    public boolean requestLoan(Currency currency) {
        boolean res = false;
        if (checkingAccount != null) {
            checkingAccount.addOneLoan(currency);
            res = deposit(currency, AccountType.CHECHINGACCOUNT);

        } else {
            System.out.println("Customer requestLoan Notice: no Checking Account");
        }
        return res;
    }

    public boolean payLoan(Currency currency) {
        boolean ret = false;
        if (checkingAccount != null) {
            checkingAccount.removeOneLoan(currency);
            return true;
        } else {
            System.out.println("CheckingAccount Notice: Pay Back Loan failed");
        }
        return ret;
    }




    public boolean has(AccountType accountType) {
        if(accountType == AccountType.CHECHINGACCOUNT) {
            return checkingAccount != null;
        } else {
            //saving account
            return savingAccount != null;
        }
    }

    public boolean payLoanInterest(HashMap<CurrencyUnit, Currency> interest, AccountType accountType) {
        return false;
    }

    private String checkingAccountToString() {
        StringBuilder sb = new StringBuilder();
        if (checkingAccount != null) {
            sb.append("**** Checking Account Info **** \n").append(checkingAccount.savingsToString()).append("\n").append(checkingAccount.getLoans()).append("\n");
        } else {
            sb.append("No checking account\n");
        }
        return sb.toString();
    }
    private String savingAccountToString() {
        StringBuilder sb = new StringBuilder();
        if (savingAccount != null) {
            sb.append("**** Saving Account Info **** \n").append(savingAccount.savingsToString()).append("\n");
        } else {
            sb.append("No saving account\n");
        }
        return sb.toString();
    }
    public String accountToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(checkingAccountToString()).append(savingAccountToString());
        return sb.toString();
    }
    @Override
    public String getUserType() {
        return this.userType.toString();
    }
}
