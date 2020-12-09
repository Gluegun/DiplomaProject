package app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class DefaultController {

//    private final UserService userService;
//
//    @GetMapping()
//    private ResponseEntity<UserDetails> getUserDetail(
//            @RequestParam(required = false, defaultValue = "") String email) {
//
//        return new ResponseEntity<>(userService.loadUserByUsername(email), HttpStatus.OK);
//
//    }


}
