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

    type(By.name("email"), personData.getEmail1());
    type(By.name("email2"), personData.getEmail2());
    type(By.name("email3"), personData.getEmail3());

    attach(By.name("photo"), personData.getPhoto());

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
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      persons.add(new PersonData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));

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
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new PersonData().withId(person.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobile(mobile).withWork(work).withAddress(address)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
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
  public PersonData infoDetailForm(PersonData person){
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", person.getId()))).click();
    String info[] = wd.findElement(By.id("content")).getText().replaceAll("[HMW:]", "")
                        .replaceAll("\\n+\\s*", "\n").replaceFirst(" ", "\n").split("\n");

    wd.navigate().back();

    return new PersonData().withId(person.getId()).withFirstname(info [0]).withLastname(info [1])
                        .withAddress(info [2]).withHome(info [3]).withMobile(info [4]).withWork(info [5])
                        .withEmail1(info [6]).withEmail2(info [7]).withEmail3(info [8]);
  }
}
