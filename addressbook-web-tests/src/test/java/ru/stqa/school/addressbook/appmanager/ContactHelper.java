package ru.stqa.school.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;
import ru.stqa.school.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


  public void selectContactById(int id) {

    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

  }

  public void deleteSelectedContact() { click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void editContactById(int id) {
    //click(By.xpath("//table[@id='maintable']/tbody/tr[" + id + "]/td[8]/a/img"));
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr['" + id + "']/td[8]/a/img")).click();

  }

  public void updateContactForm() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

  public void create(ContactData contact) {

    initContact();
    fillContactForm(contact, true);
    submitContactForm();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    editContactById(contact.getId());
    fillContactForm(contact, false);
    updateContactForm();
    returnToHomePage();
  }


  public void delete(ContactData contact) {

    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptAlert();
  }

  public boolean isThereAContact() {

    return isElementPresent(By.name("selected[]"));


  }

  public Contacts all() {

    Contacts contacts  = new Contacts();
    List<WebElement>  elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElements(By.tagName("td")).get(2).getText();
      String lastname = element.findElements(By.tagName("td")).get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);

    }

    return contacts;


  }


}

