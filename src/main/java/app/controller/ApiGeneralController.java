package app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    @GetMapping("/init")
    public String init() {
        return null;

    }

    @GetMapping("/settings")
    public String settings() {

        return null;
    }

    @GetMapping("/tag")
    public String tag() {
        return null;
    }

}
