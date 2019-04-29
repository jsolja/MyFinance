package com.foi.MyFinance.controller;

import com.foi.MyFinance.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController
{
    private static final String URL_PROFILE_UPDATE = "/user/profile";
    private static final String VIEW_PROFILE_UPDATE = "profile";
    private static final String MODEL_ATTRIBUTE_USER_MODE = "userModel";

    @RequestMapping(value = URL_PROFILE_UPDATE, method = RequestMethod.GET)
    public String getViewProfileUpdate(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_USER_MODE, new UserModel());
        return VIEW_PROFILE_UPDATE;
    }
}
