package ru.stqa.school.addressbook.test;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    
    @Test
    public void testGroupDeletion() {

        app.goToGroupCreation();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.returnToGroupPage();
    }


}
