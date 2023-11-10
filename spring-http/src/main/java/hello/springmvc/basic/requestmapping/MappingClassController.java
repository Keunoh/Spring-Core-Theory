package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String getUser() {
        return "get User";
    }

    @PostMapping
    public String saveUser() {
        return "save User";
    }

    @GetMapping("/{userId}")
    public String getUserId(@PathVariable String userId) {
        return "get User " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update User " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete User " + userId;
    }
}
