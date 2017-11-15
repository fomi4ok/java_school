package ru.stqa.school.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test

  public void testContactModification() {

    app.getNavigationHelper().goToHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("name1", "last1", "332-456-2809", "test2@test.com", "updated group"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("updated name", "updated last name", "332-456-2809", "test2@test.com", null), false);
    app.getContactHelper().updateContactForm();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() );


  }

}
