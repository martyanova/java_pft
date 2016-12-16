package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Александр on 06.11.2016.
 */
public class ApplicationManager {

  WebDriver wd;
  private final Properties properties;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private PersonHelper personHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {

    //String browser = BrowserType.CHROME;
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("L:/Devel/java_pft/addressbook-web-tests/src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    personHelper = new PersonHelper(navigationHelper.wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));



  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;

  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public PersonHelper person() {
    return personHelper;
  }

  public DbHelper db(){
    return dbHelper;
  }
}
