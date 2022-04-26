package ru.gb.peterson.homework5;

public class Main {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee(
                "Ivan",
                "Ivanov",
                "Engineer",
                "ivan.ivanov@gmail.com",
                "+50055521054",
                1500.0,
                35);
        employees[1] = new Employee(
                "Sergei",
                "Sergejev",
                "Director",
                "serg.serg@gmail.com",
                "+1231235",
                1900.0,
                45);
        employees[2] = new Employee(
                "Semjon",
                "Semjonov",
                "Constructor",
                "semsema@gmail.com",
                "+3211564545",
                1350.0,
                29);
        employees[3] = new Employee(
                "Stepan",
                "Stepanov",
                "Engineer",
                "stepstepa@gmail.com",
                "+8000165574",
                1000.0,
                53);
        employees[4] = new Employee(
                "Svetlana",
                "Svetova",
                "Secretary",
                "svet.sveta@gmail.com",
                "+8004568534",
                900.0,
                25);

        for (Employee e : employees) {
            if (e.getAge() > 40) e.print();
        }
    }
}
