package scorp.model;

public class Post implements Comparable<Post>{

    private int id;
    private String description;
    private User owner;
    private String image;
    private int createdAt;
    private boolean liked;

    public Post(int id, String description, User owner, String image, int createdAt, boolean liked) {
        this.id = id;
        this.description = description;
        this.owner = owner;
        this.image = image;
        this.createdAt = createdAt;
        this.liked = liked;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", liked=" + liked +
                '}';
    }

    @Override
    public int compareTo(Post o) {
        int compCreatedAt = Integer.compare(o.getCreatedAt(), getCreatedAt());
        if(compCreatedAt != 0) {
            return compCreatedAt;
        }
        int compPostId = Integer.compare(o.getId(), getId());
        return compPostId;
    }
}
