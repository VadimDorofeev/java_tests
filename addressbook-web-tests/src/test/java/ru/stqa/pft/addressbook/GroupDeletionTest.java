package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testUntitledTestCase() throws Exception {
        goToGroupTab();
        selectGroup();
        deleteSelectedGroups();
        goToGroupTab();
    }

}


