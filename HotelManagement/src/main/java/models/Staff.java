package models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    @ManyToOne
    @JoinColumn(name = "hotelID")
    private Hotel hotel;

    public Staff() {
    }

    public Staff(String firstName, String lastName, LocalDate birthday, String address, Hotel hotel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.hotel = hotel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName.length() > 20) {
            throw new IllegalArgumentException("First name cannot exceed 20 characters");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.length() > 20) {
            throw new IllegalArgumentException("Last name cannot exceed 20 characters");
        }
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.length() > 100) {
            throw new IllegalArgumentException("Address cannot exceed 100 characters");
        }
        this.address = address;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
