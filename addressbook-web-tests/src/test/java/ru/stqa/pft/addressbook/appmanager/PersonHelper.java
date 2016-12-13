package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.PersonData;
import ru.stqa.pft.addressbook.model.Persons;

import java.util.List;

/**
 * Created by Александр on 06.11.2016.
 */
public class PersonHelper extends HelperBase{

  public PersonHelper(WebDriver wd) {
    super(wd);
  }

  public void fillPersonForm(PersonData personData, boolean creation) {
    type(By.name("firstname"), personData.getFirstname());
    type(By.name("middlename"), personData.getMiddlename());
    type(By.name("lastname"), personData.getLastname());
    type(By.name("nickname"), personData.getNickname());
    //click(By.name("company"));
    type(By.name("title"), personData.getTitle());
    type(By.name("company"), personData.getCompany());
    type(By.name("address"), personData.getAddress());
    //click(By.name("theform"));
    type(By.name("home"), personData.getHome());
    type(By.name("mobile"), personData.getMobile());
    type(By.name("work"), personData.getWork());
    type(By.name("fax"), personData.getFax());

    if (creation) new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(personData.getGroup());
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
   // click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initPersonCreation() {
    click(By.linkText("add new"));
  }

  public void deletePerson() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectPersonById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void CloseWindow() {
    wd.switchTo().alert().accept();
  }

  public void initPersonModification() {

    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    //String xpath = "//table[@id='maintable']/tbody/tr[" + Integer.toString(id + 2) + "]/td[8]/a/img";
    //click(By.xpath(xpath));
  }

  public void submitPersonCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submitPersonModification() {
    click(By.name("update"));
  }

  public void returnToPersonPage() {
   click(By.linkText("home"));
  }

  public void create(PersonData person) {
    initPersonCreation();
    fillPersonForm(person,true);
    submitPersonCreation();
    returnToPersonPage();
  }

  public void modify(PersonData person) {
    selectPersonById(person.getId());
    initPersonModification();
    fillPersonForm(person, false);
    submitPersonModification();
    returnToPersonPage();
  }

  public void delete(PersonData person) {
    selectPersonById(person.getId());
    deletePerson();
    CloseWindow();
  }

  public boolean isThereAPerson() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getPersonCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Persons all() {
    Persons persons = new Persons();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[2]")).getText();
      PersonData person = new PersonData().withId(id).withFirstname(firstname).withLastname(lastname);
      persons.add(person);

    }
    return  persons;
  }


}
