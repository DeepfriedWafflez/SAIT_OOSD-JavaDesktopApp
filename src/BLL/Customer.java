package BLL;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    //properties
    private int id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty address;
    private SimpleStringProperty city;
    private SimpleStringProperty province;
    private SimpleStringProperty postal;
    private SimpleStringProperty country;
    private SimpleStringProperty homePhone;
    private SimpleStringProperty busPhone;
    private SimpleStringProperty email;
    private int agent;

    //constructor
    public Customer(int id, String fname, String lname, String address, String city, String prov, String postal,
                    String country, String hPhone, String bPhone, String email, int agentId) {
        this.id = id;
        this.firstName = new SimpleStringProperty(fname);
        this.lastName = new SimpleStringProperty(lname);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.province = new SimpleStringProperty(prov);
        this.postal = new SimpleStringProperty(postal);
        this.country = new SimpleStringProperty(country);
        this.homePhone = new SimpleStringProperty(hPhone);
        this.busPhone = new SimpleStringProperty(bPhone);
        this.agent = agentId;
    }

    //Get methods
    public int getId(){ return id; }
    public String getFirstName(){ return firstName.get(); }
    public String getLastName(){ return firstName.get(); }
    public String getAddress(){ return address.get(); }
    public String getCity(){ return city.get(); }
    public String getProv(){ return province.get(); }
    public String getPostal(){ return postal.get(); }
    public String getCountry(){ return country.get(); }
    public String getHomePhone(){ return homePhone.get(); }
    public String getBusPhone(){ return busPhone.get(); }
    public String getEmail(){ return email.get(); }
    public int getAgent(){ return agent; }


    //Set methods -- Customer Id is auto set by database
    public void setFirstName(String fname){ firstName.set(fname); }
    public void setLastName(String lname) { lastName.set(lname); }
    public void setAddress(String add) { address.set(add); }
    public void setCity(String cty) { city.set(cty); }
    public void setProv(String prov) { province.set(prov); }
    public void setPostal(String post) { postal.set(post); }
    public void setCountry(String cty) { country.set(cty); }
    public void setHomePhone(String phone) { homePhone.set(phone); }
    public void setBusPhone(String phone) { busPhone.set(phone); }
    public void setEmail(String eml) { email.set(eml); }
    public void setAgent(int id) { agent = id; }
}
