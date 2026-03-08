package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @Column
    private int id;

    @Column
    private String customerName;

    @Column
    private String email;

    @Column
    private String gender;

    @Column
    private long phone;

    @Column
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer(){

    }

    public Customer(int id, String customerName, String email, String gender, long phone, LocalDate registrationDate) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.registrationDate=registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
