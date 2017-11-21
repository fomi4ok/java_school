package ru.stqa.school.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test (enabled = false)
  public void testContactCreation() {

    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("aaa2", "last1123", "332-456-2809", "test2@test.com", "test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
