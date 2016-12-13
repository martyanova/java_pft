package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonCreationTests extends TestBase {

    @Test
    public void PersonCreationTests() {

        app.goTo().personPage();
        Persons before = app.person().all();
        PersonData person = new PersonData().withFirstname("test_1").withLastname("test2").withGroup("[none]");
        app.person().create(person);
        app.person().returnToPersonPage();
        Persons after = app.person().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(person.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}