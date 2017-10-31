package ru.stqa.school.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test

  public void testContactModification() {

    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm((new ContactData("updated name", "updated last name", "332-456-2809", "test2@test.com")));
    app.getContactHelper().updateContactForm();

  }

}
