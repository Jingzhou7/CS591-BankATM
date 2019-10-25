public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdraw"),
    TRANSFER("Transfer"),
    REQUEST_LOAN("Loan Request"),
    PAY_LOAN("Loan Payback");
    private final String transactionText;
    TransactionType(String transactionText) {
        this.transactionText = transactionText;
    }

    @Override
    public String toString() {
        return transactionText;
    }
}
