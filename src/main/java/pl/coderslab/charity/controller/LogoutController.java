package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    private final HttpSession session;

    @Autowired
    public LogoutController(HttpSession session) {
        this.session = session;
    }

    @GetMapping("/logout")
    public String logoutUser() {
        session.invalidate();
        return "redirect:/";
    }
}
