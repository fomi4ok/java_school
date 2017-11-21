package ru.stqa.school.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("aaa2")
            .withLastname( "last1123").withHomephone("332-456-2809").withEmail("test2@test.com").withGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c)-> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

  }

}
