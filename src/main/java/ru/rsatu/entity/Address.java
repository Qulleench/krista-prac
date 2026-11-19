package ru.rsatu.entity;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @Column(name = "house", length = 20)
    private String house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Address() {}

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouse() { return house; }
    public void setHouse(String house) { this.house = house; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}