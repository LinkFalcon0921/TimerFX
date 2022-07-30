package com.TimerFX.util;

import com.TimerFX.worker.Clockers;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Deprecated
public class ClockersQueue implements Queue<Clockers> {

    /**List of items. */
    private final ArrayList<Clockers> items;

    private AtomicBoolean flagClock;

    public ClockersQueue() {
        items = new ArrayList<>();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object item) {
        return items.contains(item);
    }

    @Override
    public Iterator<Clockers> iterator() {
        return items.iterator();
    }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(T[] copyArray) throws ClassCastException{
        return items.toArray(copyArray);
    }


    @Override
    public boolean remove(Object clocker) {
        return items.remove(clocker);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Clockers> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        items.clear();
    }


    @Override
    public boolean add(Clockers clocker) {

        if(Objects.nonNull(items) || Objects.nonNull(clocker) || contains(clocker)){
            return false;
        }

        return offer(clocker);
    }

    @Override
    public boolean offer(Clockers clocker) {
        return items.add(clocker);
    }

    @Override
    public Clockers remove() throws NullPointerException{
        if(items.isEmpty()){
            throw new NullPointerException("Does not contain any value to remove.");
        }

        return poll();
    }

    @Override
    public Clockers poll() {
        return items.remove(0);
    }

    @Override
    public Clockers element() {
        if(items.isEmpty()){
            throw new NullPointerException("Does not contain any value to show.");
        }

        return peek();
    }

    @Override
    public Clockers peek() {
        return items.get(0);
    }
}
