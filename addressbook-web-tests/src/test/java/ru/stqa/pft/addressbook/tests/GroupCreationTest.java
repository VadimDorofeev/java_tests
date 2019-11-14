package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goToGroupTab();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test1.1", "test1.2"));
        app.submitGroupCreation();
        app.returnToGroupTab();
    }
}