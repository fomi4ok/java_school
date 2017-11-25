package ru.stqa.school.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withLastname( "last1123").withFirstname("test name").withAddress(" 123 abc").withHomephone("332-456-2809").withMobilePhone("332-456-2809")
              .withWorkPhone("349-383-38")
              .withEmail("test1@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com").withGroup("test1"));
    }

  }

  @Test
  public void testContactEmail() {

    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));


  }

  private String mergeEmails(ContactData contact) {

    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));


}

}
