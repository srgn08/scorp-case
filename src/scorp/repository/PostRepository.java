package scorp.repository;

import scorp.model.Post;
import scorp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {

    private static final PostRepository instance = new PostRepository();
    private static Connection connection = null;

    private final static String url = "jdbc:postgresql://localhost:5432/scorp?currentSchema=public";
    private final static String user = "postgres";
    private final static String password = "";

    //private constructor to avoid client applications to use constructor
    private PostRepository(){}

    public static PostRepository getInstance(){
        return instance;
    }


    public Connection connectionDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public List<Post> getPostsQuery(int userId, List<Integer> postIds) {
        List<Post> posts = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Integer i : postIds){
            sb.append(i+",");
        }
        sb.deleteCharAt(sb.length() -1);
        sb.append(")");

        String sql = "SELECT * FROM post WHERE id IN " + sb;

        try {
            if (connection == null){
                connection = connectionDB();
            }

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while ( rs.next() ) {
                int postId = rs.getInt(1);
                String description = rs.getString(2);
                int userIdForPost  = rs.getInt(5);
                String image = rs.getString(3);
                int createdAt = rs.getInt(4); //değiöeyui unhutma


                User user = getUserForUserId(userId, userIdForPost);

                Post post = new Post();
                post.setId(postId);
                post.setDescription(description);
                post.setOwner(user);
                post.setImage(image);
                post.setCreatedAt(createdAt);
                post.setLiked(isPostIsLike(userId, postId));

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public User getUserForUserId(int userId, int userIdForPost) {

        User user = new User();

        String sql = "SELECT * FROM public.user WHERE id= ?;";

        try {
            if (connection == null){
                connection = connectionDB();
            }

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userIdForPost);
            ResultSet rs = st.executeQuery();

            while ( rs.next() ) {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setFullName(rs.getString(4));
                user.setProfilePicture(rs.getString(5));
                user.setFollowed(isUserFollower(userId,userIdForPost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isPostIsLike(int userId, int postId) {

        boolean result=false;
        String sql = "SELECT * FROM public.like WHERE user_id = ? and post_id=?;";

        try {
            if (connection == null){
                connection = connectionDB();
            }

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, postId);
            ResultSet rs = st.executeQuery();

            if ( rs.next() ) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isUserFollower(int userId, int userIdForPost) {

        boolean result=false;

        String sql = "SELECT * FROM follow WHERE follower_id= ? and following_id=?;";

        try {
            if (connection == null){
                connection = connectionDB();
            }

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, userIdForPost);
            ResultSet rs = st.executeQuery();

            if ( rs.next() ) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
