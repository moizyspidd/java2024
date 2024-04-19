package org.example;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();

        Model.University university = model.new University();
        university.setName("НТУ ДП");

        Model.Faculty faculty = model.new Faculty();
        faculty.setName("Факультет інформаційних технологій");

        Model.Department department = model.new Department();
        department.setName("Системний аналіз");

        Model.Group group = model.new Group();
        group.setName("124-20-1");

        Model.Student student = model.new Student();
        student.setFirstName("Георгій");
        student.setLastName("Беліменко");
        student.setPatronymic("Георгійович");
        student.setSex(Sex.MALE);
        student.setGroup(group);

        System.out.println("Університет: " + university.getName());
        System.out.println("Факультет: " + faculty.getName());
        System.out.println("Спеціальність: " + department.getName());
        System.out.println("Группа: " + group.getName());
        System.out.println("Студент: " + student.getFirstName() + " " + student.getLastName());
    }

    public static class Model {
        public class University {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        public class Faculty {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        public class Department {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        public class Group {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
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
    }

    public enum Sex {
        MALE,
        FEMALE
    }
}
