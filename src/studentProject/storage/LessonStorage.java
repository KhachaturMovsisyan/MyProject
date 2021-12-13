package studentProject.storage;

import studentProject.model.Lesson;

public class LessonStorage {
    private Lesson[] lessons = new Lesson[10];
    private int size = 0;

    public void add(Lesson lesson) {

        if (size == lessons.length) {
            extend();
        }
        lessons[size++] = lesson;
    }

    private void extend() {
        Lesson[] newLesson = new Lesson[lessons.length + 10];
        System.arraycopy(lessons, 0, newLesson, 0, lessons.length);
        lessons = newLesson;
    }

    public Lesson getByIndex(int index) {
        if (index < lessons.length) {

            return lessons[index];
        } else {
            System.err.println("Lesson not found");
            return null;
        }
    }

    public void printLessons() {
        for (int i = 0; i < size; i++) {
            System.out.println(lessons[i]);
        }
    }

    public void deleteLessonByName(String name) {
        for (int i = 0; i < size; i++) {
            if (lessons[i].getName().equals(name)) {
                deleteLesson();
                System.out.println("The lesson has been deleted");
            }
        }
    }

    private void deleteLesson() {
        for (int i = 0; i < size; i++) {
            lessons[i] = lessons[i + 1];
            size--;
        }
    }

    public Lesson getLessonByName(String name) {
        for (int i = 0; i < size; i++) {
            if (lessons[i].getName().equals(name)) {
                return lessons[i];
            }
        }
        return null;
    }
}
