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

    if (app.db().contactByName("'first name 0'").isEmpty()) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withLastname("first name 0").withFirstname("first name 0").withAddress("123 abc").withHomephone("332-456-2809").withMobilePhone("332-456-2809")
              .withWorkPhone("349-383-38")
              .withEmail("test1@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com"));
    }
    if (app.db().groupByName("'test1'").isEmpty()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    // select first match of a group by name
    Groups groups = app.db().groupByName("'test1'");
    GroupData selectedGroup = groups.iterator().next();
    // get the contacts for this group
    Contacts before = selectedGroup.getContacts();

    app.goTo().homePage();
    // select first match of a contact by name
    Contacts contacts = app.db().contactByName("'first name 0'");
    ContactData selectedContact = contacts.iterator().next();
    app.contact().addContactToTheGroup(selectedContact, selectedGroup.getName());
    // select the same group as before by id
    Groups after_groups = app.db().groupById(selectedGroup.getId());
    GroupData after_group = after_groups.iterator().next();
    // get the contacts for this group after contact addition
    Contacts after = after_group.getContacts();

    // compare
    assertThat(after, equalTo(before.withGroup(selectedGroup, selectedContact)));
  }
}
