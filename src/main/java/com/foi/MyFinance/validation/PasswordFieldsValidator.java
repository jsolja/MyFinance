package com.foi.MyFinance.validation;

import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.ForgotPasswordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordFieldsValidator implements Validator
{
    private static final String NOT_EXISTING_EMAIL_FIELD = "email";
    private static final String NOT_EXISTING_EMAIL_CODE = "validation.not.existing.email.error";

    @Autowired
    private UserFacade userFacade;

    @Override
    public boolean supports(final Class<?> aClass)
    {
        return ForgotPasswordModel.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors)
    {
        final ForgotPasswordModel forgotPasswordModel = (ForgotPasswordModel) o;
        if (ObjectUtils.isEmpty(userFacade.findByEmail(forgotPasswordModel.getEmail())))
        {
            errors.rejectValue(NOT_EXISTING_EMAIL_FIELD, NOT_EXISTING_EMAIL_CODE);
        }
    }
}
