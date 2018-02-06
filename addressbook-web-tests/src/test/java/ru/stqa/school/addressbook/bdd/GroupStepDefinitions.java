package ru.stqa.school.addressbook.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.stqa.school.addressbook.appmanager.ApplicationManager;

public class GroupStepDefinitions {

  private ApplicationManager app;

  @Before
  public void init(){

  }
  @After
  public void stop(){

  }
  @Given("^a set of groups$")
  public void loadGroups() {

  }
  @When("")
  public void createGroup(String name, String header, String footer) {

  }

  @Then("")
  public void verifyGroupCreated() {


  }
}
