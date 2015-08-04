package com.baselogic.tutorials.controller;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration/
 *
 * http://www.petrikainulainen.net/spring-mvc-test-tutorial/
 *
 * http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration/
 *
 * http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
 *
 *
 * @author Petri Kainulainen
 */
public class HomeControllerIT {

    private HomeController controller;

    @Before
    public void setUp() {
        controller = new HomeController();
    }

    @Test
    public void showPage() {
        String homeView = controller.showPage();
        assertEquals(HomeController.HOME_VIEW, homeView);
    }
}
