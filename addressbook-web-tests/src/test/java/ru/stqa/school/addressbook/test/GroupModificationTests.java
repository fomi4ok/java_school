package ru.stqa.school.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.GroupData;


public class GroupModificationTests extends TestBase {


  @Test

  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("updated group", "updated", "group"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

  }
}

