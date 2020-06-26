package ru.job4j.io_pimalex.search;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Search2Test {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSearchFilesByExt() throws IOException {
        File file1 = folder.newFile("test1.abc");
        File file2 = folder.newFile("test2.abc");
        File file3 = folder.newFile("test3.abc");
        File file4 = folder.newFile("test4.bbb");
        File file5 = folder.newFile("test5.ccc");
        File file6 = folder.newFile("test6.ddd");
        File fileFolder = folder.newFolder("TempFolder");
        File file7 = folder.newFile("TempFolder/test7.bbb");
        File folderQQQ = folder.newFolder("TempFolder", "QQQ");
        File file8 = folder.newFile("TempFolder/QQQ/test8.ccc");
        File folderZZZ = folder.newFolder("TempFolder", "ZZZ");
        File file9 = folder.newFile("TempFolder/ZZZ/test9.abc");

        /* Чтобы в метод search() передать путь по которому искать файлы,
         * определим путь где создадутся временные файлы и потом
         * передадим его в параметр метода*/
        String parent = file1.getParent();
//        System.out.println(parent);

        List<Path> listExtAAA = Search2.search(Paths.get(parent), "abc");
        List<Path> listExtBBB = Search2.search(Paths.get(parent), "bbb");
        System.out.println(listExtAAA);
        System.out.println(listExtBBB);

        assertThat(listExtAAA.toString(), is(List.of("test9.abc", "test1.abc", "test2.abc", "test3.abc").toString()));
        assertThat(listExtBBB.size(), is(2));
    }

}