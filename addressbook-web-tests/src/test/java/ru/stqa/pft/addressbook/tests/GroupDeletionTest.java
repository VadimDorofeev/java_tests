package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testUntitledTestCase() {
        app.goToGroupTab();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.goToGroupTab();
    }
}