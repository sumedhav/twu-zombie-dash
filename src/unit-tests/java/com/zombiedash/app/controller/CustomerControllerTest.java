package com.zombiedash.app.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CustomerControllerTest {
    @Test
    public void shouldDisplayCustomerHomePage() throws Exception {
        CustomerController customerController=new CustomerController();
        ModelAndView actualModel = customerController.showCustomerPage();
        assertThat(actualModel.getViewName(), is(equalTo("customer")));
    }
}
