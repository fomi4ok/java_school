package ru.stqa.school.addressbook.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withLastname( "last1123").withFirstname("test name").withHomephone("332-456-2809").withMobilePhone("332-456-2809")
              .withWorkPhone("349-383-38")
              .withEmail("test2@test.com").withGroup("test1"));
    }

  }

  @Test
  public void testContactPhones(){

    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));


  }


  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll( "[-()]","");
  }

}
