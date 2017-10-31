package ru.stqa.school.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{




  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void goToGroupCreation() {
      click(By.linkText("groups"));
  }
}