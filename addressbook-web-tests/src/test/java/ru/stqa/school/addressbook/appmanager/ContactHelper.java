package ru.stqa.school.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void returnToHomePage () {
      click(By.linkText("home page"));
    }

  public void submitContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation ) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));

  }
  }

  public void initContact() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void deleteSelectedContact() { click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void editContact() { click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContactForm() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

  public void createContact(ContactData contact) {

    initContact();
    fillContactForm(contact, true);
    submitContactForm();
    returnToHomePage();
  }

  public boolean isThereAContact() {

    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {

    List<ContactData> contacts  = new ArrayList<ContactData>();
    List<WebElement>  elements = wd.findElements(By.cssSelector("input[name='selected[]']"));
    for (WebElement element : elements) {
      String firstname = element.getText();
      String lastname = element.getText();
      ContactData contact = new ContactData(firstname, lastname, null, null, null );
      contacts.add(contact);


    }

    return contacts;


  }
}

