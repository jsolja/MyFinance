package com.foi.MyFinance.validation;

import com.foi.MyFinance.facade.UserFacade;
import com.foi.MyFinance.model.TransactionModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TransactionFieldsValidator implements Validator
{
    private static final String INCOME = "income";
    private static final String EXPENSE = "expense";

    private static final String TO_USER_FIELD = "toUser";
    private static final String TO_USER_CODE = "validation.to.user.error";

    private static final String FROM_USER_FIELD = "fromUser";
    private static final String FROM_USER_CODE = "validation.from.user.error";

    private static final String AMOUNT_FIELD = "amount";
    private static final String AMOUNT_BELOW_ZERO_CODE = "validation.amount.below.zero.error";
    private static final String AMOUNT_INSUFFICIENT_FUNDS_CODE = "validation.amount.insufficient.funds.error";

    private static final String TYPE_FIELD = "type";
    private static final String TYPE_CODE = "validation.type.error";

    @Autowired
    private UserFacade userFacade;

    @Override
    public boolean supports(final Class<?> aClass)
    {
        return TransactionModel.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors)
    {
        final TransactionModel transactionModel = (TransactionModel) o;

        if (StringUtils.equals(transactionModel.getType(), INCOME))
        {
            if (!StringUtils.equals(userFacade.getUserModel().getUsername(), transactionModel.getToUser()))
            {
                errors.rejectValue(TO_USER_FIELD, TO_USER_CODE);
            }
        }

        if (StringUtils.equals(transactionModel.getType(), EXPENSE))
        {
            if (!StringUtils.equals(userFacade.getUserModel().getUsername(), transactionModel.getFromUser()))
            {
                errors.rejectValue(FROM_USER_FIELD, FROM_USER_CODE);
            }
            else if (userFacade.getUserModel().getBalance() < transactionModel.getAmount())
            {
                errors.rejectValue(
                        AMOUNT_FIELD,
                        AMOUNT_INSUFFICIENT_FUNDS_CODE
                );
            }
        }

        if (transactionModel.getAmount() <= 0)
        {
            errors.rejectValue(AMOUNT_FIELD, AMOUNT_BELOW_ZERO_CODE);
        }

        if (!StringUtils.equals(transactionModel.getType(), INCOME) && !StringUtils.equals(
                transactionModel.getType(),
                EXPENSE
        ))
        {
            errors.rejectValue(TYPE_FIELD, TYPE_CODE);
        }
    }
}
