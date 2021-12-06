import model.Lesson;
import model.Student;
import model.User;
import storage.LessonStorage;
import storage.StudentStorage;
import storage.UserStorage;

import java.util.Scanner;

public class StudentDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) {
        Lesson[] lessons = new Lesson[2];
        lessons[0] = new Lesson("Java", 5, "core", 50000);
        lessonStorage.add(lessons[0]);
        lessons[1] = new Lesson("C", 10, "core", 50000);
        lessonStorage.add(lessons[1]);
        lessonStorage.add(new Lesson("Python", 14, "core", 50000));
        studentStorage.add(new Student("poxos", "poxosyan", 55, "poxos@mail.ru", "093333333", lessons));
        studentStorage.add(new Student("petros", "petrosyan", 15, "petros@mai.ru", "04444444", lessons));


        boolean isRUn = true;
        while (isRUn) {
            Commands.printNewCommands();
            String newCommand = scanner.nextLine();
            switch (newCommand) {
                case EXIT:
                    isRUn = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTRATION:
                    registration();
                    break;
                default:
                    System.out.println("invalid command");
            }
        }





    }

    private static void login() {
        System.out.println("please input student's email");
        String email = scanner.nextLine();
        System.out.println("please input student's password");
        String password = scanner.nextLine();
        if (userStorage.getUserByEmailAndpassword(email, password)==null){
            System.out.println("Wrong email or password");
        }else {
            if (userStorage.getUserByEmailAndpassword(email, password).getType().equals("Admin")){
                adminPage();
            }else {
                userPage();
            }
        }
    }

    private static void userPage() {

        boolean isRun = true;
        while (isRun) {
            Commands.printCommandsForUser();
            String commands = scanner.nextLine();
            switch (commands) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.printStudent();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentByLesson();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.printLessons();
                    break;
                default:
                    System.out.println("invalid command!");
            }
        }
    }

    private static void adminPage() {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String commands = scanner.nextLine();
            switch (commands) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.printStudent();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentByLesson();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.printLessons();
                    break;
                case DELETE_LESSON_BY_NAME:
                    deleteLessonByName();
                    break;
                case DELETE_STUDENT_BY_EMAIL:
                    deleteStudentByEmail();
                    break;
                default:
                    System.out.println("invalid command!");
            }
        }
    }

    private static void registration() {
        System.out.println("Please input name, surname, email, password");
        String userDate = scanner.nextLine();
        String[] userDates = userDate.split(",");
        if (userStorage.getUserByEmail(userDates[2]) == null) {
            if (userDates[2].equals("email") && userDates[3].equals("password")) {
                User admin = new User(userDates[0], userDates[1], userDates[2], userDates[3], "Admin");
                userStorage.add(admin);
                System.out.println("You are registered");
            } else {
                User user = new User(userDates[0], userDates[1], userDates[2], userDates[3], "User");
                userStorage.add(user);
                System.out.println("You are registered");
            }

        } else {
            System.out.println("User with this email has already exist ");
        }
    }

    private static void deleteStudentByEmail() {
        System.out.println("Please input student's email");
        String email = scanner.nextLine();
        if (studentStorage.getByEmail(email) != null) {
            studentStorage.deleteStudentByEmail(email);
        } else {
            System.out.println("wrong email");
        }


    }

    private static void deleteLessonByName() {
        System.out.println("Please input lesson's name");
        String name = scanner.nextLine();
        if (lessonStorage.getLessonByName(name) != null) {
            lessonStorage.deleteLessonByName(name);
        } else {
            System.err.println("wrong lesson name");
        }

    }


    private static void printStudentByLesson() {
        System.out.println("Please input lesson's name");
        String name = scanner.nextLine();
        if (lessonStorage.getLessonByName(name) != null) {
            // studentStorage.printStudentByLesson(name);
        } else {
            System.err.println("wrong lesson name");
        }

    }


    private static void addStudent() {

        System.out.println("please input student's name");
        String name = scanner.nextLine();
        System.out.println("please input student's surname");
        String surname = scanner.nextLine();
        System.out.println("please input student's age");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("please input student's email");
        String email = scanner.nextLine();
        System.out.println("please input student's phone");
        String phone = scanner.nextLine();
        System.out.println("please input student's lesson names");
        String nameOfLessons = scanner.nextLine();

        if (studentStorage.getByEmail(email) == null) {
            String[] names = nameOfLessons.split(",");
            Lesson[] lessons = new Lesson[names.length];
            for (int i = 0; i < lessons.length; i++) {
                if (lessonStorage.getLessonByName(names[i]) == null) {
                    System.out.println("Lesson with that name is not exist");
                } else {
                    lessons[i] = lessonStorage.getLessonByName(names[i]);
                }
            }
            Student student = new Student(name, surname, age, email, phone, lessons);
            studentStorage.add(student);
            System.out.println("student added");
        } else {
            System.out.println("student with this email already exist");
        }
    }

    private static void addLesson() {
        System.out.println("please input lesson's name");
        String name = scanner.nextLine();
        System.out.println("please input lesson's duration");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.println("please input lesson's lecturerName");
        String lecturerName = scanner.nextLine();
        System.out.println("please input lesson's price");
        double price = Double.parseDouble(scanner.nextLine());
        if (lessonStorage.getLessonByName(name) == null) {
            Lesson lesson = new Lesson(name, duration, lecturerName, price);
            lessonStorage.add(lesson);
            System.out.println("lesson added");
        } else {
            System.out.println("lesson with this name already exist");
        }


    }

    private static void printCommands() {
        System.out.println("Please imput " + EXIT + " for EXIT");
        System.out.println("Please imput " + ADD_LESSON + " for ADD_LESSON");
        System.out.println("Please imput " + ADD_STUDENT + " for ADD_STUDENT");
        System.out.println("Please imput " + PRINT_STUDENTS + " for PRINT_STUDENTS");
        System.out.println("Please imput " + PRINT_STUDENTS_BY_LESSON + " for PRINT_STUDENTS_BY_LESSON");
        System.out.println("Please imput " + PRINT_LESSONS + " for PRINT_LESSONS");
        System.out.println("Please imput " + DELETE_LESSON_BY_NAME + " for DELETE_LESSON_BY_NAME");
        System.out.println("Please imput " + DELETE_STUDENT_BY_EMAIL + " for DELETE_STUDENT_BY_EMAIL");
    }
}
