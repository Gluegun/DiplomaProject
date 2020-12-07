package app.repos;

import app.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p from Post p order by p.listComments.size desc")
    Page<Post> findPostsSortedByComments(Pageable pageable);

    @Query("select p from Post p order by p.time desc ")
    Page<Post> findRecentPosts(Pageable pageable);

    @Query("select p from Post p order by p.time")
    Page<Post> findOldestPosts(Pageable pageable);


}
