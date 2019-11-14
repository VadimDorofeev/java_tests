package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        goToGroupTab();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test1.1", "test1.2"));
        submitGroupCreation();
        goToGroupTab();
    }

}
