import java.util.HashMap;
import java.util.Map;

/**
 * This Account is an abstract class which is extended by CheckingAccount and SavingAccount.
 * Both accounts have some methods in common, like deposit in, transfer out
 *
 */
public abstract class Account {
    protected Currency currency;
    protected CurrencyUnit currencyUnit;
    protected HashMap<CurrencyUnit, Currency> allCurrencyBalance;  //this is a map showing how much money the user have in each currency

    public Account(Currency currency) {
        this.currency = currency;
        this.currencyUnit = currency.getUnit();
        allCurrencyBalance = new HashMap<CurrencyUnit, Currency>();
    }

    public boolean deposit(Currency c) {
        if(c.getAmount() <= 0) {
            System.out.println("Account notice: Please deposit more than 0");
            return false;
        } else {
            CurrencyUnit unit = c.getUnit();
            if(allCurrencyBalance.containsKey(unit)) {
                Currency newCurrency = c.add(allCurrencyBalance.get(unit));
                allCurrencyBalance.put(unit, newCurrency);
            } else {
                allCurrencyBalance.put(unit, c);
            }
            return true;
        }
    }

    public boolean withdraw(Currency c) {
        //here I allow the account balance to be negative
        //future implement fine on negative balance
        if(c.getAmount() <= 0) {
            System.out.println("Account notice: Please withdraw more than 0");
            return false;
        } else {
            CurrencyUnit unit = c.getUnit();
            if(allCurrencyBalance.containsKey(unit)) {
                Currency newCurrency = allCurrencyBalance.get(unit).substract(c);
                allCurrencyBalance.put(unit, newCurrency);
            } else {
                Currency newCurrency = new Currency(0, unit).substract(c);
                allCurrencyBalance.put(unit, c);
            }
            return true;
        }
    }
    public boolean transfer(Currency c) {
        CurrencyUnit unit = c.getUnit();
        if(!allCurrencyBalance.containsKey(unit)) {
            System.out.println("Account notice: No such currency in the account");
            return false;
            //check if totalCurrency can afford, and ask if exchange to transfer
        } else {
            if(allCurrencyBalance.get(unit).getAmount() < c.getAmount() ) {

                System.out.println("Account notice: Not enough money in the account");
                return false;

            } else {
                Currency newCurrency = allCurrencyBalance.get(unit).substract(c);
                allCurrencyBalance.put(unit, newCurrency);
                return true;
            }
        }
    }

    public Currency totalCurrencyInUnit(CurrencyUnit unit) {
        //to be implemented to calculate the total value of the account in some preferred unit.
        //when transfer doesn't have enough money in one unit, it can check for currency exchange.
        //it can be used for bank manager to assess the loan amount.
        return new Currency(0, unit);

    }

    public HashMap<CurrencyUnit, Currency> getAllCurrencyBalance() {
        return allCurrencyBalance;
    }

    public String savingsToString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<CurrencyUnit, Currency> entry : allCurrencyBalance.entrySet()) {
            sb = sb.append(entry.getKey().toString()).append(": ").append(entry.getValue().getAmount()).append("\n");
        }
        return sb.toString();
    }

    public abstract String getAccountType();
    public void currencyExchange(CurrencyUnit unit) {
        //t0 be implemented to exchange currency in the account
    }
}
