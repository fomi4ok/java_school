package ru.stqa.school.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withLastname( "last1123").withFirstname("test name").withAddress(" 123 abc").withHomephone("332-456-2809").withMobilePhone("332-456-2809")
              .withWorkPhone("349-383-38")
              .withEmail("test1@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com").withGroup("test1"));
    }


  }


  @Test
  public void testContactAddress() {

    app.goTo().homePage();
  ContactData contact = app.db().contacts().iterator().next();
  ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));


  }
}
