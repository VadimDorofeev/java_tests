package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testUntitledTestCase() {
        app.getNavigationHelper().goToGroupTab();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupTab();
    }
}