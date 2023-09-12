package models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private LocalDate date;
    private double discount;
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    public Payment() {
    }

    public Payment(LocalDate date, double discount, double totalPrice, Booking booking) {
        this.date = date;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.booking = booking;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount <= 0 || discount >= 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1");
        }
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
