package ru.stqa.school.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
    public void testGroupCreation() {


        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals( after, before + 1);

    }

}
