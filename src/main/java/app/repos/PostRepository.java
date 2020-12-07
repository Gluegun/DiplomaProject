package app.repos;

import app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p from Post p order by p.listComments.size desc")
    List<Post> findPostsAndSortedByComments();

    @Query("select p from Post p order by p.time desc ")
    List<Post> findPostsAndSortByRecentDate();

    @Query("select p from Post p order by p.time")
    List<Post> findOldestPosts();


}
