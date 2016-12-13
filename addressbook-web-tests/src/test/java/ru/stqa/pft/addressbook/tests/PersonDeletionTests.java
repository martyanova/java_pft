package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class PersonDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().personPage();
    if (app.person().all().size()==0){
      app.person().create(new PersonData().withFirstname("test_1").withLastname("test2"));
    }
  }

  @Test
  public void testPersonDeletion() {

    Persons before = app.person().all();
    PersonData deletedPerson = before.iterator().next();
    app.person().delete(deletedPerson);
    Persons after = app.person().all();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedPerson)));
  }


}
