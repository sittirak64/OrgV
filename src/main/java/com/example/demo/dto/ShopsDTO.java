package com.example.demo.dto;

public class ShopsDTO {
    private Long id;
    private String shopName;
    private String ownerFname;
    private String ownerLname;
    private String houseNumber;
    private String moo;
    private String street;
    private String tumbon;
    private String amper;
    private String province;
    private String phone;

    // ✅ Constructor
    public ShopsDTO(Long id, String shopName, String ownerFname, String ownerLname,
                    String houseNumber, String moo, String street,
                    String tumbon, String amper, String province, String phone) {
        this.id = id;
        this.shopName = shopName;
        this.ownerFname = ownerFname;
        this.ownerLname = ownerLname;
        this.houseNumber = houseNumber;
        this.moo = moo;
        this.street = street;
        this.tumbon = tumbon;
        this.amper = amper;
        this.province = province;
        this.phone = phone;
    }

    // ✅ Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getOwnerFname() { return ownerFname; }
    public void setOwnerFname(String ownerFname) { this.ownerFname = ownerFname; }

    public String getOwnerLname() { return ownerLname; }
    public void setOwnerLname(String ownerLname) { this.ownerLname = ownerLname; }

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
}
