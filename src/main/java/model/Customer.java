package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Email(message = "NO NO NOOOOOOO:!!!")
    @Size(min = 5, max = 30)
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name="province_id")
    private Province province;

    public Customer(String name, String email, String address, Province province) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.province = province;
    }

    public Customer() {
    }

    public Customer(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
