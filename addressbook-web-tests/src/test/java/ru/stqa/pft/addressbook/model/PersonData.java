package ru.stqa.pft.addressbook.model;

public class PersonData {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String work;
  private final String mobile;
  private final String fax;
  private String group;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PersonData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String work, String mobile, String fax, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;

    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.work = work;
    this.mobile = mobile;
    this.fax = fax;
    this.group = group;
  }

  public PersonData(int id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String work, String mobile, String fax, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.work = work;
    this.mobile = mobile;
    this.fax = fax;
    this.group = group;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PersonData that = (PersonData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "PersonData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
