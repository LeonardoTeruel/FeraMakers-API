package com.teruel.feramakers.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @NotBlank(message = "Product Description cannot be null or empty")
    private String productDescription;

    @NotBlank(message = "User cannot be null or empty")
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> userList;

    private Instant createdTime;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productDescription='" + productDescription + '\'' +
                ", userList=" + userList +
                ", createdTime=" + createdTime +
                '}';
    }
}
