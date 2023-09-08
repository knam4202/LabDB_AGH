package DAO;

import models.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAO {
    private final SessionFactory sessionFactory;

    public RoomDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addRoom(Room room) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (!isValidRoomType(room.getType())) {
                throw new IllegalArgumentException("Invalid room type");
            }
            session.save(room);
            transaction.commit();
        }
    }

    public Room getRoomById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Room.class, id);
        }
    }

    public List<Room> getAllRooms() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Room", Room.class).list();
        }
    }

    public void updateRoom(Room room) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
        }
    }

    public void deleteRoom(Room room) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(room);
            transaction.commit();
        }
    }

    private boolean isValidRoomType(String roomType) {
        return roomType != null && (roomType.equals("single") || roomType.equals("double") || roomType.equals("triple") || roomType.equals("quad"));
    }

    public List<Room> getAvailableRooms() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Room R WHERE R.status = :status";
            Query query = session.createQuery(hql);
            query.setParameter("status", "available");
            return (List<Room>) query.list();
        }
    }

}

