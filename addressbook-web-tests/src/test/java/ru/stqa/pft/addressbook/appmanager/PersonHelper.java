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
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      persons.add(new PersonData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones));

    }
    return  persons;
  }


  public PersonData infoFromEditForm(PersonData person) {
    initPersonModificationById(person.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new PersonData().withId(person.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobile(mobile).withWork(work);
  }

  private void initPersonModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("a[href='wdit.php&id=%s']", id))).click();
  }
}
