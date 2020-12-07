package app.controllers;

import app.dto.GeneralPostDto;
import app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class ApiPostController {

    private final PostService postService;

    @GetMapping()
    public ResponseEntity<GeneralPostDto> getPosts(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "5", required = false) int limit,
            @RequestParam(defaultValue = "recent", required = false) String mode) {

        GeneralPostDto posts = postService.getSortedAndPagedPosts(page, limit, mode);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
