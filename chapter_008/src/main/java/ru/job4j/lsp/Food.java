package ru.job4j.lsp;

import java.time.LocalDate;

/**
 * Food
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Food {
    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0;
    }

    public void setDisscount(double disscount) {
        this.discount = disscount;
    }

    @Override
    public String toString() {
        return "Food{"
                +
                "name='" + name + '\''
                +
                ", expireDate=" + expireDate
                +
                ", createDate=" + createDate
                +
                ", price=" + price
                +
                ", discount=" + discount
                +
                '}';
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDisscount() {
        return discount;
    }
}
