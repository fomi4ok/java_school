package ru.stqa.school.addressbook;


import org.testng.annotations.Test;



public class GroupCreationTest extends TestBase {

  @Test
    public void testGroupCreation() {
        goToGroupCreation();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "tes2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
