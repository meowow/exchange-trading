package vovandev.exchangetrading.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String username;

    private String password;
    private int active;
    private String roles = "";
    /*
    * CSV
     */
    private String permissions = "";

    public User(String username, String password, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    protected User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public List<String> getRoleList() {
        return roles.length() > 0 ?  List.of(roles.split(",")) : List.of();
    }

    public List<String> getPermissionList() {
        return permissions.length() > 0 ?  List.of(permissions.split(",")) : List.of();
    }
}
