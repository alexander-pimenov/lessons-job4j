package ru.job4j.isp.kaschukMenu;

import java.util.Optional;

public interface MenuTreeFind {
    Optional<MenuItem> findByName(String name);
}
