package narsis.xiirpl2.appfilosofi.model;

import java.util.List;

public class LoginResponse {
    private String status,message;
    private UserModel user;
    List<UserModel> userlist;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<UserModel> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserModel> userlist) {
        this.userlist = userlist;
    }
}
