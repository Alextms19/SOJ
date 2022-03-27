package dataStructures;


public abstract class User {
    protected String username;
    protected String password;
    protected String CNP;
    private boolean flag;
    public User(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setCNP(String CNP)  {

        this.CNP = CNP;
    }

    public String getCNP() {
        return CNP;
    }


}