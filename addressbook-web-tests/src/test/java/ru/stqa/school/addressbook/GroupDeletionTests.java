package ru.stqa.school.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    
    @Test
    public void testGroupDeletion() {

        goToGroupCreation();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }


}
