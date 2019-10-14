package ru.job4j.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * SchoolTest
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class SchoolTest {

    @Test
    public void classA() {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            students.add(new Student(i * 10));
        }
        School school = new School();
        List<Student> classA = school.collect(students, predicate -> predicate.getScore() >= 70 && predicate.getScore() <= 100);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(70));
        expected.add(new Student(80));
        expected.add(new Student(90));
        expected.add(new Student(100));
        assertThat(classA, is(expected));
    }

    @Test
    public void classsB() {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            students.add(new Student(i * 10));
        }
        School school = new School();
        List<Student> classB = school.collect(students, predicate -> predicate.getScore() >= 50 && predicate.getScore() < 70);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(50));
        expected.add(new Student(60));
        assertThat(classB, is(expected));
    }

    @Test
    public void classsC() {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            students.add(new Student(i * 10));
        }
        School school = new School();
        List<Student> classC = school.collect(students, predicate -> predicate.getScore() >= 0 && predicate.getScore() < 50);
        List<Student> expected = new ArrayList<>();
        expected.add(new Student(10));
        expected.add(new Student(20));
        expected.add(new Student(30));
        expected.add(new Student(40));
        assertThat(classC, is(expected));
    }

    @Test
    public void convertListToMap() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(10, "Vlad"));
        students.add(new Student(100, "Bob"));
        students.add(new Student(10, "Vlad"));
        students.add(new Student(1, "Mike"));
        School school = new School();
        Map<String, Student> result = school.convertListToMap(students);
        Map<String, Student> expected = new HashMap<String, Student>();
        expected.put("Vlad", new Student(10, "Vlad"));
        expected.put("Bob", new Student(100, "Bob"));
        expected.put("Mike", new Student(1, "Mike"));
        assertThat(result, is(expected));
    }

    @Test
    public void levelOf() {
        List<Student> students = List.of(
                new Student(10, "Vlad"),
                new Student(100, "Bob"),
                new Student(10, "Vlad"),
                new Student(1, "Mike")
        );
        School school = new School();
        List<Student> expected = List.of(
                new Student(100, "Bob"),
                new Student(10, "Vlad")
        );
        List<Student> result = school.levelOf(students, 10);
        assertThat(result, is(expected));
    }
}