package scorp.controller;

import scorp.model.Post;
import scorp.repository.PostRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostController {

    public List<Post> getPosts(int userId, List<Integer> postIds) {
        List<Post> result = PostRepository.getInstance().getPostsQuery(userId, postIds);
        if(result.isEmpty()) {
            return null;
        } else {
            return result;
        }
    }

    public List<Post> mergePosts(List<List<Post>> allPosts) {
        List<Post> mergePosts = new ArrayList<>();

        for (int i=allPosts.size() - 1; i>=0; i--) {
            for (int j= allPosts.get(i).size() - 1; j>=0; j--) {
                mergePosts.add(allPosts.get(i).get(j));
            }
        }

        Collections.sort(mergePosts, Post::compareTo);

        return mergePosts;
    }

}
