package ru.kpfu.itis.group_903.idrisov.daniyar.models;

import java.util.Objects;

public class MentorModel {
    private long id;
    private String firstName;
    private String lastName;
    private StudentModel student;

    public MentorModel(Long id, String firstName, String lastName, StudentModel student) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MentorModel that = (MentorModel) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, student);
    }

    @Override
    public String toString() {
        return "MentorModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
