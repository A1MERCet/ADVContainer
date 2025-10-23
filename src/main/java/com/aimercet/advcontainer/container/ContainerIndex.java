package com.aimercet.advcontainer.container;

import java.util.HashMap;

public class ContainerIndex<T> extends HashMap<T, IContainer> {

    public HashMap<IContainer, T> cache = new HashMap<>();

    @Override
    public IContainer put(T key, IContainer value) {
        cache.put(value, key);
        return super.put(key, value);
    }

    public void remove(IContainer container) {
        if (container == null) return;
        T t = cache.get(container);
        cache.remove(container);
        remove(t);
    }

}
