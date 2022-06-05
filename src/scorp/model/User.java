package scorp.model;

public class User {

    private int id;
    private String username;
    private String fullName;
    private String profilePicture;
    private boolean followed;


    public User(int id, String username, String fullName, String profilePicture, boolean followed) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
        this.followed = followed;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
