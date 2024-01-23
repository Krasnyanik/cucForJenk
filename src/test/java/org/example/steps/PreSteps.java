package org.example.steps;



import io.cucumber.java.Before;

import org.example.managers.DriverManager;

import org.example.managers.TestPropManager;




import static org.example.utils.PropConst.BASE_URL;

public class PreSteps {


    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();


    @Before (value = "@add")
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }




}
