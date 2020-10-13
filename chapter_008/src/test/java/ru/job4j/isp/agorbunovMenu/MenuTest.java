package ru.job4j.isp.agorbunovMenu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {

    /**
     * Add one element to menu.
     * Expect: list contains element - true.
     */
    @Test
    public void whenAddItemToListThenListNotEmpty() {
        Menu menu = new Menu("TestMenu");
        Element element = new ElementImpl("Test1");
        menu.getList().add(element);
        boolean expect = true;

        menu.drawMenu();

        assertThat(menu.getList().contains(element), is(expect));
    }

    /**
     * Set menu name TestMenu.
     * Expect menu name equals TestMenu.
     */
    @Test
    public void whenSetMenuNameThenDefineIt() {
        Menu menu = new Menu("TestMenu");
        String expect = "TestMenu";

        menu.drawMenu();

        assertThat(menu.getName(), is(expect));
    }

    /**
     * Draw menu output.
     * Have menu адача 1.
     * Have children: Задача 1.1, Задача 1.2 .
     * Задача 1.1 have children: Задача 1.1.1 , Задача 1.1.2 .
     * Задача 1.2 have children: Задача 1.2.1 , Задача 1.2.2 .
     * Задача 1.1.2 have child: Задача 1.1.1.1 .
     * Expect output:
     * Задача 1.
     * --Задача 1.1
     * ----Задача 1.1.1
     * ------Задача 1.1.1.1
     * ----Задача 1.1.2
     * --Задача 1.2
     * ----Задача 1.2.1
     * ----Задача 1.2.2
     */
    @Test
    public void whenAddMenuItemsThatHaveItemsTooThenDrawAllItemsLikeTree() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu("Задача 1.");
        Element e11 = new ElementImpl("Задача 1.1");
        Element e111 = new ElementImpl("Задача 1.1.1");
        Element e112 = new ElementImpl("Задача 1.1.2");
        Element e12 = new ElementImpl("Задача 1.2");
        Element e121 = new ElementImpl("Задача 1.2.1");
        Element e122 = new ElementImpl("Задача 1.2.2");
        Element e1111 = new ElementImpl("Задача 1.1.1.1");
        menu.getList().add(e11);
        e11.getElements().add(e111);
        e11.getElements().add(e112);
        menu.getList().add(e12);
        e12.getElements().add(e121);
        e12.getElements().add(e122);
        e111.getElements().add(e1111);
        menu.drawMenu();

        String expect = "Задача 1.\r\n--Задача 1.1\r\n----Задача 1.1.1\r\n------Задача 1.1.1.1\r\n"
                + "----Задача 1.1.2\r\n--Задача 1.2\r\n----Задача 1.2.1\r\n----Задача 1.2.2\r\n";

        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenAddMenuItemsThatHaveItemsTooThenDrawAllItemsLikeTree2() {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
        Menu menu = new Menu("Задача 1.");
        Element e11 = new ElementImpl("Задача 1.1");
        Element e111 = new ElementImpl("Задача 1.1.1");
        Element e112 = new ElementImpl("Задача 1.1.2");
        Element e12 = new ElementImpl("Задача 1.2");
        Element e121 = new ElementImpl("Задача 1.2.1");
        Element e122 = new ElementImpl("Задача 1.2.2");
        Element e123 = new ElementImpl("Задача 1.2.3");
        Element e1111 = new ElementImpl("Задача 1.1.1.1");
        Element e13 = new ElementImpl("Задача 1.3");
        Element e131 = new ElementImpl("Задача 1.3.1");
        Element e1311 = new ElementImpl("Задача 1.3.1.1");
        menu.getList().add(e11);
        e11.getElements().add(e111);
        e11.getElements().add(e112);
        menu.getList().add(e12);
        e12.getElements().add(e121);
        e12.getElements().add(e122);
        e12.getElements().add(e123);
        e111.getElements().add(e1111);
        menu.getList().add(e13);
        e13.getElements().add(e131);
        e131.getElements().add(e1311);
        menu.drawMenu();

        e11.execute();

//        String expect = "Задача 1.\r\n--Задача 1.1\r\n----Задача 1.1.1\r\n------Задача 1.1.1.1\r\n"
//                + "----Задача 1.1.2\r\n--Задача 1.2\r\n----Задача 1.2.1\r\n----Задача 1.2.2\r\n";

//        assertThat(out.toString(), is(expect));
    }

}
//Задача 1.
//--Задача 1.1
//----Задача 1.1.1
//------Задача 1.1.1.1
//----Задача 1.1.2
//--Задача 1.2
//----Задача 1.2.1
//----Задача 1.2.2
//----Задача 1.2.3
//--Задача 1.3
//----Задача 1.3.1
//------Задача 1.3.1.1
//Задача 1.1 - Do some action