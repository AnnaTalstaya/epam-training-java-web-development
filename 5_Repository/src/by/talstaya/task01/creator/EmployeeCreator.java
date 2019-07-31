package by.talstaya.task01.creator;

import by.talstaya.task01.entity.Employee;
import by.talstaya.task01.exception.CustomException;

import java.util.List;

public interface EmployeeCreator {
    Employee factoryMethod(List<String> stringList) throws CustomException;
}
