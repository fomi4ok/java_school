package ru.stqa.school.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("aaa2")
              .withLastname( "last1123").withHomephone("332-456-2809").withEmail("test2@test.com"));
    }

  }

  @Test

  public void testContactModification() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("updated test").withLastname( "updated last name").withWorkPhone("332-456-2809").withMobilePhone("332-456-2809").withAddress("123 abc").withEmail2("test1@test.com").withEmail3("test2@test.com").withHomephone("332-456-2809").withEmail("test0@test.com");
    app.goTo().homePage();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()));
    //Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();






  }



}
