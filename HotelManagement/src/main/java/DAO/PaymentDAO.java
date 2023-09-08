package DAO;

import models.Booking;
import models.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class PaymentDAO {
    private final SessionFactory sessionFactory;

    public PaymentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addPayment(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (!paidBeforeCheckin(payment)) {
                throw new IllegalArgumentException("Illegal payment date");
            }
            session.save(payment);
            transaction.commit();
        }
    }

    public Payment getPaymentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Payment.class, id);
        }
    }

    public List<Payment> getAllPayments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Payment", Payment.class).list();
        }
    }

    public void updatePayment(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(payment);
            transaction.commit();
        }
    }

    public void deletePayment(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(payment);
            transaction.commit();
        }
    }

    private boolean paidBeforeCheckin(Payment payment) {
        Booking booking = payment.getBooking();
        LocalDate paymentDate = payment.getDate();
        LocalDate checkinDate = booking.getCheckIn();

        return paymentDate.isEqual(checkinDate) || paymentDate.isBefore(checkinDate);
    }
}
