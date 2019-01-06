package fr.mash.tinytwit.ressources;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

import java.util.Date;

@Entity
@Cache
public class Message {

    @Id
    public Long id;
    @Index
    public String content;

    //display purposes
    @JsonManagedReference
    public User author;

    @Index
    public Date postedAt;

    public Message(){

    }

    public Message(String content, User author){
        this.content = content;
        this.author = author;
        this.postedAt = new Date();
    }

}
