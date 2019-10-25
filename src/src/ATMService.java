/**
 * this abstract class is the BankATM after user has signed in.
 * it is designed to be extended by CustomerGUI and ManagerGUI
 * after login the ATM keeps track of the information stored in the cache
 *
 */
public abstract class ATMService extends BankATM {

    private Cache cache;

    public ATMService(Bank bank, Cache cache) {
        super(bank);
        this.cache = cache;
    }

    public Cache getCache() {
        return cache;
    }

    //make sure CustomerGUI and ManagerGUI have implemented a function show()
    abstract public void show();
}
