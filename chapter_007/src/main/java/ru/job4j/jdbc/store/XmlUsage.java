package ru.job4j.jdbc.store;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * XmlUsage
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class XmlUsage {
    @XmlRootElement
    public static class Entries {

        private List<XmlUsage.Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> entry) {
            this.entry = entry;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entries) {
            this.entry = entries;
        }
    }

    @XmlRootElement
    public static class Entry {

        private int field;

        public Entry() {
        }

        public Entry(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }
}