package ru.job4j.sax.example3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Задача №3 — дан элемент element в xml_file3.xml,
 * вывести имена и атрибуты всех внутренних элементов,
 * если элемент не найден — вывести это.
 * <p>
 * Тут есть три возможных сценария: root, mysql, oracle.
 * Это можно будет менять в парамтре конструктора SearchingXMLHandler(""oracle).
 * Тогда программа будет выводить всю инфу о всех элементах внутри.
 * Как же нам сделать такое?
 * А достаточно просто: нам достаточно объявить логическую переменную
 * isEntered, которая будет означать, внутри ли мы нужного нам элемента,
 * и если внутри – считывать все данные из startElement.
 * <p>
 * И как видим, в примере в конструктор SearchingXMLHandler мы передали
 * root элемент.
 */
public class SAXExample {
    private static boolean isFound;

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        SearchingXMLHandler handler = new SearchingXMLHandler("root");
        parser.parse(new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file3.xml"), handler);

        if (!isFound) {
            System.out.println("Элемент не был найден.");
        }
    }

    /**
     * В данном коде мы при входе в элемент, про который нам нужна информация,
     * выставляем флажок isEntered в true, что значит, что мы внутри элемента.
     * И как только мы оказались внутри элемента, мы просто каждый новый элемент
     * в startElement обрабатываем, зная, что он точно внутренний элемент нашего
     * элемента. Таким образом, мы выводим имя элемента и его название.
     * Если же элемент не был найден в файле, то у нас есть переменная
     * isFound, которая устанавливается тогда, когда элемент находится,
     * и если она false, то будет выведено сообщение, что элемент не найден.
     * <p>
     * <p>
     * Таким образом, мы получаем всю информацию про внутренние элементы и их атрибуты.
     */
    private static class SearchingXMLHandler extends DefaultHandler {

        private String element;
        private boolean isEntered;

        public SearchingXMLHandler(String element) {
            this.element = element;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (isEntered) {
                System.out.println(String.format("Найден элемент <%s>, его атрибуты:", qName));

                int length = attributes.getLength();
                for (int i = 0; i < length; i++)
                    System.out.println(String.format("Имя атрибута: %s, его значение: %s", attributes.getQName(i), attributes.getValue(i)));
            }

            if (qName.equals(element)) {
                isEntered = true;
                isFound = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals(element))
                isEntered = false;
        }
    }
}
