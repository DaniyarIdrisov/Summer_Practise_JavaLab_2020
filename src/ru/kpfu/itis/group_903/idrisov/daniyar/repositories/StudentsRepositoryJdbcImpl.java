package ru.kpfu.itis.group_903.idrisov.daniyar.repositories;

import ru.kpfu.itis.group_903.idrisov.daniyar.models.MentorModel;
import ru.kpfu.itis.group_903.idrisov.daniyar.models.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepositoryJdbcImpl implements StudentsRepository {

    private static final String SQL_SELECT_STUDENT_BY_ID = "select * from student where id = ";
    private static final String SQL_SELECT_STUDENTS_BY_AGE = "select * from student where age = ";
    private static final String SQL_SELECT_MENTORS_BY_STUDENT_ID = "select * from mentor where mentor.student_id = ";
    private static final String SQL_SELECT_STUDENTS = "select * from student";
    private static final String SQL_INSERT_STUDENT = "insert into student " + "(first_name, last_name, age, group_number) values ('%s','%s',%d,%d)";
    private static final String SQL_UPDATE_STUDENT = "update student set " + "first_name = '%s', last_name = '%s', age = %d, group_number = %d where id = %d";


    private Connection connection;

    public StudentsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<StudentModel> findAllByAge(int age) {

        Statement statement1 = null;
        ResultSet result1 = null;
        Statement statement2;
        ResultSet result2;

        List<StudentModel> studentModels = new ArrayList<>();

        try {

            statement1 = connection.createStatement();
            result1 = statement1.executeQuery(SQL_SELECT_STUDENTS_BY_AGE + age);

            while (result1.next()) {
                StudentModel studentModel = new StudentModel(
                        result1.getLong("id"),
                        result1.getString("first_name"),
                        result1.getString("last_name"),
                        result1.getInt("age"),
                        result1.getInt("group_number")
                );

                statement2 = connection.createStatement();
                result2 = statement2.executeQuery(SQL_SELECT_MENTORS_BY_STUDENT_ID + studentModel.getId());

                List<MentorModel> mentorModels = new ArrayList<>();
                while (result2.next()) {
                    MentorModel mentorModel = new MentorModel(
                            result2.getLong("id"),
                            result2.getString("first_name"),
                            result2.getString("last_name"),
                            studentModel
                    );
                    mentorModels.add(mentorModel);
                }
                studentModel.setMentors(mentorModels);
                studentModels.add(studentModel);
            }
            return studentModels;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (result1 != null) {
                try {
                    result1.close();
                } catch (SQLException e) {
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    @Override
    public List<StudentModel> findAll() {

        Statement statement1 = null;
        ResultSet result1 = null;
        Statement statement2;
        ResultSet result2;

        List<StudentModel> studentModels = new ArrayList<>();

        try {

            statement1 = connection.createStatement();
            result1 = statement1.executeQuery(SQL_SELECT_STUDENTS);

            while (result1.next()) {
                StudentModel studentModel = new StudentModel(
                        result1.getLong("id"),
                        result1.getString("first_name"),
                        result1.getString("last_name"),
                        result1.getInt("age"),
                        result1.getInt("group_number")
                );

                statement2 = connection.createStatement();
                result2 = statement2.executeQuery(SQL_SELECT_MENTORS_BY_STUDENT_ID + studentModel.getId());

                List<MentorModel> mentorModels = new ArrayList<>();
                while (result2.next()) {
                    MentorModel mentorModel = new MentorModel(
                            result2.getLong("id"),
                            result2.getString("first_name"),
                            result2.getString("last_name"),
                            studentModel
                    );
                    mentorModels.add(mentorModel);
                }
                studentModel.setMentors(mentorModels);
                studentModels.add(studentModel);
            }
            return studentModels;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (result1 != null) {
                try {
                    result1.close();
                } catch (SQLException e) {
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    @Override
    public StudentModel findById(Long id) {

        Statement statement = null;
        ResultSet result = null;

        try {

            statement = connection.createStatement();
            result = statement.executeQuery(SQL_SELECT_STUDENT_BY_ID + id);
            StudentModel studentModel;

            if (result.next()) {
                studentModel = new StudentModel(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("age"),
                        result.getInt("group_number")
                );
            } else return null;
            result = statement.executeQuery(SQL_SELECT_MENTORS_BY_STUDENT_ID + id);
            List<MentorModel> mentorModels = new ArrayList<>();
            while (result.next()) {
                MentorModel mentorModel = new MentorModel(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        studentModel
                );
                mentorModels.add(mentorModel);
            }
            studentModel.setMentors(mentorModels);
            return studentModel;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public void save(StudentModel entity) {

        Statement statement = null;
        ResultSet result = null;

        try {

            statement = connection.createStatement();

            String insert = String.format(SQL_INSERT_STUDENT,
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAge(),
                    entity.getGroup()
            );
            statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
            result = statement.getGeneratedKeys();
            result.next();
            long id = result.getInt(1);
            entity.setId(id);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    @Override
    public void update(StudentModel entity) {

        Statement statement = null;

        try {

            statement = connection.createStatement();

            String update = String.format(SQL_UPDATE_STUDENT,
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAge(),
                    entity.getGroup(),
                    entity.getId()
            );
            statement.executeUpdate(update);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

}
