package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Ignore;
import org.junit.Test;

public class AttendeeDashboardJourneyTest {

    @Test
     @Ignore("#ZOM010 - View Score is in progress")
     public void shouldViewDashboardWithScore(){
        // given an attendee logs in
        // TODO #ZOM010 - Jules and Charles - pending the user registration story, need to log in as real user.
        Browser browser = BrowserSessionBuilder.aBrowserSession().usingHttps().loggedInAsAdmin().build();
        // when they view the dashboard
        // then they can see the current score
    }

    @Test
    @Ignore("#ZOM010 - View Score is in progress")
    public void shouldUpdateDashBoardWithScore(){
    }


}
