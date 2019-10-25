import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Bank is the core of the bankATM
 * it contains all customers, managers, profits and transactions
 * since profits and transactions belong to the bank, all "should-be" manager methods are implemented here in bank
 */
public class Bank {
    private HashMap<String, Customer> customers;
    private HashMap<String, Manager> managers;
    private HashMap<String, Customer> hasloans;
    private HashMap<CurrencyUnit, Currency> profits;
    private final double MINIMUM = 2;
    private ArrayList<Transaction> transactions;

    public Bank() {
        customers = new HashMap<String, Customer>();
        managers = new HashMap<String, Manager>();
        hasloans = new HashMap<String, Customer>();
        profits = new HashMap<CurrencyUnit, Currency>();
        transactions = new ArrayList<Transaction>();
        addTestUser();

    }

    public void addTestUser(){
        Customer c1 = new Customer("alice", "123");
        c1.openSavingAccount(new Currency(900, CurrencyUnit.USD));
        c1.openCheckingAccount(new Currency(5000, CurrencyUnit.CNY));

        Customer c2 = new Customer("bob", "234");
        c2.openSavingAccount(new Currency(10, CurrencyUnit.USD));
        c2.openCheckingAccount(new Currency(20, CurrencyUnit.GBP));

        Manager manager = new Manager("cpk", "cs591");

        customers.put(c1.getUserName(), c1);
        customers.put(c2.getUserName(), c2);
        managers.put(manager.getUserName(), manager);
    }

    public HashMap<String, Manager> getManagers() {
        return managers;
    }

    public void chargeServiceFee(Customer customer, AccountType accountType, Currency currency) {

        ServiceFee serviceFee = new ServiceFee(currency);
        //todo

    }
    public void addToProfit(Currency c) {
        CurrencyUnit unit = c.getUnit();
        if (this.profits.containsKey(unit)) {
            Currency tmp = this.profits.get(unit);
            this.profits.put(unit, tmp.add(c));
        } else {
            this.profits.put(unit, c);
        }
    }

    public void loseProfit(Currency c) {
        CurrencyUnit unit = c.getUnit();
        if (this.profits.containsKey(unit)) {
            Currency tmp = this.profits.get(unit);
            this.profits.put(unit, tmp.substract(c));
        } else {
            Currency tmp = new Currency(0, unit);
            this.profits.put(unit, tmp.substract(c));
        }
    }
    public boolean signIn(String userName, String pwd, UserType userType) {
        if(managers.size() == 0 && customers.size() == 0) {
            System.out.println("No user");
            return false;
        }
        //return true if signIn successfully. false otherwise
        System.out.println(userName + "   " + pwd + "   " + userType.toString());
        if(userType == UserType.MANAGER) {
            if(this.managers.containsKey(userName)) {
                return this.managers.get(userName).getPwd().equals(pwd);
            } else {
                System.out.println("No UserName found");
            }
        } else {
            //userType is CUSTOMER
            if(this.customers.containsKey(userName)) {
                System.out.println("2");
                return this.customers.get(userName).getPwd().equals(pwd);
            }
        }
        return false;
    }

    public boolean signUp(String userName, String pwd, UserType userType) {
        //return true if signUp successfully. false if duplicated userName exists
        if(userType == UserType.MANAGER) {
            if (this.managers.containsKey(userName)) {
                return false;
            } else {
                this.managers.put(userName, new Manager(userName, pwd));
                System.out.println("New Manager Added");
                return true;
            }
        } else {
            //userType is CUSTOMER
            if (this.customers.containsKey(userName)) {
                return false;
            } else {
                this.customers.put(userName, new Customer(userName, pwd));
                System.out.println("New Customer Added");
                return true;
            }
        }
    }

    //-----------------
    //CUSTOMER functions
    public boolean openAccount(Currency currency, AccountType accountType, Cache cache) {
        //service fee apply
        if(cache.getUserType() == UserType.CUSTOMER) {
            if (accountType == AccountType.CHECHINGACCOUNT) {

                if(openCheckingAccount(currency, cache)){
                    chargeServiceFee(customers.get(cache.getUserName()), accountType, currency);
                        return true;
                }

            } else {
                //else account type is SAVINGACCOUNT
                if(openSavingAccount(currency, cache)) {
                    chargeServiceFee(customers.get(cache.getUserName()), accountType, currency);
                }
                //chargeServiceFee();
            }
        }
        return false;
    }

    private boolean openCheckingAccount(Currency currency, Cache cache) {
        if(cache.getUserType() == UserType.CUSTOMER) {
            Customer tmp = customers.get(cache.getUserName());
            return tmp.openCheckingAccount(currency);
        }
        return false;
    }

    private boolean openSavingAccount(Currency currency, Cache cache) {
        if(cache.getUserType() == UserType.CUSTOMER) {
            Customer tmp = customers.get(cache.getUserName());
            return tmp.openSavingAccount(currency);
        }
        return false;
    }

    public boolean deposit(Currency currency, AccountType accountType, Cache cache) {
        if(cache.getUserType() == UserType.CUSTOMER) {
            Customer tmp = customers.get(cache.getUserName());
            return tmp.deposit(currency, accountType);
        }
        return false;
    }

    public boolean withdraw(Currency currency, AccountType accountType, Cache cache) {
        if(cache.getUserType() == UserType.CUSTOMER) {
            Customer tmp = customers.get(cache.getUserName());
            return tmp.withdraw(currency, accountType);
        }
        return false;
    }

    public boolean transfer(Currency currency, Cache cache, AccountType from, AccountType to) {
        boolean ret = false;
        if (cache.getUserType().equals(UserType.CUSTOMER)) {
            Customer customer = customers.get(cache.getUserName());
            if (customer.has(from)) {
                if(from == AccountType.CHECHINGACCOUNT) {
                    ret = customer.getCheckingAccount().transfer(currency);
                } else {
                    ret = customer.getSavingAccount().transfer(currency);
                }
                if (ret) {
                    ret = transfer(currency, cache, from, to);
                    if (!ret) {
                        deposit(currency, from, cache);
                    }
                }
            }
            customer.addTransaction(new Transaction(
                    TransactionType.TRANSFER, cache.getUserName(), cache.getUserName(), currency, new Date(), ret));

        } else {
            System.out.println("Bank transfer NoticeL Transfer failed ");
        }
        return ret;
    }

    public boolean requestLoan(Currency currency, Cache cache) {
        boolean ret = false;
        if (cache.getUserType() == UserType.CUSTOMER) {
            Customer customer = customers.get(cache.getUserName());
            if (customer.has(AccountType.CHECHINGACCOUNT))
                ret = customer.requestLoan(currency);

            if (ret && this.hasloans.containsKey(cache.getUserName())) this.hasloans.put(cache.getUserName(), customer);
            customer.addTransaction(new Transaction(TransactionType.REQUEST_LOAN, cache.getUserName(), cache.getUserName(), currency, new Date(), ret));
        } else {
            System.out.println("Bank RequestLoan Notice: Loan request failed");
        }
        return false;
    }

    public boolean payLoan(Currency currency, Cache cache) {
        boolean ret = false;
        if (cache.getUserType() == UserType.CUSTOMER) {
            Customer customer = customers.get(cache.getUserName());
            if (customer.has(AccountType.CHECHINGACCOUNT)) {
                ret = customer.payLoan(currency);
            }
            customer.addTransaction(new Transaction(
                    TransactionType.PAY_LOAN, cache.getUserName(), cache.getUserName(), currency, new Date(), ret));
        } else {
            System.out.println("Bank Notice: Payback Loan failed");
        }

        return ret;
    }


    //------------------
    //MANAGER functions
    public String collectReport(Cache cache) {
        StringBuilder sb = new StringBuilder();
        if (cache.getUserType() == UserType.MANAGER) {
            sb.append("-------Manager's Daily Report-------\n");
            sb.append("TransactionType, CurrencyUnit, Amount \n");
            //for all customers
            for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                ArrayList<Transaction> trans = entry.getValue().getTransactions();
                //for all transactions
                for (int i = 0; i < trans.size(); i++) {
                    Transaction t = trans.get(i);
                    sb.append(t.toString());
                }
            }
        }
        return sb.toString();
    }

    //check-up on a specific client
    public String checkUpCustomer(String userName, Cache cache) {
        StringBuilder sb = new StringBuilder();
        if (cache.getUserType() == UserType.MANAGER && customers.containsKey(userName)) {
            Customer tmp = customers.get(userName);
            sb.append("-------Clients info-------\n");
            sb.append("Customer String: ").append(tmp.getUserName()).append("\n");
            sb.append("Account Info: \n").append(tmp.accountToString()).append("\n");
        } else {
            sb.append("Bank Notice: No such customer found");
        }
        return sb.toString();
    }
    //check-up on the poor ones who owe money
    public String checkUpThePoor(Cache cache) {
        StringBuilder sb = new StringBuilder();
        if (cache.getUserType() == UserType.MANAGER) {
            sb.append("-------Clients in Debt-------\n");
            for(Map.Entry<String, Customer> entry : hasloans.entrySet()) {
                Customer tmp = entry.getValue();
                sb.append("Customer String: ").append(tmp.getUserName()).append("\n");
                sb.append("Account Info: \n").append(tmp.accountToString()).append("\n");
            }
        }
        return sb.toString();
    }
    //update profit means after one interest charge cycle
    //user clicking on this button means charge interest once
    public String updateProfit(Cache cache) {
        StringBuilder sb = new StringBuilder();
        if (cache.getUserType() == UserType.MANAGER) {
            chargeInterest();
            sb.append("-------Profits-------\n");
            for(Map.Entry<CurrencyUnit, Currency> entry : profits.entrySet()) {
                CurrencyUnit unit = entry.getKey();
                double amount = entry.getValue().getAmount();
                sb.append(unit.toString()).append(": ").append(amount).append("\n");

            }
        }
        return sb.toString();
    }

    private void chargeInterest() {
        //this stands for one interest charge cycle
        //includes pay interests to customers saving accounts and collect interest from customers loans
        //pay interests to customers saving accounts
        for(Map.Entry<String, Customer> entry : customers.entrySet()) {
            Customer customer = entry.getValue();
            payInterest(customer);
        }
        //collect interests from hasLoans
        for(Map.Entry<String, Customer> entry : hasloans.entrySet()) {
            Customer customer = entry.getValue();
            collectInterest(customer);
        }

    }

    private void payInterest(Customer customer) {
        //pay interest to customers saving account
        //but only to those with high balance
        AccountType accountType = AccountType.SAVINGACCOUNT;
        if(customer.hasHighBalance()) {
            Currency interest = Interest.getInterestForSaving(customer);
            customer.deposit(interest, accountType);
            loseProfit(interest);
        }
    }

    private void collectInterest(Customer customer) {
        //collect interest from loans in the checking account
        AccountType accountType = AccountType.CHECHINGACCOUNT;
        HashMap<CurrencyUnit, Currency> interest = Interest.getInterestForLoan(customer);
        customer.payLoanInterest(interest, accountType);
        for(Map.Entry<CurrencyUnit, Currency> entry : interest.entrySet()) {
            Currency currency = entry.getValue();
            addToProfit(currency);
        }
    }

}
