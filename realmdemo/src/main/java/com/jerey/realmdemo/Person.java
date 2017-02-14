package com.jerey.realmdemo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Xiamin on 2017/2/14.
 */

public class Person extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private int age;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}