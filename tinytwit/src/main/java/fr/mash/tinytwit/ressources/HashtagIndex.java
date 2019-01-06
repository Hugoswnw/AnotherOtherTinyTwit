package fr.mash.tinytwit.ressources;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
@Cache
public class HashtagIndex {
    @Id
    public Long id;

    @Index
    public Message message;

    @Index
    public String word;

    public HashtagIndex(){}

    public HashtagIndex(Message message, String word){
        this.message = message;
        this.word = word;
    }

}
