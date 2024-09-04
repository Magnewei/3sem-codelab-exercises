package dk.cph.dao;

import dk.cph.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherDaoImpl implements GenericDAO<Teacher, Integer> {

    private static TeacherDaoImpl instance;
    private static EntityManagerFactory emf;

    public static TeacherDaoImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TeacherDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Teacher> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
            return query.getResultList();
        }
    }

    @Override
    public void persistEntity(Teacher teacher) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, id);
            em.remove(teacher);
            em.getTransaction().commit();
        }
    }

    @Override
    public Teacher findEntity(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Teacher.class, id);
        }
    }

    @Override
    public void updateEntity(Teacher teacher, Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(teacher);
            em.getTransaction().commit();
        }
    }
}
