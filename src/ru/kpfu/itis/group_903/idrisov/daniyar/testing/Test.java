package ru.kpfu.itis.group_903.idrisov.daniyar.testing;

import ru.kpfu.itis.group_903.idrisov.daniyar.models.StudentModel;
import ru.kpfu.itis.group_903.idrisov.daniyar.repositories.StudentsRepository;
import ru.kpfu.itis.group_903.idrisov.daniyar.repositories.StudentsRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

    private static final String URL = "jdbc:postgresql://localhost:5432/my_first_database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "...";


    public static void main(String[] args) throws SQLException {
        StudentModel studentModel = new StudentModel(null, "Tupac", "Shakur", 25, 996);
        StudentModel studentModel1 = new StudentModel((long) 4, "Notorious", "B.I.G", 24, 997);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(connection);
        System.out.println(studentsRepository.findById(1L));
        System.out.println(studentsRepository.findById(3L));
        System.out.println(studentsRepository.findById(4L));
        System.out.println(studentsRepository.findAllByAge(19));
        System.out.println(studentsRepository.findAllByAge(18));
        System.out.println(studentsRepository.findAll());
        studentsRepository.save(studentModel);
        System.out.println(studentsRepository.findById(4L));
        studentsRepository.update(studentModel1);
        System.out.println(studentsRepository.findById(4L));
        connection.close();
    }

}
