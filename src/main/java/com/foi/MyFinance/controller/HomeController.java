package com.foi.MyFinance.controller;

import com.foi.MyFinance.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController
{
    private static final String MODEL_ATTRIBUTE_USER_ENTITY = "userEntity";

    private static final String HOME_PAGE = "/home";

    @RequestMapping(value = HOME_PAGE, method = RequestMethod.GET)
    public String home(final Model model)
    {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        model.addAttribute(MODEL_ATTRIBUTE_USER_ENTITY, userEntity);
        return HOME_PAGE;
    }
}
