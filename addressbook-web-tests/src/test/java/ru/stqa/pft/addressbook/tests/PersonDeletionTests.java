package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class PersonDeletionTests extends TestBase {

  @Test
  public void testPersonDeletion() {
    app.getNavigationHelper().gotoPersonPage();
    app.getPersonHelper().selectPerson();
    app.getPersonHelper().deletePerson();
    app.getPersonHelper().CloseWindow();
  }


}
