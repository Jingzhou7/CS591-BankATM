import java.util.HashMap;
import java.util.Map;

public class Loan {
    //Loan is a HashMap containing all loans
    private HashMap<CurrencyUnit, Currency> loans;
    public Loan() {
        loans = new HashMap<CurrencyUnit, Currency>();

    }

    public HashMap<CurrencyUnit, Currency> getLoans() {
        return loans;
    }


    public void addLoan(Currency c) {
        CurrencyUnit unit = c.getUnit();
        if(loans.containsKey(unit)) {
            Currency newCurrency = c.add(loans.get(unit));
            loans.put(unit, newCurrency);
        } else {
            loans.put(unit, c);
        }
    }

    public void removeLoan(Currency c) {
        CurrencyUnit unit = c.getUnit();
        if(!loans.containsKey(unit)) {
            System.out.println("Loan notice: No loan in such currency");
        } else {
            double loanAmount = loans.get(unit).getAmount();
            if(c.getAmount() >= loanAmount) {
                //manager would be glad if loans are payed back more than the amount required
                loans.remove(unit);
            } else {
                //not enough to pay back the loan
                Currency newCurrency = loans.get(unit).substract(c);
                loans.put(unit, newCurrency);
            }
        }
    }

    public boolean hasLoan() {
        //check if the loans HashMap has entries
        if(loans.size() > 0) return true;
        else return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<CurrencyUnit, Currency> entry : loans.entrySet()) {
            sb = sb.append("--------------Loan----------------\n").append(entry.getKey().toString()).append(": ").append(entry.getValue().getAmount()).append("\n");
        }
        return sb.toString();
    }
}
