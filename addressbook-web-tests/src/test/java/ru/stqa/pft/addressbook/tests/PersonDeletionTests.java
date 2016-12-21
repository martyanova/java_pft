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
    if (app.db().persons().size()==0){
      app.goTo().personPage();
      app.person().create(new PersonData().withFirstname("test_1").withLastname("test2"));
    }
  }

  @Test
  public void testPersonDeletion() {

    Persons before = app.db().persons();
    PersonData deletedPerson = before.iterator().next();
    app.person().delete(deletedPerson);
    Persons after = app.db().persons();
    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.without(deletedPerson)));
    verifyPersonListInUI();
  }


}
