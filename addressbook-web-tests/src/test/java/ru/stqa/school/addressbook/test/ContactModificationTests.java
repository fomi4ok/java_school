package ru.stqa.school.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();

    if ( app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("aaa2")
              .withLastname( "last1123").withHomephone("332-456-2809").withEmail("test2@test.com").withGroup("test1"));
    }

  }

  @Test

  public void testContactModification() {

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("updated test").withLastname( "updated last name").withHomephone("332-456-2809")
            .withEmail( "test2@test.com").withGroup( "test1");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);

    Assert.assertEquals(before, after);


  }



}
