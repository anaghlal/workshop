package com.aws.anaghlal.ec2.intro.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Employee {
    private int id;
    private String name;

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    private int age;

    public static Optional<Employee> from(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            double salary = rs.getDouble("salary");
            return  Optional.of(new Employee(id, name, age, salary));
        }catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    private double salary;
}
