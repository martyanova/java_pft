package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class PersonData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private  String firstname;
  @Transient
  private  String middlename;
  @Expose
  @Column(name = "lastname")
  private  String lastname;
  @Transient
  private  String nickname;
  @Transient
  private  String title;
  @Transient
  private  String company;
  @Transient
  private  String address;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private  String home;
  @Transient
  private  String work;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobile;
  @Transient
  private  String fax;
  @Transient
  private  String email1;
  @Transient
  private  String email2;
  @Transient
  private  String email3;
  @Transient
  private  String allPhones;
  @Transient
  private  String allEmails;
  @Transient
  private  String allDetails;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();


  /*@Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }

  public PersonData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }*/

  public String getAllDetails() {
    return allDetails;
  }

  public PersonData withAllDetails(String allDetails) {
    this.allDetails = allDetails;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public PersonData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getEmail1() {
    return email1;
  }

  public PersonData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public PersonData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public PersonData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public PersonData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public PersonData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public PersonData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public PersonData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public PersonData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public PersonData withTitle(String title) {
    this.title = title;
    return this;
  }

  public PersonData withCompany(String company) {
    this.company = company;
    return this;
  }

  public PersonData withAddress(String address) {
    this.address = address;
    return this;
  }

  public PersonData withHome(String home) {
    this.home = home;
    return this;
  }

  public PersonData withWork(String work) {
    this.work = work;
    return this;
  }

  public PersonData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public PersonData withFax(String fax) {
    this.fax = fax;
    return this;
  }

 // public PersonData withGroup(String group) {
  //  this.group = group;
  //  return this;
 // }

  public int getId() {
    return id;
  }

  public PersonData withId(int id) {
    this.id = id;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getWork() {
    return work;
  }

  public String getMobile() {
    return mobile;
  }

  public String getFax() {
    return fax;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  //public String getGroup() {
   // return group;
  //}

  @Override
  public String toString() {
    return "PersonData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PersonData that = (PersonData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  public PersonData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
