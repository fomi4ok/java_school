package ru.stqa.school.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;
import ru.stqa.school.addressbook.model.GroupData;
import ru.stqa.school.addressbook.model.Groups;

import java.security.acl.Group;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase{
  @BeforeMethod
  public void ensurePreconditionsForContacts() {
    if (app.db().contacts().isEmpty()) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withLastname("first name 0").withFirstname("first name 0").withAddress("123 abc").withHomephone("332-456-2809").withMobilePhone("332-456-2809")
              .withWorkPhone("349-383-38")
              .withEmail("test1@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com"));
    }
    if (app.db().groups().isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }



  @Test
  public void testRemoveContactFromGroup() {
    ContactData selectedContact = app.db().contacts().iterator().next();
    Groups before = selectedContact.getGroups();
    GroupData selectedGroup;

    if (before.isEmpty()) {
      selectedGroup = app.db().groups().iterator().next();
      app.goTo().homePage();
      app.contact().addContactToTheGroup(selectedContact, selectedGroup);
    }
    else {
      selectedGroup = before.iterator().next();
    }

    app.goTo().homePage();
    app.contact().removeContactFromTheGroup(selectedContact, selectedGroup);

    Groups after = app.db().contactById(selectedContact.getId()).iterator().next().getGroups();

    // compare
    assertThat(after, equalTo(before.withoutContact(selectedGroup, selectedContact)));
  }

}
