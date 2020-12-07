package app.service.impl;

import app.dto.GeneralPostDto;
import app.mapper.Mapper;
import app.model.Post;
import app.model.PostVote;
import app.model.enums.ModerationStatus;
import app.repos.PostRepository;
import app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final Mapper mapper;
    private final PostRepository postRepository;

    @Override
    public GeneralPostDto getPostsActiveAndAcceptedByModerator() {

        List<Post> allPostsFromDb = postRepository.findAll();

        return mapper.toGeneralPostDto(allPostsFromDb.stream()
                .filter(p -> p.getIsActive() == 1)
                .filter(p -> p.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .collect(Collectors.toList()));
    }

    @Override
    public GeneralPostDto getAllPosts() {

        return mapper.toGeneralPostDto(postRepository.findAll());

    }

    @Override
    public GeneralPostDto getMostDiscussedPosts() {

        return mapper.toGeneralPostDto(postRepository.findPostsAndSortedByComments()
                .stream()
                .filter(p -> p.getIsActive() == 1)
                .filter(p -> p.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .collect(Collectors.toList()));

    }

    @Override
    public GeneralPostDto getMostRecentPosts() {
        return mapper.toGeneralPostDto(postRepository.findPostsAndSortByRecentDate().stream()
                .filter(p -> p.getIsActive() == 1)
                .filter(p -> p.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .collect(Collectors.toList()));
    }

    @Override
    public GeneralPostDto getMostLikedPosts() {

        List<Post> posts = postRepository.findAll().stream()
                .filter(p -> p.getIsActive() == 1)
                .filter(p -> p.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .collect(Collectors.toList());


        List<Post> likedPosts = new ArrayList<>();

        int currentPostLikes;

        likedPosts.add(posts.get(0));

        for (int i = 1; i < posts.size(); i++) {


            currentPostLikes = likeCount(posts.get(i).getListVotes());
            if (currentPostLikes >= likeCount(posts.get(i - 1).getListVotes())) {
                likedPosts.add(i - 1, posts.get(i));
            } else {
                likedPosts.add(posts.get(i));
            }


        }

        return mapper.toGeneralPostDto(likedPosts);

    }

    @Override
    public GeneralPostDto getMostOldPosts() {

        return mapper.toGeneralPostDto(postRepository.findOldestPosts().stream()
                .filter(p -> p.getIsActive() == 1)
                .filter(p -> p.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .collect(Collectors.toList()));

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

    private int dislikeCount(List<PostVote> votes) {

        int dislikes = 0;
        for (PostVote vote : votes) {
            if (vote.getValue() == -1) {
                dislikes++;
            }
        }

        return dislikes;
    }


}
