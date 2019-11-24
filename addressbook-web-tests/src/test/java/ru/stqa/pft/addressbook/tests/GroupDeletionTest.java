package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testUntitledTestCase() {
        app.getNavigationHelper().goToGroupTab();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", "test1.1", "test1.2"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupTab();
        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertTrue(after.size() == before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}