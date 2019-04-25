package com.foi.MyFinance.controller;

import com.foi.MyFinance.model.ForgotPasswordModel;
import com.foi.MyFinance.validation.PasswordFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class PasswordController
{
    private static final String MODEL_ATTRIBUTE_FORGOT_PASSWORD_MODEL = "forgotPasswordModel";
    private static final String VIEW_FORGOTTEN_PASSWORD = "forgottenPassword";

    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "Email with reset link has been sent to ";

    @Autowired
    private PasswordFieldsValidator passwordFieldsValidator;

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.GET)
    public String getViewForgottenPassword(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_FORGOT_PASSWORD_MODEL, new ForgotPasswordModel());
        return VIEW_FORGOTTEN_PASSWORD;
    }

    @RequestMapping(value = VIEW_FORGOTTEN_PASSWORD, method = RequestMethod.POST)
    public String postViewForgottenPassword(
            @ModelAttribute(MODEL_ATTRIBUTE_FORGOT_PASSWORD_MODEL)
            @Valid
            final ForgotPasswordModel forgotPasswordModel, final BindingResult result, final Model model)
    {
        ValidationUtils.invokeValidator(passwordFieldsValidator, forgotPasswordModel, result);
        if (!result.hasErrors())
        {
            model.addAttribute(
                    MODEL_ATTRIBUTE_SUCCESS,
                    MODEL_ATTRIBUTE_SUCCESS_MESSAGE + forgotPasswordModel.getEmail()
            );
        }
        return VIEW_FORGOTTEN_PASSWORD;
    }

}
