package app.services.impl;

import app.model.Post;
import app.repos.PostRepository;
import app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post getPost(int id) {
        return postRepository.getById(id);
    }

}
