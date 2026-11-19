package ru.rsatu.service;

import ru.rsatu.entity.Address;
import ru.rsatu.entity.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StudentService {

    @PersistenceContext(unitName = "my-pu")
    private EntityManager em;

    // Получить всех студентов с адресами
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    // Получить одного студента по ID
    public Student getStudent(Long id) {
        return em.find(Student.class, id);
    }

    // Добавить нового студента
    public void addStudent(Student student) {
        em.persist(student);
    }

    // Удалить студента
    public void deleteStudent(Long id) {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }

    // Добавить адрес студенту
    public void addAddressToStudent(Long studentId, Address address) {
        Student student = em.find(Student.class, studentId);
        if (student != null) {
            student.addAddress(address);
            em.persist(address); // Сохраняем новый адрес
        }
    }
}