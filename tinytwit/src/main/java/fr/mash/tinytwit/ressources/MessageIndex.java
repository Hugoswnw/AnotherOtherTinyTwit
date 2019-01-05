package fr.mash.tinytwit.ressources;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class MessageIndex {

    @Id
    public Long id;

    @Index
    public Key<User> author;
    @Index
    public Key<Message> message;

    public MessageIndex() {}

    public MessageIndex(Key<User> author, Key<Message> message){
        this.author = author;
        this.message = message;
    }
}
