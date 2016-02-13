package me.climbingti.climbingtrainer.common;

import java.util.ArrayList;

/**
 * Created by Aleksi on 12.11.2015.
 */
public class Collection {

    protected ArrayList<Entity> list;

    public Collection() {
        list = new ArrayList<>();
    }

    public void add(Entity entity) {
        list.add(entity);
    }

    public Entity get(int index){
        return list.get(index);
    }

    public void delete(int index){
        list.remove(index);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void set(int index, Entity entity){
        list.set(index, entity);
    }
}