package ru.kpfu.itis.group_903.idrisov.daniyar.repositories;

import ru.kpfu.itis.group_903.idrisov.daniyar.models.StudentModel;

import java.util.List;

public interface StudentsRepository extends CrudRepository<StudentModel> {

    List<StudentModel> findAllByAge(int age);

}
