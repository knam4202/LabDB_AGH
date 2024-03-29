package models;


import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String number;
    private String type;
    private double pricePerNight;
    private String status;
    private String description;
    @ManyToOne
    @JoinColumn(name = "hotelID")
    private Hotel hotel;

    public Room() {
    }

    public Room(String number, String type, double pricePerNight, String status, String description, Hotel hotel) {
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.description = description;
        this.hotel = hotel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number != null && number.isEmpty()) {
            throw new IllegalArgumentException("Number cannot be empty");
        }
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight < 0) {
            throw new IllegalArgumentException("Price per night must be non-negative");
        }
        this.pricePerNight = pricePerNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!isValidRoomStatus(status)) {
            throw new IllegalArgumentException("Invalid room status");
        }
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 200) {
            throw new IllegalArgumentException("Description cannot exceed 200 characters");
        }
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    private boolean isValidRoomStatus(String status) {
        return status != null && (status.equals("available") || status.equals("booked"));
    }
}
