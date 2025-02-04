package com.myProjects.ManyToOne.Controllers;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.myProjects.ManyToOne.Entities.*;
import com.myProjects.ManyToOne.Services.WebServices;;
@Controller
public class WebControllers {
    @Autowired
    private WebServices webServices;

    @GetMapping("/login")
    public String showloginpage(){
        return "loginpage";
    }
    @GetMapping("/")
    public String hello(){
            return "hello";
    }
    @GetMapping("/register")
    public String register(){
        return "registerpage";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        webServices.RegisterUser(user);
        return "redirect:/login";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/adminpage")
    public String viewAdmin(){
        return "adminpage";
    }
    @Secured("ROLE_USER")
    @GetMapping("/userpage")
    public String viewUser(){
        return "userpage";
    }
    @GetMapping("/403")
    public String accessDenied() {
        return "403";  
    }
    @GetMapping("/error")
    public String handleError(Model model) {
        // You can access the exception or status via the model
        model.addAttribute("errorMessage", "Something went wrong. Please try again later.");
        return "error";  // Custom error page
    }
}
@ControllerAdvice
 class GlobalExceptionHandler {

    // Handle Access Denied Exception
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(Model model) {
        model.addAttribute("error", "You do not have permission to access this page.");
        return "403";  // Return custom 403.html
    }
    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Exception e, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
        return "error";  // This will redirect to a generic error page (can be customized)
    }
}
