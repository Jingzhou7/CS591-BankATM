public class Currency implements Comparable{
    private double amount;
    private CurrencyUnit unit;


    public Currency(double amount, CurrencyUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public CurrencyUnit getUnit() {
        return unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setUnit(CurrencyUnit unit) {
        this.unit = unit;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Currency add(Currency c) {
        //type check might be unnecessary if add if used carefully
        if (!c.getUnit().equals(this.getUnit())) {
            Currency tmp = new Currency(-1, CurrencyUnit.USD);
            return tmp;
        }
        double newAmount = c.getAmount() + this.getAmount();
        return new Currency(newAmount, this.getUnit());
    }

    public Currency substract(Currency c) {
        //type check might be unnecessary if add if used carefully
        if (!c.getUnit().equals(this.getUnit())) {
            Currency tmp = new Currency(-1, CurrencyUnit.USD);
            return tmp;
        }
        double newAmount = c.getAmount() - this.getAmount();
        return new Currency(newAmount, this.getUnit());
    }

    @Override
    public int compareTo(Object o) {
        Currency c = (Currency) o;
        if(this.getAmount() > c.getAmount()) return 1;
        else if(this.getAmount() == c.getAmount()) return 0;
        else return -1;
    }
}
