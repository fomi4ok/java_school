package ru.stqa.school.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withLastname( "last1123").withFirstname("first name").withAddress("123 w State street").withHomephone("332-456-2809").withEmail("test3@test.com").withGroup("test1"));
    }

  }


  @Test
  public void testContactAddress() {

    app.goTo().homePage();
  ContactData contact = app.contact().all().iterator().next();
  ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));


  }
}
