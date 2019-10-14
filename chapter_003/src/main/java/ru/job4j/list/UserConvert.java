package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**
 * UserConvert
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hashMap = new HashMap<>();
        for (User value : list) {
            hashMap.put(value.getId(), value);
        }
        return hashMap;
    }
}
