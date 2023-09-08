package DAO;

import models.Booking;
import models.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class BookingDAO {
    private final SessionFactory sessionFactory;

    public BookingDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room = booking.getRoom();
            if (!isRoomAvailable(room)) {
                throw new IllegalArgumentException("This room has been booked");
            }
            room.setStatus("booked");
            session.update(room);

            session.save(booking);
            transaction.commit();
        }
    }

    public Booking getBookingById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Booking.class, id);
        }
    }

    public List<Booking> getAllBookings() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Booking", Booking.class).list();
        }
    }

    public void updateBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(booking);
            transaction.commit();
        }
    }

    public void deleteBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(booking);
            transaction.commit();
        }
    }

    public void checkOutBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Room room = booking.getRoom();
            room.setStatus("available");
            session.update(room);
            transaction.commit();
        }
    }

    private boolean isRoomAvailable(Room room) {
        return "available".equals(room.getStatus());
    }

}

