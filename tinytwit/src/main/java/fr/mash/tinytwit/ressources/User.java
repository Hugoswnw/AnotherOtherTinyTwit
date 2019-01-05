package fr.mash.tinytwit.ressources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    public String username;

    @JsonBackReference
    public List<Key<User>> follows = new ArrayList<Key<User>>();

    public User(){}

    public User(String username){
        this.username = username;
    }

}
