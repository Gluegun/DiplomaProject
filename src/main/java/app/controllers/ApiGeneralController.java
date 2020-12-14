package app.controllers;

import app.dto.Blog;
import app.dto.PostForYear;
import app.dto.TagDto;
import app.service.PostService;
import app.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiGeneralController {

    private final TagService tagService;
    private final PostService postService;

    @GetMapping("/init")
    public Blog init() {
        return new Blog();
    }

    @GetMapping("/settings")
    public String settings() {
        return null;
    }

    @GetMapping("/tag")
    public List<TagDto> getTags() {

        return tagService.getListOfTags("");

    }

    @GetMapping("/calendar")
    public PostForYear getPostsByYear(
            @RequestParam(required = false, defaultValue = "") String year) {

        return postService.getCalendar(year);

    }

}
