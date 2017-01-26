package by.dorohovich.site.entity;

/**
 * Created by User on 08.12.2016.
 */
public class User extends Entity<Integer> {

    private String login;
    private String password;
    private String email;
    private Role role;
    private int rating;

    public User(int id, String login, String password, Role role, int rating) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.rating = rating;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = Role.USER;
        this.rating = 0;
    }

    public User(String login, String password, Role role, int rating) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.rating = rating;
    }

    public User(Integer id, String login, String password, String email, Role role, int rating) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.rating = rating;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.role = Role.USER;
        this.rating = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isAdmin()
    {
        return Role.ADMIN == role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email'" + email + '\'' +
                ", ROLE=" + role +
                ", rating=" + rating +
                '}';
    }


}
