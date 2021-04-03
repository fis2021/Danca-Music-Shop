package src.test;

public final class UserSessionTest {
/*
    public static UserSession instance;

    private String userName;
    private String userType;

    public UserSession(String userName, String userType) {
        this.userName = userName;
        this.userType = userType;
    }

    public static UserSession getInstace(String userName, String userType) {
        if(instance == null) {
            instance = new UserSession(userName, userType);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public void cleanUserSession() {
        userName = "";// or null
        userType = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", userType=" + userType +
                '}';
    }
    */
	public static String userName;
	public static String userType;
}