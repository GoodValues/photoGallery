package com.gallery.photo.controller;

import com.gallery.photo.configuration.jwt.JwtProvider;
import com.gallery.photo.message.request.LoginDTO;
import com.gallery.photo.message.response.JwtTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    JwtProvider provider;
    AuthenticationManager manager;

    @Autowired
    public LoginController(JwtProvider provider, AuthenticationManager manager) {
        this.provider = provider;
        this.manager = manager;
    }

    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity getTokenForUser(@RequestBody LoginDTO loginDTO){

        Authentication authentication =
                manager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);

        return ResponseEntity.ok(new JwtTokenDTO(token));

    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String getTokenForUser(@ModelAttribute("user") LoginDTO loginDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword() )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);
        redirectAttributes.addFlashAttribute("user", loginDTO);
        return "redirect:/";
    }

}
