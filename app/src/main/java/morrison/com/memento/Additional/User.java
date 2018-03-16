package morrison.com.memento.Additional;

/**
 * Created by Michael on 12.11.2017.
 */

public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String avatar;
    private long birth;
    private String eventDescription;
    private String friends;
    private String blackList;

    private static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() { return password;}

    public void setPassword(String password) {this.password = password;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birth=" + birth +
                ", eventDescription='" + eventDescription + '\'' +
                ", friends='" + friends + '\'' +
                ", blackList='" + blackList + '\'' +
                '}';
    }
}
