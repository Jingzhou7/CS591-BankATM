public class Manager extends User {
    //Manager is simply a user with a MANAGER's clearance
    //profits and reports do not belong to a single bank manager. they belong to the bank.
    private UserType userType;
    public Manager(String name, String pwd) {
        super(name, pwd);
        userType = UserType.MANAGER;
    }


    @Override
    public String getUserType() {
        return this.userType.toString();
    }
}
