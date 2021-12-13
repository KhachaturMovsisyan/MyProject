package studentProject.storage;

import studentProject.exception.UserNotFoundException;
import studentProject.model.Student;

public class StudentStorage {
    private Student[] students = new Student[10];
    private int size = 0;


    public void add(Student student) {

        if (size == students.length) {
            extend();
        }
        students[size++] = student;
    }

    private void extend() {
        Student[] newArray = new Student[students.length + 10];
        System.arraycopy(students, 0, newArray, 0, students.length);
        students = newArray;
    }

    public Student getByIndex(int index) {
        if (index < students.length) {

            return students[index];
        } else {
            System.out.println("Student not found");
            return null;
        }
    }

    public void printStudent() {
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

//    public void printStudentByLesson(String name) {
//        for (int i = 0; i < size; i++) {
//        //    if (students[i].getLesson().getName().contains(name)){
//                System.out.println(students[i]);
//            }
//        }
//    }

    public void deleteStudentByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (students[i].getEmail().equals(email)){
                deleteStudent();
            }
        }
    }

    private void deleteStudent() {
        for (int i = 0; i < size; i++) {
            students[i]=students[i+1];
            size--;
        }
    }

    public Student getByEmail(String email) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (students[i].getEmail().equals(email)){
                return students[i];
            }
        }
        throw new UserNotFoundException();
    }

    public void printStudentByLesson(String name) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < students[i].getLessons().length; j++) {
                if (students[i].getLessons()[j].getName().equals(name)){
                    System.out.println(students[i]);
                }
            }
        }
    }
}
