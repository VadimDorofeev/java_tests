package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactGroupTest extends TestBase {

    @Test
    public void testContactGroup() {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData newContact = new ContactData().withFirstName("Ivan").withLastName("Ivanov").inGroup(groups.iterator().next());
        app.contact().createWithGroup(newContact);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream()
                .mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

}
