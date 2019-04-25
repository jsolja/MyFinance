package com.foi.MyFinance.controller;

import com.foi.MyFinance.entity.UserEntity;
import com.foi.MyFinance.facade.EmailFacade;
import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.ForgotPasswordModel;
import com.foi.MyFinance.validation.PasswordFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PasswordController
{
    private static final String MODEL_ATTRIBUTE_FORGOT_PASSWORD_MODEL = "forgotPasswordModel";
    private static final String VIEW_FORGOTTEN_PASSWORD = "forgottenPassword";

    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "Email with reset link has been sent to ";

    private static final String MODEL_ATTRIBUTE_EMAIL = "emailNotFound";
    private static final String MODEL_ATTRIBUTE_EMAIL_MESSAGE = "This email does not belong to any account!";

    @Autowired
    private PasswordFieldsValidator passwordFieldsValidator;

    @Autowired
    private EmailFacade emailFacade;

    @Autowired
    private UserFacade userFacade;

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
            final ForgotPasswordModel forgotPasswordModel,
            final Model model,
            final HttpServletRequest request)
    {
        final Optional<UserEntity> optionalUserEntity = userFacade.findByEmail(forgotPasswordModel.getEmail());
        if (optionalUserEntity.isPresent())
        {
            model.addAttribute(
                    MODEL_ATTRIBUTE_SUCCESS,
                    MODEL_ATTRIBUTE_SUCCESS_MESSAGE + forgotPasswordModel.getEmail()
            );
            emailFacade.sendForgottenPasswordEmail(optionalUserEntity.get(), request);
        }
        else
        {
            model.addAttribute(MODEL_ATTRIBUTE_EMAIL, MODEL_ATTRIBUTE_EMAIL_MESSAGE);
        }
        return VIEW_FORGOTTEN_PASSWORD;
    }

}
