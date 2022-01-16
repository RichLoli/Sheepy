package com.macoloco.sheepy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aya
 * @date 2022/1/16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getuser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
