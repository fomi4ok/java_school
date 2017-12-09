package ru.stqa.school.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.school.addressbook.model.ContactData;
import ru.stqa.school.addressbook.model.Contacts;
import ru.stqa.school.addressbook.model.GroupData;
import ru.stqa.school.addressbook.model.Groups;

import java.util.List;


public class DbHelper {


  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);

  }

  public Groups groupByName(String group_name) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where name = " + group_name).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Groups groupById(Integer group_id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where id = " + group_id).list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
         session.beginTransaction();
         List<ContactData> result = session.createQuery("from ContactData where deprecated ='0000-00-00'").list();
         session.getTransaction().commit();
         session.close();
         return new Contacts(result);
  }

  public Contacts contactByName(String contact_name) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated ='0000-00-00' and  firstname = " + contact_name).list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Contacts contactById(Integer contact_id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where  deprecated ='0000-00-00' and id = " + contact_id).list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }




}


