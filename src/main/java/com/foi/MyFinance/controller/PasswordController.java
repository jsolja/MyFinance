package com.foi.MyFinance.controller;

import com.foi.MyFinance.model.ForgotPasswordModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PasswordController
{
    private static final String MODEL_ATTRIBUTE_FORGOT_PASSWORD_MODEL = "forgotPasswordModel";
    private static final String VIEW_FORGOTTEN_PASSWORD = "forgottenPassword";

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.GET)
    public String getViewForgottenPassword(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_FORGOT_PASSWORD_MODEL, new ForgotPasswordModel());
        return VIEW_FORGOTTEN_PASSWORD;
    }
}
