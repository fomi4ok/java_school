package ru.stqa.school.addressbook.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.school.addressbook.appmanager.ApplicationManager;
import ru.stqa.school.addressbook.model.ContactData;

public class TestBase {


  protected final ApplicationManager app = new ApplicationManager();
  FirefoxDriver wd;


  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
