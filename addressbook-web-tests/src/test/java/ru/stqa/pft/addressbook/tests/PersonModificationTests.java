package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Александр on 06.11.2016.
 */
public class PersonModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    if (app.db().persons().size()==0){
      app.goTo().personPage();
      app.person().create(new PersonData().withFirstname("test_1").withLastname("test2"));
    }
  }

  @Test
  public void testPersonModification(){
    Persons before = app.db().persons();
    PersonData modifiedPerson = before.iterator().next();
    PersonData person = new PersonData()
            .withId(modifiedPerson.getId()).withFirstname("test_1").withLastname("test2").withMiddlename("test3");
    app.goTo().personPage();
    app.person().modify(person);
    Persons after = app.db().persons();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedPerson).withAdded(person)));
    verifyPersonListInUI();

  }




}
