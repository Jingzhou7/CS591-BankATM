public class ServiceFee {
    //all services has service fee of at least 2 usd
    private Currency serviceFee;
    private static final double SERVICE_FEE_RATE = 0.01;
    private static final Currency MINIMUM = new Currency(2, CurrencyUnit.USD);
    public ServiceFee(Currency c) {
        //serviceFee is based on the service.
        //services related to money has service fee proportional to the amount of money being serviced
        //there is a minimum service fee
        CurrencyUnit unit = c.getUnit();
        double newAmount = c.getAmount() * SERVICE_FEE_RATE;
        Currency sf = new Currency(newAmount, unit);
        if(sf.compareTo(MINIMUM) == -1) {
            serviceFee = MINIMUM;
        } else {
            serviceFee = sf;
        }

    }
}
