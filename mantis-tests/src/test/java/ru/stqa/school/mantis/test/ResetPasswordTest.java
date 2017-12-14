package ru.stqa.school.mantis.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.school.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
      app.mail().start();

    }



@Test
  public void testResetPassword() throws IOException, MessagingException, InterruptedException {

  long now = System.currentTimeMillis();
  String username = String.format("user%s", now);
  String user_password = "password";
  String user_email = String.format("user%s@localhost.localdomain", now);
  app.registration().start(username, user_email);
  List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
  String registrationConfirmationLink = findConfirmationLink(mailMessages, user_email);
  app.registration().finish(registrationConfirmationLink, user_password);

  //request password reset
  String admin_username = app.getProperty("web.adminLogin").toString();
  String admin_password = app.getProperty("web.adminPassword").toString();
  app.reset().adminsignin(admin_username, admin_password);
  app.reset().passwordForUser(username);
  List<MailMessage> mailMessages2 = app.mail().waitForMail(2, 10000);
  String resetConfirmationLink = findResetPasswordLink(mailMessages2, user_email);
  String new_user_password = "new_password";
  app.reset().finish(resetConfirmationLink, new_user_password);
  assertTrue(app.newSession().login(username, new_user_password));

}

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);

  }

  private String findResetPasswordLink(List<MailMessage> mailMessages, String user_email) {
    // find second email to this user_email (skip registration verification email)
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(user_email)).collect(Collectors.toList()).get(1);
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);


  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }




}