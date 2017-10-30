package ru.stqa.school.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

public class contactCreation extends TestBase {


  @Test
  public void testContactCreation() {


    app.getContactHelper().initContact();
    app.getContactHelper().fillContactForm(new ContactData("name1", "last1", "332-456-2809", "test2@test.com"));
    app.getContactHelper().submitContactForm();
    app.getContactHelper().returnToHomePage();

  }

}
