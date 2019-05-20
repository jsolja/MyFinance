package com.foi.userservice.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * MIKROSERVISI - USER
 */
@Component
public class UpdateUserFieldsValidator implements Validator
{
    //    private static final String EXISTING_EMAIL_FIELD = "email";
    //    private static final String EXISTING_EMAIL_CODE = "validation.existing.email.error";
    //
    //    private static final String MATCHING_PASSWORD_FIELD = "matchingPassword";
    //    private static final String MATCHING_PASSWORD_CODE = "validation.matching.password.error";
    //
    //    @Autowired
    //    private UserFacade userFacade;
    //
    @Override
    public boolean supports(final Class<?> aClass)
    {
        return true;//UserModel.class.equals(aClass);
    }

    //
    //    @Override
    public void validate(final Object o, final Errors errors)
    {
        //        final UserModel userModel = (UserModel) o;
        //        final Optional<UserEntity> optionalUserEntity = userFacade.findByEmail(userModel.getEmail());
        //        if (optionalUserEntity.isPresent())
        //        {
        //            if (!StringUtils.equals(optionalUserEntity.get().getUsername(), userModel.getUsername()))
        //            {
        //                errors.rejectValue(EXISTING_EMAIL_FIELD, EXISTING_EMAIL_CODE);
        //            }
        //        }
        //        if (!StringUtils.equals(userModel.getPassword(), userModel.getMatchingPassword()))
        //        {
        //            errors.rejectValue(MATCHING_PASSWORD_FIELD, MATCHING_PASSWORD_CODE);
        //        }
    }
}
