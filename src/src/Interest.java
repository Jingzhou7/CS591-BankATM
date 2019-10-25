import java.util.HashMap;
import java.util.Map;

public class Interest {
    //Interest is only calculated once when requested
    //so no need to create an object Interest
    //use the static method to calculate the interest is enough
    private final static double INTEREST_RATE = 0.02;
    private final static double LOAN_RATE = 0.1;
    private final static CurrencyUnit DEFAULT_UNIT = CurrencyUnit.USD;
    public static Currency getInterestForSaving(Customer customer) {
        //interest is only payed to customers with high balance in the saving account.
        //default interest CurrencyUnit is USD
        if(customer.hasHighBalance()) {
            double newAmount = customer.getSavingAccount().totalCurrencyInUnit(CurrencyUnit.USD).getAmount() * INTEREST_RATE;
            return new Currency(newAmount, DEFAULT_UNIT);
        }
        return new Currency(-1, DEFAULT_UNIT);
    }

    /**
     * this method caculates interests for loans. each currencyUnit has their own interest calculated
     * @param customer
     * @return loans' interest hashtable
     */
    public static HashMap<CurrencyUnit, Currency> getInterestForLoan(Customer customer) {
        Loan loan = customer.getCheckingAccount().getLoans();
        HashMap<CurrencyUnit, Currency> loans = loan.getLoans();
        HashMap<CurrencyUnit, Currency> loanInterest = new HashMap<>();
        for(Map.Entry<CurrencyUnit, Currency> entry : loans.entrySet()) {
            CurrencyUnit unit = entry.getKey();
            Currency c = entry.getValue();
            double newAmount = c.getAmount() * LOAN_RATE;
            Currency interest = new Currency(newAmount, unit);
            loanInterest.put(unit, interest);
        }
        return loanInterest;
    }

}
