package com.foi.userservice.validation;

import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.UserModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFieldsValidator implements Validator
{
    private static final String MATCHING_PASSWORD_FIELD = "matchingPassword";
    private static final String MATCHING_PASSWORD_CODE = "validation.matching.password.error";
    private static final String EXISTING_EMAIL_FIELD = "email";
    private static final String EXISTING_EMAIL_CODE = "validation.existing.email.error";
    private static final String EXISTING_USERNAME_FIELD = "username";
    private static final String EXISTING_USERNAME_CODE = "validation.existing.username.error";

    @Autowired
    private UserFacade userFacade;

    @Override
    public boolean supports(final Class<?> aClass)
    {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors)
    {
        final UserModel userModel = (UserModel) o;
        if (!ObjectUtils.isEmpty(userFacade.findByEmail(userModel.getEmail())))
        {
            errors.rejectValue(EXISTING_EMAIL_FIELD, EXISTING_EMAIL_CODE);
        }
        if (!ObjectUtils.isEmpty(userFacade.findByUsername(userModel.getUsername())))
        {
            errors.rejectValue(EXISTING_USERNAME_FIELD, EXISTING_USERNAME_CODE);
        }
        if (!StringUtils.equals(userModel.getPassword(), userModel.getMatchingPassword()))
        {
            errors.rejectValue(MATCHING_PASSWORD_FIELD, MATCHING_PASSWORD_CODE);
        }
    }
}
