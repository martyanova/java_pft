package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class PersonCreationTests extends TestBase {

    @Test
    public void PersonCreationTests() {

        app.getNavigationHelper().gotoPersonPage();
        List<PersonData> before = app.getPersonHelper().getPersonList();

        PersonData person = new PersonData("test_1", "test1", "test2", "test4", "test5", "test6", "test7", "test9", "test8", "test0", "test","[none]");
        app.getPersonHelper().createPerson(person);
        app.getPersonHelper().returnToPersonPage();
        List<PersonData> after = app.getPersonHelper().getPersonList();
        Assert.assertEquals(after.size(), before.size() + 1);

        //person.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(person);
        Comparator<? super PersonData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        //app.getPersonHelper().fillPersonForm(new PersonData("test_1", "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test9", "test8", "test0", "test"),true);
       // app.getPersonHelper().submitPersonCreation();
    }

}