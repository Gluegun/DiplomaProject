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


    @GetMapping
    public ResponseEntity<GeneralPostDto> filterPosts(@RequestParam(defaultValue = "recent") String mode) {

        switch (mode) {
            case "popular":
                return new ResponseEntity<>(postService.getMostDiscussedPosts(), HttpStatus.OK);
            case "recent":
                return new ResponseEntity<>(postService.getMostRecentPosts(), HttpStatus.OK);
            case "best":
                return new ResponseEntity<>(postService.getMostLikedPosts(), HttpStatus.OK);
            case "early":
                return new ResponseEntity<>(postService.getMostOldPosts(), HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.OK);
        }
    }



}
