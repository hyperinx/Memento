package morrison.com.memento.Additional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import morrison.com.memento.R;

public class FriendsActivity extends AppCompatActivity {
    private List<Friend> friends;
    private RecyclerView friendsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_recycle_view);


        friendsRecyclerView = (RecyclerView)findViewById(R.id.friends_rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        friendsRecyclerView.setLayoutManager(llm);
        friendsRecyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeAdapter(){
        FriendsAdapter friendsAdapter = new FriendsAdapter(friends);
        friendsRecyclerView.setAdapter(friendsAdapter);
    }

    // This method creates an ArrayList that has three Person objects
    public void initializeData() {

        /*try {
            JSONObject friendsArray = new JSONObject("{\n" +
                    "  \"User1\": {\n" +
                    "      \"id\": 1,\n" +
                    "      \"name\": Michael Morrison,\n" +
                    "      \"age\": 21\n" +
                    "      \"email\": \"m.morrison8996@gmail.com\"\n" +
                    "      \"photo\": \"\"\n" +
                    "    }\n" +
                    "}");
            friends = new ArrayList<>();

            Iterator<String> temp = friendsArray.keys();
            while (temp.hasNext()) {
                String key = temp.next();
                JSONObject friendData = new JSONObject(friendsArray.get(key).toString());

                friends.add(new Friend(Integer.parseInt(friendData.get("id").toString()),
                        friendData.get("name").toString(),
                        Integer.parseInt(friendData.get("age").toString()),
                        friendData.get("email").toString(),
                        friendData.get("photo").toString()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

*/      friends = new ArrayList<Friend>();
        friends.add(new Friend(1, "Michael Morrison", 21, "m.morrison555@gmail.com", ""));

    }

    private void removeFromFriendsList(List<Friend> friends, int id) {

        List<Friend> tempListOfFriends = new ArrayList<Friend>(friends);
        int idx = -1;
        for (Friend friend : tempListOfFriends) {

            idx++;
            if (friend.getId() == id) {

                friends.remove(idx);
            }
        }
        tempListOfFriends = null;
    }
}
