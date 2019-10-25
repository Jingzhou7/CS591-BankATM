import java.awt.*;

public enum AccountType {
    SAVINGACCOUNT("Saving Account"),
    CHECHINGACCOUNT("Checking Account");
    private final String accountText;
    AccountType(String accountText) {
        this.accountText = accountText;
    }

    @Override
    public String toString() {
        return accountText;
    }
}
