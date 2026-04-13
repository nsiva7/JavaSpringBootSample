package siva.nimmala.springbootsample.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class User {
    String id;
    String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
