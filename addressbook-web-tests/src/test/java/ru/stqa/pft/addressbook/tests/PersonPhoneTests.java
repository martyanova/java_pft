package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class PersonPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().personPage();
        if (app.person().all().size()==0){
            app.person().create(new PersonData().withFirstname("test_1").withLastname("test2").withHome("8312568978")
            .withMobile("895012345678").withWork("+4951234578"));
        }
    }

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
