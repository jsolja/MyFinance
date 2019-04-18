package com.foi.MyFinance.controller;

import com.foi.MyFinance.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController
{
    private static final String MODEL_ATTRIBUTE_USER_MODEL = "userModel";

    private static final String URL_REGISTRATION = "/registration";
    private static final String VIEW_REGISTRATION = "registration";

    @RequestMapping(value = URL_REGISTRATION, method = RequestMethod.GET)
    public String getRegistration(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_USER_MODEL, new UserModel());
        return VIEW_REGISTRATION;
    }

    @RequestMapping(value = URL_REGISTRATION, method = RequestMethod.POST)
    public String postRegistration(
            @ModelAttribute(MODEL_ATTRIBUTE_USER_MODEL)
            @Valid
            final UserModel userModel, final
    HttpServletRequest request, final BindingResult result)
    {
        System.out.println("tusam");
        System.out.println(userModel.getFirstName());
        return VIEW_REGISTRATION;
    }

}
