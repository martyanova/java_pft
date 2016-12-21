package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Саша on 21.12.2016.
 */
public class PersonDelFromGroup extends  TestBase{

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
    public void testDelPersonFromGroup() {
        for (GroupData group : app.db().groups()) {
            if (! group.getPersons().isEmpty()) {
                Persons before = (Persons) group.getPersons();
                app.person().selectGroup(group.getId());
                PersonData delPerson = before.iterator().next();
                app.person().selectPersonById(delPerson.getId());
                app.person().delSelectedPersonFromGroup();
                app.goTo().personPage();
                app.db().refresh(group);
                Persons after = (Persons) group.getPersons();
                assertThat(after, equalTo(before.without(delPerson)));
                return;
            }
        }
        PersonData person = app.db().persons().iterator().next();
        app.person().selectPersonById(person.getId());
        GroupData group = app.db().groups().iterator().next();
        app.person().selectGroupForAdd(group.getId());
        app.person().addToSelectedGroup();
        app.goTo().personPage();
        app.db().refresh(group);
        assertThat(group.getPersons().size(), equalTo(1));
        app.person().selectGroup(group.getId());
        app.person().selectPersonById(person.getId());
        app.person().delSelectedPersonFromGroup();
        app.goTo().personPage();
        app.db().refresh(group);
        assertThat(group.getPersons().size(), equalTo(0));
    }

}
