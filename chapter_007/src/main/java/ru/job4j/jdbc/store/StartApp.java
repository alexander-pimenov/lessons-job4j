package ru.job4j.jdbc.store;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * StartApp
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartApp {

    private static final String LS = System.getProperty("line.separator");

    private static void createTemplate(File file) throws FileNotFoundException {
        String xslt = "<?xml version=\"1.0\"?>" + LS
                + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">" + LS
                + " <xsl:template match=\"/\">" + LS
                + "<entries>" + LS
                + "<xsl:for-each select=\"entries/entry\">\n" + LS
                + "<entry>" + LS
                + "<xsl:attribute name=\"field\">" + LS
                + "<xsl:value-of select=\"field\"/>" + LS
                + "</xsl:attribute>" + LS
                + "</entry>" + LS
                + "</xsl:for-each>" + LS
                + "</entries>" + LS
                + "</xsl:template>" + LS
                + "</xsl:stylesheet>";
        PrintWriter out = new PrintWriter(file.getPath());
        out.write(xslt);
        out.close();
    }

    private static void checkFileCreation(File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.init();
        storeSQL.generate(50000);

        File fileFromStoreXML = new File("fileFromStoreXML.xml");
        checkFileCreation(fileFromStoreXML);

        StoreXML storeXML = new StoreXML(fileFromStoreXML);
        storeXML.save(storeSQL.load());

        ConvertXSQT convertXSQT = new ConvertXSQT();

        File xsltTemplate = new File("template.xslt");
        checkFileCreation(xsltTemplate);
        createTemplate(xsltTemplate);

        File targetXML = new File("targetXML.xml");
        checkFileCreation(targetXML);

        convertXSQT.convert(fileFromStoreXML, targetXML, xsltTemplate);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        ParserSAX handler = new ParserSAX();
        saxParser.parse(targetXML, handler);

        System.out.println(handler.getSum());
    }
}
