package bdd;

import io.cucumber.java.en.Given;

public class Steps {

    @Given("I login to app")
    public void test(){
        System.out.println("login ...");
    }
    @Given("I create an account for test user")
    public void testCreateAccount(){
        System.out.println("Create account ...");
    }
}
