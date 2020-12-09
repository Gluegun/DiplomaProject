package app.repos;

import app.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p from Post p order by p.listComments.size desc")
    Page<Post> findPostsSortedByComments(Pageable pageable);

    @Query("select p from Post p order by p.time desc ")
    Page<Post> findRecentPosts(Pageable pageable);

    @Query("select p from Post p order by p.time")
    Page<Post> findOldestPosts(Pageable pageable);

    @Query("select p from Post p where p.title LIKE %:query%")
    Page<Post> findByQuery(@Param("query") String query, Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE DATE(time) between ?1 and ?2", nativeQuery = true)
    Page<Post> findByTime(LocalDate from, LocalDate to, Pageable pageable);

}
