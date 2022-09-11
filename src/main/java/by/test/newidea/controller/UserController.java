package by.test.newidea.controller;

import by.test.newidea.controller.requests.UserCreateRequest;
import by.test.newidea.controller.requests.UserSearchRequest;
import by.test.newidea.domain.User;
import by.test.newidea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

 //   @RequestMapping(method = RequestMethod.GET, value = "/users")
 //   @GetMapping("/users")
    @GetMapping
    public ModelAndView findAllUsers() {
        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Slava");
        model.addObject("users", users);

        model.setViewName("users");

        return model;
    }
 //   @GetMapping("/user/{id}")
    @GetMapping("/{id}")
    public ModelAndView findUserById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long userId = Long.parseLong(id);
        User user = userService.findById(userId);

        ModelAndView model = new ModelAndView();
        model.addObject("userName", user.getUserName());
        model.addObject("user", user);

        model.setViewName("user");

        return model;
    }
//
//    @GetMapping("/users/search")
//    public ModelAndView findAllUsersWithParams(@RequestParam("limit") String limit,
//                                               @RequestParam("offset") String offset) {
//
//        int verifiedLimit = Integer.parseInt(limit);
//        int verifiedOffset = Integer.parseInt(offset);
//
//        List<User> users = userService.search(verifiedLimit, verifiedOffset);
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("user", "Slava");
//        model.addObject("users", users);
//
//        model.setViewName("users");
//
//        return model;
//    }


   // @GetMapping("/users/search")
    @GetMapping("/search")
    public ModelAndView findAllUsersWithParams(@ModelAttribute UserSearchRequest userSearchRequest) {

        int verifiedLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.search(verifiedLimit, verifiedOffset);

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Slava");
        model.addObject("users", users);

        model.setViewName("users");

        return model;
    }

    @PostMapping
    //Jackson
    public ModelAndView createUser(@RequestBody UserCreateRequest createRequest) {

        User user = new User();
        user.setUserName(createRequest.getUserName());
        user.setSurname(createRequest.getSurname());
        user.setBirth(new Timestamp(new Date().getTime()));
        user.setCreateDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
      //  user.set(createRequest.getWeight());

        userService.create(user);

        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Slava");
        model.addObject("users", users);

        model.setViewName("users");

        return model;
    }



}

