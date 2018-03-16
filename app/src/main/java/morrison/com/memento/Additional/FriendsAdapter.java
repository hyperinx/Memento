package morrison.com.memento.Additional;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import morrison.com.memento.R;

/**
 * Created by Michael on 15.11.2017.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    List<Friend> friends;

    FriendsAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_card_view, parent, false);
        return new FriendViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder friendViewHolder, int i) {

        friendViewHolder.friendName.setText(friends.get(i).getName());
        friendViewHolder.friendEmail.setText(friends.get(i).getEmail());
        friendViewHolder.friendAge.setText(String.valueOf(friends.get(i).getAge()));
        friendViewHolder.friendPhoto.setImageResource(R.drawable.ic_people);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        CardView friendCv;
        TextView friendName;
        TextView friendEmail;
        TextView friendAge;
        CircleImageView friendPhoto;

        FriendViewHolder(View itemView) {
            super(itemView);
            friendCv = (CardView) itemView.findViewById(R.id.friend_cv);
            friendName = (TextView) itemView.findViewById(R.id.friend_name);
            friendAge = (TextView) itemView.findViewById(R.id.friend_age);
            friendEmail = (TextView) itemView.findViewById(R.id.friend_email);
            friendPhoto = (CircleImageView) itemView.findViewById(R.id.friend_photo);
        }
    }
}