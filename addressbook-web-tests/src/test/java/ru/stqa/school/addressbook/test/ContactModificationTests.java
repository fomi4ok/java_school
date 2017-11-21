package ru.stqa.school.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();

    if ( app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("aaa2")
              .withLastname( "last1123").withHomephone("332-456-2809").withEmail("test2@test.com").withGroup("test1"));
    }

  }

  @Test

  public void testContactModification() {

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId())
            .withFirstname("updated test").withLastname( "updated last name").withHomephone("332-456-2809")
            .withEmail( "test2@test.com").withGroup( "test1");
    app.contact().modifyContact(before, index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);


  }



}
