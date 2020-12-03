package app.service.impl;

import app.dto.GeneralPostDto;
import app.mapper.Mapper;
import app.model.Post;
import app.repos.PostRepository;
import app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final Mapper mapper;
    private final PostRepository postRepository;

    @Override
    public GeneralPostDto getGeneralPostDtoInfo() {

        List<Post> posts = postRepository.findAll();
        return mapper.toGeneralPostDto(posts);

    }
}
