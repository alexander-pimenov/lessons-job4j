package ru.job4j.stream;

import java.util.Comparator;
import java.util.Objects;

/**
 * Student
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Student implements Comparator<Student> {
   private int score;
   private String surname;

    public Student(int score) {
        this.score = score;
    }

    public Student(int score, String surname) {
        this.score = score;
        this.surname = surname;
    }

    public int getScore() {
        return score;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Student{"
                + "score=" + score + '}';
    }

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getScore() - o2.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
