package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.PersonData;

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

  public void selectPerson() {
    click(By.name("selected[]"));
  }

  public void CloseWindow() {
    wd.switchTo().alert().accept();
  }

  public void initPersonModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitPersonCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submitPersonModification() {
    click(By.name("update"));
  }
}
