package model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proID;
    private String proName;
    private double proPrice;
    private int proQuantity;
    private String img;

    @Transient
    private MultipartFile avatar;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    public Product() {
    }

    public Product(String proName, double proPrice, int proQuantity, String img, Category category) {
        this.proName = proName;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.img = img;
        this.category = category;
    }

    public Product(String proName, double proPrice, int proQuantity) {
        this.proName = proName;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
    }

    public Product(Long proID, String proName, double proPrice, int proQuantity, String img, MultipartFile avatar) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.img = img;
        this.avatar = avatar;
    }

    public Product(String proName, double proPrice, int proQuantity, String img) {
        this.proName = proName;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.img = img;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getProID() {
        return proID;
    }

    public void setProID(Long proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
