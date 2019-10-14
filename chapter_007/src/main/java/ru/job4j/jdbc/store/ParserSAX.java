package ru.job4j.jdbc.store;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * ParserSAX
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ParserSAX extends DefaultHandler {

    private long sum;

    public long getSum() {
        return sum;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("entry".equalsIgnoreCase(qName)) {
            String field = attributes.getValue("field");
            sum += Integer.valueOf(field);
        }
    }
}