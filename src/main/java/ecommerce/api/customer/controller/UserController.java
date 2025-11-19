package ecommerce.api.customer.controller;

import ecommerce.api.customer.config.JwtUtil;
import ecommerce.api.customer.entity.User;
import ecommerce.api.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null && userService.checkPassword(user.getPassword(), existing.getPassword())) {
            return jwtUtil.generateToken(existing.getEmail());
        }
        return "Invalid credentials";
    }

    @GetMapping("/test")
    public String test() {
        return "JWT authentication successful!";
    }
}

