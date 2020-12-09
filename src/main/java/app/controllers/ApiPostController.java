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
            @RequestParam(defaultValue = "0", required = false) int offset,
            @RequestParam(defaultValue = "5", required = false) int limit,
            @RequestParam(defaultValue = "recent", required = false) String mode) {

        return new ResponseEntity<>(postService.getSortedAndPagedPosts(offset, limit, mode), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<GeneralPostDto> findPosts(
            @RequestParam(defaultValue = "", required = false) String query,
            @RequestParam(defaultValue = "0", required = false) int offset,
            @RequestParam(defaultValue = "5", required = false) int limit) {

        return new ResponseEntity<>(postService.findPostsByQuery(query, offset, limit), HttpStatus.OK);

    }

    @GetMapping("/byDate")
    public ResponseEntity<GeneralPostDto> findPostsByDate(
            @RequestParam(defaultValue = "", required = false) String date,
            @RequestParam(defaultValue = "0", required = false) int offset,
            @RequestParam(defaultValue = "5", required = false) int limit) {

        return new ResponseEntity<>(postService.findByDate(date, offset, limit), HttpStatus.OK);

    }

    @GetMapping("/byTag")
    public ResponseEntity<GeneralPostDto> findPostsByTag(
            @RequestParam(defaultValue = "", required = false) String tag,
            @RequestParam(defaultValue = "0", required = false) int offset,
            @RequestParam(defaultValue = "5", required = false) int limit) {

        return new ResponseEntity<>(postService.findByTag(tag, offset, limit), HttpStatus.OK);

    }
}
