package ru.stqa.school.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("aaa2")
              .withLastname( "last1123").withHomephone("332-456-2809").withEmail("test2@test.com"));
    }
  }

  @Test

  public void testContactDeletion() {

    Contacts before = app.db().contacts();
    ContactData deleteContact = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deleteContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deleteContact)));
    verifyContactListInUI();

  }


}
