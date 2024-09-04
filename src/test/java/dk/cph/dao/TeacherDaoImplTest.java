package dk.cph.dao;

import dk.cph.config.HibernateConfig;
import dk.cph.config.HibernateConfigState;
import dk.cph.model.Teacher;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeacherDaoImplTest {

    private TeacherDaoImpl teacherDao;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig(HibernateConfigState.TEST);
        teacherDao = TeacherDaoImpl.getInstance(emf);
    }


    @AfterAll
    public void tearDown() {
        // Clean up after each test
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testPersistEntity() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setName("John Doe");
        teacher.setEmail("john.doe@example.com");

        // Act
        teacherDao.persistEntity(teacher);
        Teacher foundTeacher = teacherDao.findEntity(teacher.getId());

        // Assert
        assertNotNull(foundTeacher);
        assertEquals("John Doe", foundTeacher.getName());
        assertEquals("john.doe@example.com", foundTeacher.getEmail());
    }

    @Test
    public void testFindAll() {
        // Arrange
        Teacher teacher1 = new Teacher();
        teacher1.setName("John Doe");
        teacher1.setEmail("john.doe@example.com");

        Teacher teacher2 = new Teacher();
        teacher2.setName("Jane Smith");
        teacher2.setEmail("jane.smith@example.com");

        teacherDao.persistEntity(teacher1);
        teacherDao.persistEntity(teacher2);

        // Act
        List<Teacher> teachers = (List<Teacher>) teacherDao.findAll();

        // Assert
        assertEquals(2, teachers.size());
    }

    @Test
    public void testRemoveEntity() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setName("John Doe");
        teacher.setEmail("john.doe@example.com");

        teacherDao.persistEntity(teacher);
        int id = teacher.getId();

        // Act
        teacherDao.removeEntity(Integer.valueOf(id));
        Teacher foundTeacher = teacherDao.findEntity(Integer.valueOf(id));

        // Assert
        assertNull(foundTeacher);
    }

    @Test
    public void testUpdateEntity() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setName("John Doe");
        teacher.setEmail("john.doe@example.com");

        teacherDao.persistEntity(teacher);
        int id = teacher.getId();

        // Update teacher
        teacher.setName("Johnathan Doe");
        teacher.setEmail("johnathan.doe@example.com");

        // Act
        teacherDao.updateEntity(teacher, Integer.valueOf(id));
        Teacher updatedTeacher = teacherDao.findEntity(Integer.valueOf(id));

        // Assert
        assertNotNull(updatedTeacher);
        assertEquals("Johnathan Doe", updatedTeacher.getName());
        assertEquals("johnathan.doe@example.com", updatedTeacher.getEmail());
    }
}
