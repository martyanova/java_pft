package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import ru.stqa.pft.addressbook.model.PersonData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саша on 15.12.2016.
 */
public class PersonDataGenerator {

    @Parameter(names = "-c", description = "Person count" )
    public int count;

    @Parameter (names = "-f", description = "Target file" )
    public String file;

    @Parameter (names = "-d", description = "Data format" )
    public String format;

    public static void  main (String[] args) throws IOException {
        PersonDataGenerator generator = new PersonDataGenerator();
        JCommander jCommander = new JCommander(generator);

        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
        //int count = Integer.parseInt(args[0]);
        //File file = new File(args[1]);

    }

    private void run() throws IOException {
        List<PersonData> persons = generatePersons(count);
        if (format.equals("csv")){
            saveAsCSV(persons, new File(file));
        }else if (format.equals("xml")){
            saveAsXML(persons, new File(file));
        }else if (format.equals("json")){
            saveAsJSON(persons, new File(file));
        }else{
            System.out.println("Unrecognized format" + format);

        }
    }

    private void saveAsJSON(List<PersonData> persons, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(persons);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<PersonData> persons, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(PersonData.class);
        String xml = xStream.toXML(persons);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }


    private  void saveAsCSV(List<PersonData> persons, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (PersonData person : persons) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", person.getFirstname(), person.getLastname(), person.getWork(), person.getMobile(), person.getHome()));

            }
        }
    }

    private  List<PersonData> generatePersons(int count) {
        List<PersonData> persons = new ArrayList<PersonData>();
        for (int i = 0; i< count; i++){
            persons.add(new PersonData().withFirstname(String.format("test %s", i))
                    .withLastname(String.format("Lastname %s", i)).withWork(String.format("Work %s", i))
                    .withMobile(String.format("Mobile %s", i)).withHome(String.format("Home %s", i)));
                    //.withGroup("[none]"));
        }
        return persons;
    }
}
