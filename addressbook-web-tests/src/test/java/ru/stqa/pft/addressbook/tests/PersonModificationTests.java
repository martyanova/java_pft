package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
    app.goTo().personPage();
    if (app.person().all().size()==0){
      app.person().create(new PersonData().withFirstname("test_1").withLastname("test2"));
    }
  }

  @Test
  public void testPersonModification(){
    Persons before = app.person().all();
    PersonData modifiedPerson = before.iterator().next();
    PersonData person = new PersonData()
            .withId(modifiedPerson.getId()).withFirstname("test_1").withLastname("test2").withMiddlename("test3");
    app.person().modify(person);

    Persons after = app.person().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedPerson).withAdded(person)));

  }


}
