package com.foi.userservice.controller;

import com.foi.userservice.facade.UserFacade;
import com.foi.userservice.model.TransactionListModel;
import com.foi.userservice.model.UserWithDateModel;
import com.foi.userservice.model.UserWithYearModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Controller
public class ReportController
{
    private static final String URL_MONTHLY_REPORT = "/user/monthly-report";
    private static final String URL_YEARLY_REPORT = "/user/yearly-report";
    private static final String VIEW_MONTHLY_REPORT = "monthly-report";
    private static final String VIEW_YEARLY_REPORT = "yearly-report";
    private static final String TRANSACTION_SERVICE_URL = "http://transaction-service";
    private static final String FIND_BY_USER_AND_CHOSEN_MONTH = "/find-by-user-and-chosen-month";
    private static final String FIND_BY_USER_AND_CHOSEN_YEAR = "/find-by-user-and-chosen-year";
    private static final String MODEL_ATTRIBUTE_TRANSACTIONS = "transactions";
    private static final String MODEL_ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS = "transactionTableSuccess";
    private static final String ERROR_MESSAGE_MONTHLY = "No transactions found between ";
    private static final String ERROR_MESSAGE_YEARLY = "No transactions found for year ";
    private static final String AND = " and ";
    private static final String yyyy_MM_dd = "yyyy-MM-dd";
    private static final String FIRST_DAY_OF_MONTH = "-01";
    private static final String DATE = "date";
    private static final String YEAR = "year";

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = URL_MONTHLY_REPORT, method = RequestMethod.GET)
    public String getViewMonthlyReport(final Model model)
    {
        return VIEW_MONTHLY_REPORT;
    }

    @RequestMapping(value = URL_MONTHLY_REPORT, method = RequestMethod.POST)
    public String postViewMonthlyReport(
            @RequestParam(DATE)
            final String date, final Model model)
    {
        UserWithDateModel userWithDateModel = new UserWithDateModel();
        userWithDateModel.setDate(date);
        userWithDateModel.setUserEntity(userFacade.getUserEntity());
        Gson gson = new Gson();
        String request = gson.toJson(userWithDateModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        ResponseEntity<TransactionListModel> result = restTemplate.exchange(
                TRANSACTION_SERVICE_URL + FIND_BY_USER_AND_CHOSEN_MONTH,
                HttpMethod.POST,
                entity,
                TransactionListModel.class
        );
        if (result.getBody() != null && result.getBody().getTransactionEntityList() != null && !result
                .getBody()
                .getTransactionEntityList()
                .isEmpty())
        {
            model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS, Boolean.TRUE);
            model.addAttribute(
                    MODEL_ATTRIBUTE_TRANSACTIONS, result.getBody().getTransactionEntityList());
        }
        else
        {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(yyyy_MM_dd);
            final LocalDate beginDate = LocalDate.parse(date + FIRST_DAY_OF_MONTH, dateTimeFormatter);
            final LocalDate endDate = beginDate.withDayOfMonth(beginDate.lengthOfMonth());
            final DateTimeFormatter format = DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)
                    .withLocale(Locale.ENGLISH);
            final String firstDayOfMonth = beginDate.format(format);
            final String lastDayOfMonth = endDate.format(format);
            model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS, Boolean.FALSE);
            model.addAttribute(
                    MODEL_ATTRIBUTE_ERROR_MESSAGE,
                    ERROR_MESSAGE_MONTHLY + firstDayOfMonth + AND + lastDayOfMonth
            );
        }
        return VIEW_MONTHLY_REPORT;
    }

    @RequestMapping(value = URL_YEARLY_REPORT, method = RequestMethod.GET)
    public String getViewYearlyReport(final Model model)
    {
        return VIEW_YEARLY_REPORT;
    }

    @RequestMapping(value = URL_YEARLY_REPORT, method = RequestMethod.POST)
    public String postViewYearlyReport(
            @RequestParam(YEAR)
            final String year, final Model model)
    {
        UserWithYearModel userWithYearModel = new UserWithYearModel();
        userWithYearModel.setUserEntity(userFacade.getUserEntity());
        userWithYearModel.setYear(year);
        Gson gson = new Gson();
        String request = gson.toJson(userWithYearModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        ResponseEntity<TransactionListModel> result = restTemplate.exchange(
                TRANSACTION_SERVICE_URL + FIND_BY_USER_AND_CHOSEN_YEAR,
                HttpMethod.POST,
                entity,
                TransactionListModel.class
        );
        if (result.getBody() != null && result.getBody().getTransactionEntityList() != null && !result
                .getBody()
                .getTransactionEntityList()
                .isEmpty())
        {
            model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS, Boolean.TRUE);
            model.addAttribute(
                    MODEL_ATTRIBUTE_TRANSACTIONS, result.getBody().getTransactionEntityList());
        }
        else
        {
            model.addAttribute(MODEL_ATTRIBUTE_TRANSACTION_TABLE_SUCCESS, Boolean.FALSE);
            model.addAttribute(MODEL_ATTRIBUTE_ERROR_MESSAGE, ERROR_MESSAGE_YEARLY + year);
        }
        return VIEW_YEARLY_REPORT;
    }
}
