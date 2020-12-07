package app.service;

import app.dto.GeneralPostDto;

public interface PostService {

    GeneralPostDto getPostsActiveAndAcceptedByModerator();

    GeneralPostDto getAllPosts();

    GeneralPostDto getMostDiscussedPosts();

    GeneralPostDto getMostRecentPosts();

    GeneralPostDto getMostLikedPosts();

    GeneralPostDto getMostOldPosts();

}
