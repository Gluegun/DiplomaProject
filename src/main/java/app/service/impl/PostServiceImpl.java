package app.service.impl;

import app.dto.GeneralPostDto;
import app.dto.PostForLikes;
import app.mapper.Mapper;
import app.model.Post;
import app.model.PostVote;
import app.model.enums.ModerationStatus;
import app.repos.PostRepository;
import app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final Mapper mapper;
    private final PostRepository postRepository;


    @Override
    public GeneralPostDto getSortedAndPagedPosts(int offset, int limit, String mode) {

        PageRequest page = PageRequest.of(offset, limit);

        List<Post> posts = new ArrayList<>();

        switch (mode) {

            case "popular":
                posts = postRepository.findPostsSortedByComments(page).toList();
                break;
            case "recent":
                posts = postRepository.findRecentPosts(page).toList();
                break;
            case "best":
                posts = getMostLikedPosts(page);
                break;
            case "early":
                posts = postRepository.findOldestPosts(page).toList();
                break;

            default:
                new ArrayList<>();

        }

        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .filter(post -> post.getTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        return mapper.toGeneralPostDto(posts);

    }

    @Override
    public GeneralPostDto findPostsByQuery(String query, int offset, int limit) {

        if (query.isEmpty()) {
            List<Post> all = postRepository.findAll();
            List<Post> collect = all.stream()
                    .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                    .filter(post -> post.getIsActive() == 1)
                    .filter(post -> post.getTime().isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());
            return mapper.toGeneralPostDto(collect);
        }


        PageRequest page = PageRequest.of(offset, limit);

        List<Post> posts = postRepository.findByQuery(query, page).toList();

        return mapper.toGeneralPostDto(posts);


    }

    private List<Post> getMostLikedPosts(Pageable pageable) {

        //get page
        Page<Post> allPosts = postRepository.findAll(pageable);

        //convert page to list
        List<Post> posts = allPosts.toList();

        //create collection of suitable class
        List<PostForLikes> sortedByLikes = new ArrayList<>();

        //creating and fill suitable collection
        for (Post post : posts) {

            PostForLikes postForLikes = new PostForLikes();
            postForLikes.setPost(post);
            postForLikes.setLikesAmount(likeCount(post.getListVotes()));

            sortedByLikes.add(postForLikes);

        }

        //sort it
        Collections.sort(sortedByLikes);

        List<Post> result = new ArrayList<>();

        for (PostForLikes sortedByLike : sortedByLikes) {
            Post post = mapper.toPost(sortedByLike);
            result.add(post);
        }

        return result;

    }

    private int likeCount(List<PostVote> votes) {

        int likes = 0;
        for (PostVote vote : votes) {
            if (vote.getValue() == 1) {
                likes++;
            }
        }
        return likes;
    }

}
