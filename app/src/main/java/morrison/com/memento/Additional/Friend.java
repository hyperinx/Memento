package morrison.com.memento.Additional;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michael on 15.11.2017.
 */

public class Friend {
    private int id;
    private String name;
    private int age;
    private String email;
    private String photo;

    Friend(int id, String name, int age, String email, String photo) {
        this.id = id;

        this.name = name;
        this.age = age;
        this.email = email;
        this.photo = photo;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
