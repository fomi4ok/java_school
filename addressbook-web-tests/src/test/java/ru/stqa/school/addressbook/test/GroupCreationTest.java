package ru.stqa.school.addressbook.test;


import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

}
