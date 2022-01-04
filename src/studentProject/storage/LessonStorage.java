package studentProject.storage;

import studentProject.model.Lesson;

import java.util.LinkedList;
import java.util.List;

public class LessonStorage {
    private List<Lesson> list = new LinkedList<>();


    public void add(Lesson lesson) {
        list.add(lesson);

    }


    public void printLessons() {
        for (Lesson lesson : list) {
            System.out.println(lesson);
            //System.out.println("The lesson has been deleted");
        }
    }

    public void deleteLessonByName(String name) {
        for (Lesson lesson : list) {
            if (lesson.getName().equals(name)) {
                list.remove(lesson);
                System.out.println("The lesson has been deleted");
            }
        }
    }

    public Lesson getLessonByName(String name) {
        for (Lesson lesson : list) {
            if (lesson.getName().equals(name)){
                return lesson;
            }
        }
        return null;
    }
}
