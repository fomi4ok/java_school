package ru.stqa.school.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("name1", "last1", "332-456-2809", "test2@test.com", "test1"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

  }

}
