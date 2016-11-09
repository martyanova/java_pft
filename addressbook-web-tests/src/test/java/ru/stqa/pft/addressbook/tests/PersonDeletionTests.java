package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;

public class PersonDeletionTests extends TestBase {

  @Test
  public void testPersonDeletion() {
    app.getNavigationHelper().gotoPersonPage();
    if (! app.getPersonHelper().isThereAPerson()){
      app.getPersonHelper().createPerson(new PersonData("test_1", "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test9", "test8", "test0", "test"));
    }
    app.getPersonHelper().selectPerson();
    app.getPersonHelper().deletePerson();
    app.getPersonHelper().CloseWindow();
  }
}
