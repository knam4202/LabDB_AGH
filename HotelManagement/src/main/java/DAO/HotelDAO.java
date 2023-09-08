package DAO;

import models.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HotelDAO {
    private final SessionFactory sessionFactory;

    public HotelDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addHotel(Hotel hotel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(hotel);
            transaction.commit();
        }
    }

    public Hotel getHotelById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Hotel.class, id);
        }
    }

    public List<Hotel> getAllHotels() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hotel", Hotel.class).list();
        }
    }

    public void updateHotel(Hotel hotel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(hotel);
            transaction.commit();
        }
    }

    public void deleteHotel(Hotel hotel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(hotel);
            transaction.commit();
        }
    }
}
