package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Александр on 06.11.2016.
 */
public class PersonModificationTests extends TestBase {

  @Test
  public void testPersonModification(){

    app.getNavigationHelper().gotoPersonPage();
    if (! app.getPersonHelper().isThereAPerson()){
      app.getPersonHelper().createPerson(new PersonData("test_1", "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test9", "test8", "test0", "test"));
    }
    List<PersonData> before = app.getPersonHelper().getPersonList();
    //app.getPersonHelper().selectPerson(before.size() - 1);
    app.getPersonHelper().initPersonModification(before.size() - 1);
    PersonData person = new PersonData(before.get(before.size()-1).getId(),"test_1", "test1", "test2", null, null, null, null, null, null, null, null, null);
    app.getPersonHelper().fillPersonForm(person, false);
    app.getPersonHelper().submitPersonModification();
    app.getPersonHelper().returnToPersonPage();

    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(person);
    Comparator<? super PersonData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
