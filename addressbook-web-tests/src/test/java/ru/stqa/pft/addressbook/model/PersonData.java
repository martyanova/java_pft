package ru.stqa.pft.addressbook.model;

public class PersonData {
  private int id = Integer.MAX_VALUE;
  private  String firstname;
  private  String middlename;
  private  String lastname;
  private  String nickname;
  private  String title;
  private  String company;
  private  String address;
  private  String home;
  private  String work;
  private  String mobile;
  private  String fax;
  private  String group;
  private  String allPhones;

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

  public PersonData withGroup(String group) {
    this.group = group;
    return this;
  }

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

  public String getGroup() {
    return group;
  }

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
}
