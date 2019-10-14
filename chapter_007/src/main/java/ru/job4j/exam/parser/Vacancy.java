package ru.job4j.exam.parser;

import java.time.LocalDateTime;

/**
 * Vacancy
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Vacancy {

    private static final String LN = System.lineSeparator();

    private String vacancyName;
    private String description;
    private LocalDateTime actualDate;
    private String link;

    @Override
    public String toString() {
        return String.format(
                " Vacancy: %s%s Description: %s%s Link: %s%s Date: %s",
                vacancyName,
                LN,
                description,
                LN,
                link,
                LN,
                actualDate
        );
    }

    public Vacancy(String vacancyName, String description, LocalDateTime actualDate, String link) {
        this.vacancyName = vacancyName;
        this.description = description;
        this.actualDate = actualDate;
        this.link = link;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public LocalDateTime getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDateTime actualDate) {
        this.actualDate = actualDate;
    }
}
