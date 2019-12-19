package ru.job4j.generic.store;

public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> simpleArray = new SimpleArray<>(10);

    @Override
    public void add(T model) {
        simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (id == null || model == null) {
            return false;
        }
        boolean result = false;
        int index = 0;
        for (T element : this.simpleArray) {
            if (element != null && element.getId().equals(id)) {
                this.simpleArray.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public T findById(String id) {
        return null;
    }
}
