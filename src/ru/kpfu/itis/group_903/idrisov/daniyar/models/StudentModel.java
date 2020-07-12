package ru.kpfu.itis.group_903.idrisov.daniyar.models;

import java.util.List;
import java.util.Objects;

public class StudentModel {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int group;
    private List<MentorModel> mentorModels;

    public StudentModel(Long id, String firstName, String lastName, int age, int group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.group = group;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public List<MentorModel> getMentors() {
        return mentorModels;
    }

    public void setMentors(List<MentorModel> mentors) {
        this.mentorModels = mentors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentModel that = (StudentModel) o;
        return id == that.id &&
                age == that.age &&
                group == that.group &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(mentorModels, that.mentorModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, group, mentorModels);
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", group=" + group +
                ", mentorModels=" + mentorModels +
                '}';
    }

}
