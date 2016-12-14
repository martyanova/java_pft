package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Саша on 14.12.2016.
 */
public class PersonDetailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().personPage();
        if (app.person().all().size()==0){
            app.person().create(new PersonData().withFirstname("test_1").withLastname("test2").withHome("8312568978")
                    .withMobile("895012345678").withWork("+4951234578").withAddress("Бульвар Южный")
                    .withEmail1("1@mail.ru").withEmail2("2@mail.ru").withEmail3("3@mail.ru"));
        }
    }
    @Test
    public void testPersonAddress() {
        app.goTo().personPage();
        PersonData person = app.person().all().iterator().next();
        PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);
        PersonData personInfoDetails = app.person().infoDetailForm(person);

        MatcherAssert.assertThat((merge(personInfoDetails)), CoreMatchers.equalTo(merge(personInfoDetails)));

    }

    private String merge(PersonData person) {

        return Arrays.asList(person.getLastname(), person.getFirstname(), person.getAddress(),
                person.getHome(), person.getMobile(), person.getWork(), person.getEmail1(), person.getEmail2(), person.getEmail3()).stream()
                .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }

}