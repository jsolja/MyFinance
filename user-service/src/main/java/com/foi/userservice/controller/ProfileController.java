package com.foi.userservice.controller;

import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.UserModel;
import com.foi.userservice.validation.UpdateUserFieldsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ProfileController
{
    private static final String URL_PROFILE_UPDATE = "/user/profile";
    private static final String VIEW_PROFILE_UPDATE = "profile";
    private static final String MODEL_ATTRIBUTE_USER_MODEL = "userModel";
    private static final String MODEL_ATTRIBUTE_SUCCESS = "successMessage";
    private static final String MODEL_ATTRIBUTE_SUCCESS_MESSAGE = "You have successfully updated your data.";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UpdateUserFieldsValidator updateUserFieldsValidator;

    @RequestMapping(value = URL_PROFILE_UPDATE, method = RequestMethod.GET)
    public String getViewProfileUpdate(final Model model)
    {
        model.addAttribute(MODEL_ATTRIBUTE_USER_MODEL, userFacade.getUserModel());
        return VIEW_PROFILE_UPDATE;
    }

    @RequestMapping(value = URL_PROFILE_UPDATE, method = RequestMethod.POST)
    public String postViewProfileUpdate(
            @ModelAttribute(MODEL_ATTRIBUTE_USER_MODEL)
            @Valid
            final UserModel userModel,
            final BindingResult result,
            final Model model,
            final HttpServletRequest request
    )
    {
        ValidationUtils.invokeValidator(updateUserFieldsValidator, userModel, result);
        if (!result.hasErrors())
        {
            userFacade.updateUser(userModel);
            model.addAttribute(MODEL_ATTRIBUTE_SUCCESS, MODEL_ATTRIBUTE_SUCCESS_MESSAGE);
        }
        model.addAttribute(MODEL_ATTRIBUTE_USER_MODEL, userModel);
        return VIEW_PROFILE_UPDATE;
    }
}
