package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveUniversityToJson(Model.University university, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(university, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Model.University loadUniversityFromJson(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Model.University>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testUniversityJsonSerialization() {
        Model model = new Model();

        Model.University oldUniversity = model.createTypicalUniversity();

        saveUniversityToJson(oldUniversity, "old_university.json");
        Model.University newUniversity = loadUniversityFromJson("old_university.json");

        assertTrue(oldUniversity.equals(newUniversity));
    }

    public static void main(String[] args) {
        Model model = new Model();

        Model.University university = model.createTypicalUniversity();

        // Збереження університету у форматі JSON
        saveUniversityToJson(university, "university.json");

        // Зчитування університету з файлу JSON
        Model.University loadedUniversity = loadUniversityFromJson("university.json");

        // Вивід зчитаного університету
        System.out.println("Loaded University Name: " + loadedUniversity.getName());
        for (Model.Faculty faculty : loadedUniversity.getFaculties()) {
            System.out.println("Faculty: " + faculty.getName());
            for (Model.Department department : faculty.getDepartments()) {
                System.out.println("\tDepartment: " + department.getName());
                for (Model.Group group : department.getGroups()) {
                    System.out.println("\t\tGroup: " + group.getName());
                    for (Model.Student student : group.getStudents()) {
                        System.out.println("\t\t\tStudent: " + student.getFirstName() + " " +
                                student.getLastName() + " (" + student.getSex() + ")");
                    }
                }
            }
        }
    }

    public static class Model {
        public static class University {
            private String name;
            private List<Faculty> faculties;

            public University() {
                faculties = new ArrayList<>();
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void addFaculty(Faculty faculty) {
                faculties.add(faculty);
            }

            public List<Faculty> getFaculties() {
                return faculties;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof University)) {
                    return false;
                }
                University other = (University) obj;
                return this.name.equals(other.getName());
            }
        }

        public class Faculty {
            private String name;
            private List<Department> departments;

            public Faculty() {
                departments = new ArrayList<>();
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void addDepartment(Department department) {
                departments.add(department);
            }

            public List<Department> getDepartments() {
                return departments;
            }
        }

        public class Department {
            private String name;
            private List<Group> groups;

            public Department() {
                groups = new ArrayList<>();
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void addGroup(Group group) {
                groups.add(group);
            }

            public List<Group> getGroups() {
                return groups;
            }
        }

        public class Group {
            private String name;
            private List<Student> students;

            public Group() {
                students = new ArrayList<>();
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void addStudent(Student student) {
                students.add(student);
            }

            public List<Student> getStudents() {
                return students;
            }
        }

        public class Student extends Human {
            private Group group;

            public void setGroup(Group group) {
                this.group = group;
            }

            public Group getGroup() {
                return group;
            }
        }

        public abstract class Human {
            private String firstName;
            private String lastName;
            private String patronymic;
            private Sex sex;

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setPatronymic(String patronymic) {
                this.patronymic = patronymic;
            }

            public String getPatronymic() {
                return patronymic;
            }

            public void setSex(Sex sex) {
                this.sex = sex;
            }

            public Sex getSex() {
                return sex;
            }
        }

        public University createTypicalUniversity() {
            University university = new University();
            university.setName("НТУ ДП");

            Faculty faculty1 = new Faculty();
            faculty1.setName("Факультет інформаційних технологій");

            Department department1 = new Department();
            department1.setName("Системний аналіз");

            Group group1 = new Group();
            group1.setName("124-20-1");

            Student student1 = new Student();
            student1.setFirstName("Георгій");
            student1.setLastName("Беліменко");
            student1.setPatronymic("Георгійович");
            student1.setSex(Sex.MALE);
            student1.setGroup(group1);

            Group group2 = new Group();
            group2.setName("124-20-2");

            Student student2 = new Student();
            student2.setFirstName("Ірина");
            student2.setLastName("Булава");
            student2.setPatronymic("Андріївна");
            student2.setSex(Sex.FEMALE);
            student2.setGroup(group2);

            department1.addGroup(group1);
            department1.addGroup(group2);
            faculty1.addDepartment(department1);
            university.addFaculty(faculty1);

            return university;
        }
    }

    public enum Sex {
        MALE,
        FEMALE
    }
}
