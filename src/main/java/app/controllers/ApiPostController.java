package app.controllers;


import app.dto.PostDTO;
import app.mappers.PostMapper;
import app.model.Post;
import app.repos.PostRepository;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;


    @GetMapping("/{id}")
    public PostDTO getPostDTOById(@PathVariable int id) {
        Post post = postService.getPost(id);
        return postMapper.toDTO(post);
    }

    @GetMapping("/")
    public List<PostDTO> getAllPostsDTO () {

        List<Post> posts = postRepository.findAll();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post post : posts) {
            postsDTO.add(getPostDTOById(post.getId()));
        }

        return postsDTO;
    }


}