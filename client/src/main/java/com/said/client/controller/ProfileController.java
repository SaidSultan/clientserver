package com.said.client.controller;


import com.said.client.model.User;
import com.said.client.transfer.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/user")
    public String getProfilePage(ModelMap modelMap, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        User details = (User) authentication.getPrincipal();
        UserDTO userDTO = UserDTO.from(details);
        modelMap.addAttribute("authUser", userDTO);
        return "profile";
    }
}
