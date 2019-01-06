package fr.mash.tinytwit;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.QueryKeys;
import fr.mash.tinytwit.ressources.HashtagIndex;
import fr.mash.tinytwit.ressources.MessageIndex;
import fr.mash.tinytwit.ressources.Message;
import fr.mash.tinytwit.ressources.User;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(name = "tinytwitsmah", version = "v1")
public class API {

    @ApiMethod(name = "user.get.all", httpMethod = ApiMethod.HttpMethod.GET, path = "user_get_all")
    public List<User> getUsers(){
        return ofy().load().type(User.class).list();
    }

    @ApiMethod(name = "user.get", httpMethod = ApiMethod.HttpMethod.GET, path = "user_get")
    public User getUser(@Named("username") String username){
        return ofy().load().type(User.class).id(username).now();
    }

    @ApiMethod(name = "user.add", httpMethod = ApiMethod.HttpMethod.POST, path = "user_add")
    public User addUser(@Named("username") String username) throws Exception{
        if(username == null && username.length() >= 2){
            throw new Exception("Invalid username");
        }

        if(getUser(username)!=null)
            throw new Exception("Existing username");

        User user = new User(username);
        ofy().save().entity(user);
        return user;
    }

    public QueryKeys<MessageIndex> getFollowKey(@Named("follower") String follower, @Named("followed") String followed) throws Exception{
        User from = getUser(followed), to = getUser(followed);
        QueryKeys<MessageIndex> follows = ofy().load().type(MessageIndex.class).filter("follower == ",from).filter("followed", to).keys();
        return follows;
    }

    @ApiMethod(name = "follow.add", httpMethod = ApiMethod.HttpMethod.POST, path = "follow_add")
    public void follow(@Named("follower") String follower, @Named("followed") String followed) throws Exception{
        User from = getUser(follower), to = getUser(followed);
        if(from==null)
            throw new Exception("Invalid following user");

        if(to==null)
            throw new Exception("Invalid followed user");

        Key<User> toKey = Key.create(to);

        if(from.follows.contains(toKey))
            throw new Exception("Already followed user");

        from.follows.add(toKey);
        ofy().save().entities(from);
    }

    @ApiMethod(name = "follow.delete", httpMethod = ApiMethod.HttpMethod.DELETE, path = "follow_delete")
    public void unfollow(@Named("follower") String follower, @Named("followed") String followed) throws Exception{
        User from = getUser(follower), to = getUser(followed);
        if(from==null)
            throw new Exception("Invalid following user");

        if(to==null)
            throw new Exception("Invalid followed user");

        Key<User> toKey = Key.create(to);

        if(!from.follows.contains(toKey))
            throw new Exception("Already deleted");

        from.follows.remove(toKey);
        ofy().save().entities(from);
    }

    @ApiMethod(name = "message.add", httpMethod = ApiMethod.HttpMethod.POST, path = "message_add")
    public void addMessage(@Named("content") String content, @Named("author") String authorUsername) throws Exception {
        User author = getUser(authorUsername);
        if(author==null)
            throw new Exception("Invalid user");

        Message m = new Message(content, author);
        Key k = ofy().save().entity(m).now();
        ofy().save().entity(new MessageIndex(Key.create(author), k));

        String[] words = m.content.split(" ");
        for (int i = 0; i<words.length; i++){
            String word = words[i];
            if(word.charAt(0)=='#'){
                ofy().save().entity(new HashtagIndex(m, word));
            }
        }
    }

    @ApiMethod(name = "message.timeline", httpMethod = ApiMethod.HttpMethod.GET, path = "message_get_timeline")
    public List<Message> getTimeline(@Named("username") String username, @Named("amount") int amount) throws Exception {
        User user = getUser(username);
        ArrayList<Key<Message>> keys = new ArrayList<Key<Message>>();

        user.follows.forEach((key) -> ofy().load().type(MessageIndex.class).filter("author == ",key).list().forEach((mi) -> keys.add(mi.message)));
        ArrayList<Message> messages = new ArrayList<>(ofy().load().keys(keys).values());

        messages.sort((o1, o2) -> -o1.postedAt.compareTo(o2.postedAt));

        return messages.subList(0, Math.min(amount, messages.size()));
    }


    @ApiMethod(name = "hashtag.messages", httpMethod = ApiMethod.HttpMethod.GET, path = "hashtag_get_messages")
    public List<Message> getHashtasMessages(@Named("word") String word, @Named("amount") int amount) throws Exception {
        List<Message> messages = new ArrayList<Message>();
        ofy().load().type(HashtagIndex.class).filter("word == ", word).list().forEach((m) -> messages.add(m.message));
        messages.sort((o1, o2) -> -o1.postedAt.compareTo(o2.postedAt));
        return messages;
    }


}
