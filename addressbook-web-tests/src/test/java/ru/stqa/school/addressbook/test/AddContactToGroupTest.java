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

public class AddContactToGroupTest extends TestBase {

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
  public void testAddContactToGroup() {
    ContactData selectedContact = app.db().contacts().iterator().next();
    Groups before = selectedContact.getGroups();
    GroupData selectedGroup;
    Groups notAddedGroups = app.db().groups();
    notAddedGroups.removeAll(before);

    if (notAddedGroups.isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
      Groups notAddedGroups2 = app.db().groups();
      notAddedGroups2.removeAll(before);
      selectedGroup = notAddedGroups2.iterator().next();
    }
    else {
      selectedGroup = notAddedGroups.iterator().next();
    }

    app.goTo().homePage();
    app.contact().addContactToTheGroup(selectedContact, selectedGroup);
    // select the same group as before by id
    Groups after = app.db().contactById(selectedContact.getId()).iterator().next().getGroups();

    // compare
    assertThat(after, equalTo(before.withContact(selectedGroup, selectedContact)));
  }
}
