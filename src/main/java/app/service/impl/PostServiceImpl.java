package app.service.impl;

import app.dto.GeneralPostDto;
import app.dto.PostDtoWithComments;
import app.dto.PostForLikes;
import app.mapper.Mapper;
import app.model.Post;
import app.model.PostVote;
import app.model.Tag;
import app.model.enums.ModerationStatus;
import app.repos.PostRepository;
import app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

        return getFilteredPosts(posts);

    }

    @Override
    public GeneralPostDto findPostsByQuery(String query, int offset, int limit) {

        if (query.isEmpty()) {

            List<Post> all = postRepository.findAll();

            return getFilteredPosts(all);
        }


        PageRequest page = PageRequest.of(offset, limit);

        List<Post> posts = postRepository.findByQuery(query, page).toList();

        return getFilteredPosts(posts);


    }

    @Override
    public GeneralPostDto findByDate(String date, int offset, int limit) {

        PageRequest page = PageRequest.of(offset, limit);

        if (date.isEmpty() || date.isBlank()) {

            Page<Post> all = postRepository.findAll(page);

            return getFilteredPosts(all.toList());

        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate timeFrom = LocalDate.parse(date, dateTimeFormatter);
        LocalDate timeTo = timeFrom.plusDays(1);


        Page<Post> posts = postRepository.findByTime(timeFrom, timeTo, page);

        return getFilteredPosts(posts.toList());


    }

    @Override
    public GeneralPostDto findByTag(String tagName, int offset, int limit) {


        //todo без пагинации находит, с ней нет. Надо подумать

        PageRequest page = PageRequest.of(offset, limit);

        Page<Post> allPosts = postRepository.findAll(page);

        if (tagName.isBlank() || tagName.isEmpty()) {

            return getFilteredPosts(allPosts.toList());

        }

        List<Post> posts = allPosts.toList();

        List<Post> matchedPosts = new ArrayList<>();

        for (Post post : posts) {

            for (Tag tag : post.getListTags()) {

                if (tag.getName().equals(tagName)) {
                    matchedPosts.add(post);
                }

            }
        }

        return getFilteredPosts(matchedPosts);


    }

    @Override
    public PostDtoWithComments findById(int id) {

        Optional<Post> postById = postRepository.findById(id);

        if (postById.isPresent()) {
            Post post = postById.get();
            return mapper.toPostDtoWithComments(post);
        } else return null;

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

    private GeneralPostDto getFilteredPosts(List<Post> posts) {


        posts = posts.stream()
                .filter(post -> post.getModerationStatus().equals(ModerationStatus.ACCEPTED))
                .filter(post -> post.getIsActive() == 1)
                .filter(post -> post.getTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        return mapper.toGeneralPostDto(posts);

    }

}
