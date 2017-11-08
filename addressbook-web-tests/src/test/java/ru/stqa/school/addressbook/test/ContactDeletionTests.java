package ru.stqa.school.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {

    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("name1", "last1", "332-456-2809", "test2@test.com", "updated group"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();


  }
}
