package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Александр on 06.11.2016.
 */
public class ApplicationManager {

  WebDriver wd;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private PersonHelper personHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {

    //String browser = BrowserType.CHROME;
    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    personHelper = new PersonHelper(navigationHelper.wd);
    sessionHelper.login("admin", "secret");

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
}
