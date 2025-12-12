package tests;

import Utilites.utility;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class injectionapi {

    @BeforeTest
    public void register() {
        utility.injectionapi();
    }

    @Test
    public void login() {

    }
}
