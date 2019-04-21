package theblueorb.dev.com.dependencyinjectionwithdagger2;

import androidx.annotation.Nullable;

public class Member {
    private String username;
    private String password;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
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

    public boolean equals(Member obj) {
        return this.username.equals(obj.username)&&this.password.equals(obj.password);

    }
}
