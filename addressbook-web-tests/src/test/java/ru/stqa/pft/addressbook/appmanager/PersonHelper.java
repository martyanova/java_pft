package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.PersonData;

/**
 * Created by Александр on 06.11.2016.
 */
public class PersonHelper extends HelperBase{

  public PersonHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void fillPersonForm(PersonData personData) {
    firstname(By.name("firstname"), personData.getFirstname());
    firstname(By.name("middlename"), personData.getMiddlename());
    firstname(By.name("lastname"), personData.getLastname());
    firstname(By.name("nickname"), personData.getNickname());
    click(By.name("company"));
    firstname(By.name("title"), personData.getTitle());
    firstname(By.name("company"), personData.getCompany());
    firstname(By.name("address"), personData.getAddress());
    click(By.name("theform"));
    firstname(By.name("home"), personData.getHome());
    firstname(By.name("mobile"), personData.getMobile());
    firstname(By.name("work"), personData.getWork());
    firstname(By.name("fax"), personData.getFax());
    click(By.xpath("//div[@id='content']/form/input[21]"));
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

  public void submitPersonModification() {
    click(By.name("update"));
  }
}
