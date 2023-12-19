
import java.net.IDN;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author waniya_mustafa
 */
public class customer {
    private int ID;
    private String name;
    private String phoneNumber;
    private String Address;
    private String Password;
    private String gender;
    private ArrayList<order> orders;

    public customer(int ID, String name, String phoneNumber, String Address, String Password, String gender) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.Address = Address;
        this.Password = Password;
        this.gender = gender;
        orders=new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<order> orders) {
        this.orders = orders;
    }
    
    
}
