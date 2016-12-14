package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Саша on 14.12.2016.
 */
public class PersonAddressTests extends TestBase {

    @Test

    public void testPersonAddress(){
        app.goTo().personPage();
        PersonData person = app.person().all().iterator().next();
        PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

        assertThat(person.getAddress(), equalTo(personInfoFromEditForm.getAddress()));
    }
}
