package ru.job4j.io_pimalex.zip;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class ExtensionExcludePredicate implements Predicate<Path> {

    private final List<String> extensions;

    public ExtensionExcludePredicate(List<String> extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean test(Path path) {
        return extensions.stream()
                .anyMatch(ext->path.toFile().getName().endsWith(ext));
    }

    public List<String> getExtensions() {
        return extensions;
    }
}
