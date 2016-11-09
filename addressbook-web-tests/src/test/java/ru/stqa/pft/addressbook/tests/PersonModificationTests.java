package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

/**
 * Created by Александр on 06.11.2016.
 */
public class PersonModificationTests extends TestBase {

  @Test
  public void testPersonModification(){
    app.getNavigationHelper().gotoPersonPage();
    if (! app.getPersonHelper().isThereAPerson()){
      app.getPersonHelper().createPerson(new PersonData("test_1", "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test9", "test8", "test0", "test"),true);
    }
    app.getPersonHelper().selectPerson();
    app.getPersonHelper().initPersonModification();
    app.getPersonHelper().fillPersonForm(new PersonData("test", "test1", "test2", "test3", "test4", "test5", "test10", "test7", "test9", "test8", "test0", null), false);
    app.getPersonHelper().submitPersonModification();
    app.getPersonHelper().returnToPersonPage();
  }
}
