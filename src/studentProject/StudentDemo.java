package studentProject;

import studentProject.exception.UserNotFoundException;
import studentProject.model.Lesson;
import studentProject.model.Student;
import studentProject.model.User;
import studentProject.storage.LessonStorage;
import studentProject.storage.StudentStorage;
import studentProject.storage.UserStorage;

import java.util.Scanner;

public class StudentDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) {
        Lesson[] lessons = new Lesson[3];
        lessons[0] = new Lesson("Java", 5, "core", 50000);
        lessons[1] = new Lesson("C", 10, "core", 50000);
        lessons[2] = new Lesson("Python", 14, "core", 50000);
        lessonStorage.add(lessons[0]);
        lessonStorage.add(lessons[1]);
        lessonStorage.add(lessons[2]);

        userStorage.add(new User("martiros", "martirosyan", "mart@mail.ru", "user", "Admin"));

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
        if (userStorage.getUserByEmailAndPassword(email, password) == null) {
            System.out.println("Wrong email or password");
        } else {
            if (userStorage.getUserByEmailAndPassword(email, password).getType().equals("Admin")) {
                adminPage();
            } else {
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
        if (userStorage.getUserByEmail(userDates[2]) != null) {
            System.out.println("user with this email already exist");
        } else {
            if (userDates[2].equals("email") && userDates[3].equals("password")) {
                User admin = new User(userDates[0], userDates[1], userDates[2], userDates[3], "Admin");
                userStorage.add(admin);
                System.out.println("You are registered");
            } else {
                User user = new User(userDates[0], userDates[1], userDates[2], userDates[3], "User");
                userStorage.add(user);
                System.out.println("You are registered");
            }
        }

    }


    private static void deleteStudentByEmail() {
        System.out.println("Please input student's email");
        String email = scanner.nextLine();
        try {
            studentStorage.getByEmail(email);
        } catch (UserNotFoundException e) {
            System.out.println("Student not found");
        }
        studentStorage.deleteStudentByEmail(email);

        System.out.println("wrong email");
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
            studentStorage.printStudentByLesson(name);
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
        try {
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("please input student's email");
            String email = scanner.nextLine();
            System.out.println("please input student's phone");
            String phone = scanner.nextLine();
            System.out.println("please input student's lesson names");
            String nameOfLessons = scanner.nextLine();

            try {
                studentStorage.getByEmail(email);
                System.out.println("student with this email already exist");
            } catch (UserNotFoundException e) {
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
            }
        } catch (NumberFormatException e) {
            System.out.println("age must be a number");
        }
    }


    private static void addLesson() {
        try {
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
        } catch (Exception e) {
            System.out.println("duration and price should numbers");
        }

    }


}
