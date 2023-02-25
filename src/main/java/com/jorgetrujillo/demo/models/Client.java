package com.jorgetrujillo.demo.models;

import com.jorgetrujillo.demo.dtos.ClientDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 16, name = "id_type")
    private String idType;
    @Column(nullable = false,unique = true, name = "id_number")
    private  String idNumber;
    @Column(nullable = false)
    private String names;
    @Column(nullable = false)
    private  String email;
    @Column(nullable = false, name = "mobile_number")
    private String mobileNumber;
    @Column
    private  boolean deleted = false;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Client(){}

    public Client(String names, String idType, String idNumber, String email, String mobileNumber) {
        this.idType = idType;
        this.idNumber = idNumber;
        this.names = names;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public ClientDto toClientDto(){
        return new ClientDto(
                this.getId(),
                this.getNames(),
                this.getIdType(),
                this.getIdNumber(),
                this.getMobileNumber(),
                this.getEmail(),
                this.getMainAddress().getProvince(),
                this.getMainAddress().getCity(),
                this.getMainAddress().getAddress()
        );
    }

    private Address getMainAddress(){
        if(this.getAddresses().size()>0){
            return this.getAddresses().stream().filter(
                    Address::isMain
            ).toList().get(0);
        } else {
            return new Address();
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setClient(this);
    }

    public void removeStudent(Address address) {
        addresses.remove(address);
        address.setClient(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
