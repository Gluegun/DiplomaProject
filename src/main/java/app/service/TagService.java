package app.service;

import app.dto.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> getListOfTags(String query);

}
