package models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double price;
    @ManyToOne
    @JoinColumn(name = "roomID")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    public Booking() {
    }

    public Booking(LocalDate checkIn, LocalDate checkOut, double price, Room room, Customer customer) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.room = room;
        this.customer = customer;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
