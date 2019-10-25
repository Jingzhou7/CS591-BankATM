public enum UserType {
    MANAGER("Manager"),
    CUSTOMER("Customer");
    private final String userText;
    UserType(String userText) {
        this.userText = userText;
    }

    @Override
    public String toString() {
        return userText;
    }
}
