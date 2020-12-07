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
import java.util.*;
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
                posts = getMostPopularPosts(page);
                break;
            case "recent":
                posts = getRecentPosts(page);
                break;
            case "best":
                posts = getMostLikedPosts(page);
                break;
            case "early":
                posts = getOldestPosts(page);
                break;

            default:
                new ArrayList<>();

        }

        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .collect(Collectors.toList());

        return mapper.toGeneralPostDto(posts);

    }

    private List<Post> getMostPopularPosts(Pageable pageable) {


        Page<Post> all = postRepository.findPostsSortedByComments(pageable);

        List<Post> posts = all.toList();

        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .collect(Collectors.toList());

        return posts;

    }

    private List<Post> getRecentPosts(Pageable pageable) {


        Page<Post> recentPosts = postRepository.findRecentPosts(pageable);

        List<Post> posts = recentPosts.toList();

        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .collect(Collectors.toList());

        return posts;

    }

    private List<Post> getOldestPosts(Pageable pageable) {

        Page<Post> oldestPosts = postRepository.findOldestPosts(pageable);

        List<Post> posts = oldestPosts.toList();

        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .collect(Collectors.toList());

        return posts;

    }

    private List<Post> getMostLikedPosts(Pageable pageable) {

        //get page
        Page<Post> allPosts = postRepository.findAll(pageable);

        //convert page to list
        List<Post> posts = allPosts.toList();

        //create collection of suitable class
        List<PostForLikes> sortedByLikes = new ArrayList<>();

        //filter posts with required mod. status and activity
        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .collect(Collectors.toList());


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
