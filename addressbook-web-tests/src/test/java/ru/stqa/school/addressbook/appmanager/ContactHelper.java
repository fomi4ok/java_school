package ru.stqa.school.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;
import ru.stqa.school.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void returnToHomePage () {
      click(By.linkText("home page"));
    }


  public void submitContactForm() { click(By.xpath("//div[@id='content']/form/input[21]"));}

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());


   /* if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));

  }*/
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

    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

  }

  public void updateContactForm() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

  public void create(ContactData contact) {

    initContact();
    fillContactForm(contact);
    submitContactForm();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    editContactById(contact.getId());
    fillContactForm(contact);
    updateContactForm();
    contactCache = null;
    returnToHomePage();
  }


  public void delete(ContactData contact) {

    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptAlert();
    contactCache = null;
  }

  public void submitAddToGroup(){
    click(By.name("add"));
  }

  public void addContactToTheGroup(ContactData contact, GroupData group){
    selectContactById(contact.getId());
    selectToGroupById(group.getId());
    submitAddToGroup();

  }


  public void removeContactFromTheGroup(ContactData contact, GroupData group){
    selectGroupById(group.getId());
    selectContactById(contact.getId());
    removeContact();

  }

  private void removeContact() {
    click(By.name("remove"));

  }

  public void selectGroupById(int id){
    new Select(wd.findElement(By.name("group"))).selectByValue("" + id);

  }

  public void selectToGroupById(int id){
    new Select(wd.findElement(By.name("to_group"))).selectByValue("" + id);

  }



  public boolean isThereAContact() {

    return isElementPresent(By.name("selected[]"));


  }

  public int count() {

    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache  = new Contacts();
    List<WebElement>  elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElements(By.tagName("td")).get(2).getText();
      String lastname = element.findElements(By.tagName("td")).get(1).getText();
      String address = element.findElements(By.tagName("td")).get(3).getText();
      String allPhones = element.findElements(By.tagName("td")).get(5).getText();
      String allEmails = element.findElements(By.tagName("td")).get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).
              withLastname(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
      contactCache.add(contact);

    }

    return contactCache;


  }


  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).
            withLastname(lastname).withAddress(address).withHomephone(home)
            .withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3);


  }





  }




