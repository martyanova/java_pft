package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;

import static org.junit.Assert.assertTrue;

/**
 * Created by Саша on 21.12.2016.
 */
public class PersonAddToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().personPage();
        if (app.db().persons().size()==0){
            app.person().create(new PersonData().withFirstname("test_1").withLastname("test2"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testAddContactToGroup() {

        boolean success = false;

        for (PersonData person : app.db().persons()) {
            if (app.db().groups().size() != person.getGroups().size()) {
                app.person().selectPersonById(person.getId());
                for (GroupData group : app.db().groups()) {
                    if (! group.getPersons().contains(person)) {
                        app.person().selectGroupForAdd(group.getId());
                        app.person().addToSelectedGroup();
                        app.db().refresh(group);
                        assertTrue(group.getPersons().contains(person));
                        return;
                    }
                }
            }
        }
        app.person().create(new PersonData()
                .withFirstname("test_1").withLastname("test2"));
        int personId = app.db().persons().stream().mapToInt((c) -> c.getId()).max().getAsInt();
        app.person().selectPersonById(personId);
        GroupData group = app.db().groups().iterator().next();
        app.person().selectGroupForAdd(group.getId());
        app.person().addToSelectedGroup();
        app.db().refresh(group);
        for (PersonData contact : group.getPersons()) {
            if (contact.getId() == personId) {
                success = true;
            }
        }
        assertTrue(success);
    }

}
