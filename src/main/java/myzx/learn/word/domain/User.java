package myzx.learn.word.domain;

public class User {

    private String username;
    private String password;
    private String email;
    private String level;
    private int num;

    public int getHavelearnt() {
        return havelearnt;
    }

    public void setHavelearnt(int havelearnt) {
        this.havelearnt = havelearnt;
    }

    private int havelearnt;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
