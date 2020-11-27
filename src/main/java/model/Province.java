package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    public Province() {
    }

    public Province(String name) {
        this.name = name;
    }

    public Province(Long id, String name, List<Customer> customers) {
        this.id = id;
        this.name = name;
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

}
