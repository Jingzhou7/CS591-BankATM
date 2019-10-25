public enum CurrencyUnit {
    USD(1.0),
    CNY(7.077471),
    EUR(0.907919),
    GBP(0.792344),
    HKD(7.844808),
    CAD(1.323527),
    JPY(108.231132);


    private final double rate;
    CurrencyUnit(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public static double getExchangeRate(CurrencyUnit unit, CurrencyUnit toUnit) {
        return 0.0;
    }
}
