package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: Clmkimpo
 * Date: 29/08/12
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateConferencePageTest {

    @Test
    public void successfulCreationOfConferenceIfAllDataIsValid() {
        Browser browser = Application.browser();
        browser.open("/zombie/admin/conference/createConference");

        WebElement confNameElement = browser.findElement(By.name("conf_name"));
        confNameElement.sendKeys("Java");
        WebElement confTopicElement = browser.findElement(By.name("conf_topic"));
        confTopicElement.sendKeys("Java basics");
        WebElement confDescriptionElement = browser.findElement(By.name("conf_description"));
        confDescriptionElement.sendKeys("Java is a fun language. It also is an island, a very beautiful one.");
        WebElement confStartDateElement = browser.findElement(By.name("conf_start_date"));
        confStartDateElement.sendKeys("2013-12-12");
        WebElement confEndDateElement = browser.findElement(By.name("conf_end_date"));
        confEndDateElement.sendKeys("2013-12-13");
        WebElement confVenueElement = browser.findElement(By.name("conf_venue"));
        confVenueElement.sendKeys("Thoughtworks india pvt. ltd.");
        WebElement confNoOfAttendeesElement = browser.findElement(By.name("conf_max_attendees"));
        confNoOfAttendeesElement.sendKeys("40");

        WebElement saveElement = browser.findElement(By.name("save"));
        saveElement.click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Conference Home"));

        WebElement exNameElement = browser.findElement(By.id("existing_conference_name_1"));
        assertThat(exNameElement.getText(), is("Java"));

    }
    @Test
    public void ifDataIsInValidStayOnTheSamePage() {
        Browser browser = Application.browser();
        browser.open("/zombie/admin/conference/createConference");

        WebElement confNameElement = browser.findElement(By.name("conf_name"));
        confNameElement.sendKeys("Java");
        WebElement confTopicElement = browser.findElement(By.name("conf_topic"));
        confTopicElement.sendKeys("Java basics");
        WebElement confDescriptionElement = browser.findElement(By.name("conf_description"));
        confDescriptionElement.sendKeys("Java is a fun language. It also is an island, a very beautiful one.");
        WebElement confStartDateElement = browser.findElement(By.name("conf_start_date"));
        confStartDateElement.sendKeys("2013-12-12");
        WebElement confEndDateElement = browser.findElement(By.name("conf_end_date"));
        confEndDateElement.sendKeys("2013-12-13");
        WebElement confVenueElement = browser.findElement(By.name("conf_venue"));
//        confVenueElement.sendKeys("");
        WebElement confNoOfAttendeesElement = browser.findElement(By.name("conf_max_attendees"));
        confNoOfAttendeesElement.sendKeys("4");

        WebElement saveElement = browser.findElement(By.name("save"));
        saveElement.click();
        assertThat(browser.getPageTitle(), is("Create Conference Page"));
        assertThat(confNameElement.getAttribute("value"), is("Java"));
        assertThat(confDescriptionElement.getAttribute("value"), is("Java is a fun language. It also is an island, a very beautiful one."));
        assertThat(confEndDateElement.getAttribute("value"),is("2013-12-13"));
        assertThat(confNoOfAttendeesElement.getAttribute("value"),is("4"));
        assertThat(confStartDateElement.getAttribute("value"),is("2013-12-12"));
        assertThat(confTopicElement.getAttribute("value"), is("Java basics"));
        assertThat(confVenueElement.getAttribute("value"),is(""));



    }

}
