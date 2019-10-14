package ru.job4j.jdbc.store;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

/**
 * ConvertXSQT
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertXSQT {
    public void convert(File source, File dest, File scheme) {
        try {
            String xsltTemplate = new String(Files.readAllBytes(scheme.toPath()));
            String fileFromStoreXML = new String(Files.readAllBytes(source.toPath()));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(
                    new StreamSource(
                            new ByteArrayInputStream(xsltTemplate.getBytes()))
            );
            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(fileFromStoreXML.getBytes())),
                    new StreamResult(dest)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
