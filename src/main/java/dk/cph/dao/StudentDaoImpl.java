package dk.cph.dao;

import dk.cph.model.Course;
import dk.cph.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentDaoImpl implements GenericDAO<Student, Integer> {

    private static StudentDaoImpl instance;
    private static EntityManagerFactory emf;

    public static StudentDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new StudentDaoImpl();
        }
        return instance;
    }

    @Override
    public Collection<Student> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        }
    }

    @Override
    public void persistEntity(Student student) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            em.remove(student);
            em.getTransaction().commit();
        }
    }

    @Override
    public Student findEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Student.class, id);
        }
    }

    @Override
    public void updateEntity(Student student, Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        }
    }
}
