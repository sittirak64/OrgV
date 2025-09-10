package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shops")
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")   // map ให้ตรงกับ shop_id
    private Long id;

    @Column(name = "shop_ownerfname", nullable = false)
    private String ownerFname;

    @Column(name = "shop_ownerlname", nullable = false)
    private String ownerLname;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "shop_housenumber")
    private String houseNumber;

    @Column(name = "shop_moo")
    private String moo;

    @Column(name = "shop_street")
    private String street;

    @Column(name = "shop_tumbon")
    private String tumbon;

    @Column(name = "shop_amper")
    private String amper;

    @Column(name = "shop_province")
    private String province;

    @Column(name = "shop_phone")
    private String phone;

    @Column(name = "shop_username", unique = true, nullable = false)
    private String username;

    @Column(name = "shop_pass", nullable = false)
    private String password;

    // ---------------------------
    // Getter and Setter
    // ---------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerFname() { return ownerFname; }
    public void setOwnerFname(String ownerFname) { this.ownerFname = ownerFname; }

    public String getOwnerLname() { return ownerLname; }
    public void setOwnerLname(String ownerLname) { this.ownerLname = ownerLname; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getMoo() { return moo; }
    public void setMoo(String moo) { this.moo = moo; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getTumbon() { return tumbon; }
    public void setTumbon(String tumbon) { this.tumbon = tumbon; }

    public String getAmper() { return amper; }
    public void setAmper(String amper) { this.amper = amper; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
