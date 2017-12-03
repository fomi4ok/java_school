package ru.stqa.school.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;

    }
    generator.run();
  }
  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println(" Unrecognized format" + format);
    }}

  private List<ContactData> generateContacts(int count) {

    List<ContactData> contact = new ArrayList<ContactData>();
    for (int i = 0; i< count; i++){
      contact.add(new ContactData().withFirstname(String.format("first name %s", i))
              .withLastname(String.format("last name %s", i)).withAddress(String.format("address %s", i)).withHomephone(String.format("345-455-5555 %s", i)).withMobilePhone(String.format("345-455-5555 %s", i))
              .withWorkPhone(String.format("345-455-5555 %s", i)).withEmail(String.format("test@email.com %s", i)).withEmail2(String.format("test@email.com %s", i))
              .withEmail3(String.format("test@email.com %s", i)));
    }
    return contact;

  }


  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
      System.out.println(new File(".").getAbsolutePath());
      try (Writer writer = new FileWriter(file)) {
        for (ContactData contact : contacts) {
          writer.write(String.format("%s;%s;%s;\n", contact.getLastname(), contact.getFirstname(), contact.getHomephone(), contact.getMobilePhone()
                  ,contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
      }
    }


  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }

  }

}
