package com.foi.MyFinance.controller;

import com.foi.MyFinance.entity.TransactionEntity;
import com.foi.MyFinance.facade.TransactionFacade;
import com.foi.MyFinance.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

@Controller
public class ReportController
{
    private static final String URL_MONTHLY_REPORT = "/user/monthly-report";
    private static final String URL_YEARLY_REPORT = "/user/yearly-report";

    private static final String VIEW_MONTHLY_REPORT = "monthly-report";
    private static final String VIEW_YEARLY_REPORT = "yearly-report";

    private static final String MODEL_ATTRIBUTE_TRANSACTIONS = "transactions";
    private static final String MODEL_ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS = "transactionTableSuccess";
    private static final String ERROR_MESSAGE = "No transactions found between ";
    private static final String AND = " and ";

    private static final String yyyy_MM_dd = "yyyy-MM-dd";
    private static final String FIRST_DAY_OF_MONTH = "-01";

    @Autowired
    private TransactionFacade transactionFacade;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = URL_MONTHLY_REPORT, method = RequestMethod.GET)
    public String getViewMonthlyReport(final Model model)
    {
        return VIEW_MONTHLY_REPORT;
    }

    @RequestMapping(value = URL_MONTHLY_REPORT, method = RequestMethod.POST)
    public String postViewMonthlyReport(
            @RequestParam("date")
            final String date, final Model model)
    {
        final List<TransactionEntity> transactionEntityList = transactionFacade.findByUserAndChosenMonth(
                userFacade.getUserEntity(),
                date
        );
        if (!transactionEntityList.isEmpty())
        {
            model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS, Boolean.TRUE);
            model.addAttribute(
                    MODEL_ATTRIBUTE_TRANSACTIONS, transactionEntityList);
        }
        else
        {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(yyyy_MM_dd);
            final LocalDate beginDate = LocalDate.parse(date + FIRST_DAY_OF_MONTH, dateTimeFormatter);

            final LocalDate endDate = beginDate.withDayOfMonth(beginDate.lengthOfMonth());

            final DateTimeFormatter format = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                                                              .withLocale(Locale.ENGLISH);
            final String firstDayOfMonth = beginDate.format(format);
            final String lastDayOfMonth = endDate.format(format);

            model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS, Boolean.FALSE);
            model.addAttribute(MODEL_ATTRIBUTE_ERROR_MESSAGE, ERROR_MESSAGE + firstDayOfMonth + AND + lastDayOfMonth);
        }
        return VIEW_MONTHLY_REPORT;
    }

    @RequestMapping(value = URL_YEARLY_REPORT, method = RequestMethod.GET)
    public String getViewYearlyReport(final Model model)
    {
        return VIEW_YEARLY_REPORT;
    }
}
