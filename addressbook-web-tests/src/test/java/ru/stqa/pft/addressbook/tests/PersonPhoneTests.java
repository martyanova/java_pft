package ru.stqa.pft.addressbook.tests;

import org.apache.xerces.impl.xpath.regex.Match;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class PersonPhoneTests extends TestBase {

    @Test
    public void testPersonPhones(){

        app.goTo().personPage();
        PersonData person = app.person().all().iterator().next();
        PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

        assertThat(person.getAllPhones(), equalTo(mergePhones(personInfoFromEditForm)));

    }

    private String mergePhones(PersonData person) {
        return Arrays.asList(person.getHome(), person.getMobile(), person.getWork())
                .stream().filter((s) -> ! s.equals(""))
                .map(PersonPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }


    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
   }
}
