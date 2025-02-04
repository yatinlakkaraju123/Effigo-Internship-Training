package com.myprojects.OnetoOne.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {


        @GetMapping("/")
        public String home() {
            return "hello";  // Maps to hello.html (or hello.jsp)
        }

        @GetMapping("/login")
        public String login() {
            return "login";  // Maps to login.html (or login.jsp)
        }


}
