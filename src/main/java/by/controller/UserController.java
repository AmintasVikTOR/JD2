package by.controller;

import by.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long userId, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findOne(userId));
        return "user/user";
    }

    @GetMapping("/search")
    public String searchUser(@RequestParam("query") String query, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.search(query));
        return "user/users";
    }
}
