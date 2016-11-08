package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.PersonData;

public class PersonCreationTests extends TestBase {

    @Test
    public void PersonCreationTests() {

        app.getPersonHelper().initPersonCreation();
        app.getPersonHelper().fillPersonForm(new PersonData("test_1", "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test9", "test8", "test0", "test"),true);
        app.getPersonHelper().submitPersonCreation();
    }

}