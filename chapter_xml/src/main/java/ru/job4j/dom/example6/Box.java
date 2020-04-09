package ru.job4j.dom.example6;

public class Box {
    private Integer id;
    private Integer parentId;

    public Box(Integer id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return String.format("Box id=%d, parentId=%d", id, parentId);

    }
}
