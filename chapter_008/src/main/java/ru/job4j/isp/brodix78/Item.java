package ru.job4j.isp.brodix78;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private Item parent;
    private String content;
    private Action action;
    private List<Item> kids = new ArrayList<>();

    public Item(Item parent, String content) {
        this.parent = parent;
        this.content = content;
    }

    public Item(String content, Action action) {
        this.content = content;
        this.action = action;
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Item> getKids() {
        return kids;
    }

    public void setKids(List<Item> kids) {
        this.kids = kids;
    }

    public Item addKid(String content, Action action) {
        Item kid = new Item(content, action);
        kid.setParent(this);
        this.kids.add(kid);
        return kid;
    }

    public Item addKid(Item kid) {
        kid.setParent(this);
        this.kids.add(kid);
        return kid;
    }

    public boolean removeKid(Item kid) {
        return kids.remove(kid);
    }
}
