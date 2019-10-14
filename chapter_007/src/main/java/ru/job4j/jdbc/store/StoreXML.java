package ru.job4j.jdbc.store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StoreXML
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StoreXML {
    private File file;

    public StoreXML(File file) {
        this.file = file;
    }

    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsage.Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(
                    new XmlUsage.Entries(list.stream().map(e -> new XmlUsage.Entry(e.getField())).collect(Collectors.toList())),
                    this.file
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
