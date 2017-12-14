package ru.stqa.school.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

  public ResetPasswordHelper (ApplicationManager app) {
    super(app);  }


  public void passwordForUser(String username) throws InterruptedException {
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText(username));
    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }

  public void adminsignin(String username, String password) throws InterruptedException {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void finish(String resetConfirmationLink, String user_password) {
    wd.get(resetConfirmationLink);
    type(By.name("password"), user_password);
    type(By.name("password_confirm"), user_password);
    click(By.className("bigger-110"));
  }

}
