package DAO;

import models.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class StaffDAO {
    private final SessionFactory sessionFactory;

    public StaffDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addStaff(Staff staff) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(staff);
            transaction.commit();
        }
    }

    public Staff getStaffById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Staff.class, id);
        }
    }

    public List<Staff> getAllStaff() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Staff", Staff.class).list();
        }
    }

    public void updateStaff(Staff staff) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(staff);
            transaction.commit();
        }
    }

    public void deleteStaff(Staff staff) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(staff);
            transaction.commit();
        }
    }
}

