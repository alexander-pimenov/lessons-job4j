package ru.job4j.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Здесь рассматриваем Парсер JDOM для обновления или изменения существующего XML-файла xml_file7.xml
 *
 * Подключил зависимость в pom.xml
 * 		https://mvnrepository.com/artifact/org.jdom/jdom
 * 		<dependency>
 * 			<groupId>org.jdom</groupId>
 * 			<artifactId>jdom</artifactId>
 * 			<version>2.0.2</version>
 * 		</dependency>
 *
 * 	для работы с org.jdom2.*
 *
 * 	Имеели до изменения такое xml файл:
 * 	<company>
 *     <staff id="1">
 *         <firstname>yong</firstname>
 *         <lastname>mook kim</lastname>
 *         <nickname>mkyong</nickname>
 *         <salary>5000</salary>
 *     </staff>
 * </company>
 */



public class ModifyXMLFile {
    public static void main(String[] args) {

        try {

            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file7.xml");

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();

            //update staff id attribute
            Element staff = rootNode.getChild("staff");
            staff.getAttribute("id").setValue("2");

            //add new age element
            Element age = new Element("age").setText("28");
            staff.addContent(age);

            //update salary value
            staff.getChild("salary").setText("7000");

            //remove firstname element
            staff.removeChild("firstname");

            XMLOutputter xmlOutput = new XMLOutputter();

            //display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("C:\\projects\\lessons-job4j\\chapter_xml\\src\\main\\resources\\xml_file7.xml"));

            //xmlOutput.output(doc, System.out);

            System.out.println("File updated!");
        } catch (IOException io) {
            io.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
}
