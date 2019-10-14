package ru.job4j.generic;

import java.util.Iterator;
import java.util.Objects;

abstract class AbstractStore<T extends Base> implements Store<T> {

    SimpleArray<T> store;

    public AbstractStore(SimpleArray<T> store) {
        this.store = store;
    }

    @Override
   public void add(T model) {
        store.add(model);
   }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int i = 0;
        for (Iterator<T> iterator = store.iterator(); iterator.hasNext(); i++) {
            var value = iterator.next();
            if (value.getId().equals(id)) {
                store.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int i = 0;
        for (Iterator<T> iterator = store.iterator(); iterator.hasNext(); i++) {
            var value = iterator.next();
            if (value.getId().equals(id)) {
                store.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int i = 0;
        for (Iterator<T> iterator = store.iterator(); iterator.hasNext(); i++) {
            var value = iterator.next();
            if (value.getId().equals(id)) {
                result = (T) store.get(i);
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "AbstractStore{" + "store=" + store + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractStore<?> that = (AbstractStore<?>) o;
        return store.equals(that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store);
    }
}
