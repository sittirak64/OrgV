package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resu_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shops shop;

    @Column(name = "resu_shopname", nullable = false)
    private String shopName;

    @Column(name = "resu_housenumber")
    private String houseNumber;

    @Column(name = "resu_moo")
    private String moo;

    @Column(name = "resu_street")
    private String street;

    @Column(name = "resu_tumbon")
    private String tumbon;

    @Column(name = "resu_amper")
    private String amper;

    @Column(name = "resu_province")
    private String province;

    @Column(name = "resu_phone")
    private String phone;

    @Column(name = "resu_vegename", nullable = false)
    private String vegeName;

    @Column(name = "resu_result")
    private String result;

    @Lob
    @Column(name = "resu_certificate")
    private byte[] certificate;

    @Column(name = "resu_location")
    private String location;

    // ---------------------------
    // Getter & Setter
    // ---------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shops getShop() { return shop; }
    public void setShop(Shops shop) { this.shop = shop; }

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

    public String getVegeName() { return vegeName; }
    public void setVegeName(String vegeName) { this.vegeName = vegeName; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public byte[] getCertificate() { return certificate; }
    public void setCertificate(byte[] certificate) { this.certificate = certificate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
