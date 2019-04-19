package com.foi.MyFinance.validation;

import com.foi.MyFinance.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class RegistrationFieldsValidator implements Validator
{
    private static final String MATCHING_PASSWORD = "matchingPassword";
    private static final String MATCHING_PASSWORD_ATTRIBUTE = "validation.matching.password.error";

    @Override
    public boolean supports(final Class<?> aClass)
    {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors)
    {
        final UserModel userModel = (UserModel) o;
        if (!StringUtils.equals(userModel.getPassword(), userModel.getMatchingPassword()))
        {
            errors.rejectValue(MATCHING_PASSWORD, MATCHING_PASSWORD_ATTRIBUTE);
        }
    }
}
