package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Саша on 14.12.2016.
 */
public class PersonAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().personPage();
        if (app.person().all().size()==0){
            app.person().create(new PersonData().withFirstname("test_1").withLastname("test2").withAddress("Бульвар Южный"));
        }
    }

    @Test

    public void testPersonAddress(){
        app.goTo().personPage();
        PersonData person = app.person().all().iterator().next();
        PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

        assertThat(person.getAddress(), equalTo(personInfoFromEditForm.getAddress()));
    }
}
