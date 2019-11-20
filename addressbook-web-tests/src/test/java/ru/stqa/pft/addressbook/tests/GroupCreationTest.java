package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupTab();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", null, "test1.2"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupTab();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertTrue(after == before + 1);
    }
}