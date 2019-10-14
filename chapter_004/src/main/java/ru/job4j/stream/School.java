package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * School
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class School {
    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    Map<String, Student> convertListToMap(List<Student> students) {
        return students.stream().distinct().collect(Collectors.toMap(Student::getSurname,
                student -> student));
    }

    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().sorted(Comparator.comparing(Student::getScore).reversed())
                .distinct()
                .flatMap(Stream::ofNullable)
                .takeWhile(student -> student.getScore() >= bound)
                .collect(Collectors.toList());
    }
}