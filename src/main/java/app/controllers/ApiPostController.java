package app.controllers;

import app.dto.GeneralPostDto;
import app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class ApiPostController {

    private final PostService postService;

    @GetMapping
    public GeneralPostDto getPostsInfo() {

        return postService.getGeneralPostDtoInfo();

    }


}
