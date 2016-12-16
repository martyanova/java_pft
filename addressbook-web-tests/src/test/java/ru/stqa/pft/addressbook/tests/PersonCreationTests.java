package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validPersonFromJson() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("L:\\Devel\\java_pft\\addressbook-web-tests\\src\\test\\resources\\persons.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<PersonData> groups = gson.fromJson(json, new TypeToken<List<PersonData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

        }
    }

    @DataProvider
    public Iterator<Object[]> validPersons() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("L:\\Devel\\java_pft\\addressbook-web-tests\\src\\test\\resources\\persons.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(PersonData.class);
            List<PersonData> persons = (List<PersonData>) xStream.fromXML(xml);
            return persons.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }
    @Test(dataProvider = "validPersons")
    public void PersonCreationTests(PersonData person) {

        app.goTo().personPage();
        Persons before = app.person().all();
        File photo = new File("src/test/resources/Koala.jpg");
        //PersonData person = new PersonData().withFirstname("test_1").withLastname("test2").withGroup("[none]").withWork("111").withMobile("222").withHome("777").withPhoto(photo);
        app.person().create(person);
        app.person().returnToPersonPage();
        Persons after = app.person().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(person.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
    public void  testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/Koala.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}