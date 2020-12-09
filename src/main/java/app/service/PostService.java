package app.service;

import app.dto.GeneralPostDto;

public interface PostService {


    GeneralPostDto getSortedAndPagedPosts(int offset, int limit, String mode);

    GeneralPostDto findPostsByQuery(String query, int offset, int limit);

    GeneralPostDto findByDate(String date, int offset, int limit);

    GeneralPostDto findByTag(String tag, int offset, int limit);

}
