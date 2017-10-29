package ru.stqa.school.addressbook.test;


import org.testng.annotations.Test;
import ru.stqa.school.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase {

  @Test
    public void testGroupCreation() {
        app.goToGroupCreation();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "tes2", "test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
