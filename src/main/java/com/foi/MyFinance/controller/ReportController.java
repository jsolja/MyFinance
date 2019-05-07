package com.foi.MyFinance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController
{
    private static final String URL_MONTHLY_REPORT = "/user/monthly-report";
    private static final String URL_YEARLY_REPORT = "/user/yearly-report";

    private static final String VIEW_MONTHLY_REPORT = "monthly-report";
    private static final String VIEW_YEARLY_REPORT = "yearly-report";

    @RequestMapping(value = URL_MONTHLY_REPORT, method = RequestMethod.GET)
    public String getViewMonthlyReport(final Model model)
    {
        return VIEW_MONTHLY_REPORT;
    }

    @RequestMapping(value = URL_YEARLY_REPORT, method = RequestMethod.GET)
    public String getViewYearlyReport(final Model model)
    {
        return VIEW_YEARLY_REPORT;
    }
}
