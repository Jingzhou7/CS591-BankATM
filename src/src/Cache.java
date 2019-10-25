public class Cache {
    private String userName;
    private String pwd;
    private UserType userType;

    public Cache(String userName, String pwd, UserType userType) {
        this.userName = userName;
        this.pwd = pwd;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPwd() {
        return pwd;
    }
}
