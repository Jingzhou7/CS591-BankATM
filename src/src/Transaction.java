import java.util.Date;

/**
 * this class is used to keep details of each transaction of any type
 * it is designed to prepare the report for bank manager
 */
public class Transaction {
    private TransactionType transactionType;
    private String from;
    private String to;
    private Currency currency;
    private Date date;
    private boolean processed;

    public Transaction(TransactionType transactionType, String from, String to, Currency currency, Date date, boolean processed){
        this.transactionType = transactionType;
        this.from = from;
        this.to = to;
        this.currency = currency;
        this.date = date;
        this.processed = processed;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Date getDate() {
        return date;
    }
    public boolean getProcessed() {
        return this.processed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTransactionType().toString()).append(", ");
        sb.append(getCurrency().getUnit().toString()).append(", ");
        sb.append(getCurrency().getAmount()).append(", ");
        sb.append(getFrom()).append(", ");
        sb.append(getTo()).append(", ");
        sb.append(getProcessed()).append(" \n");
        return sb.toString();
    }
}
