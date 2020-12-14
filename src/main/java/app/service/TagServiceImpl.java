package app.service;

import app.dto.TagDto;
import app.mapper.Mapper;
import app.model.Tag;
import app.repos.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {


    private final TagRepository tagRepository;
    private final Mapper mapper;

    @Override
    public List<TagDto> getListOfTags(String query) {

        List<Tag> tags = tagRepository.findAll();

        return tags.stream().map(mapper::toTagDto).collect(Collectors.toList());

    }
}
