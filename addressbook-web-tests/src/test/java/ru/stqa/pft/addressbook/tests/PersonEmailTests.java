package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Саша on 14.12.2016.
 */
public class PersonEmailTests extends TestBase{

    @Test
    public void testPersonAddress(){
        app.goTo().personPage();
        PersonData person = app.person().all().iterator().next();
        PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

        assertThat(person.getAllEmails(), equalTo(mergeEmail(personInfoFromEditForm)));

    }

    private String mergeEmail(PersonData person) {

        return Arrays.asList(person.getEmail1(), person.getEmail2(), person.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(PersonEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String email){
        return email.replaceAll("\\s", "");
    }
    }
