package com.example.myapplicationlistfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FriendsBox extends ArrayAdapter<Friends> {

    public FriendsBox(@NonNull Context context, int resource, @NonNull List<Friends> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View MyView;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        MyView = layoutInflater.inflate(R.layout.friend_box_template,null);

        ImageView Vector = MyView.findViewById(R.id.Vector);
        TextView Name = MyView.findViewById(R.id.Name);
        TextView Age = MyView.findViewById(R.id.Age);
        TextView Sexe = MyView.findViewById(R.id.Sexe);

        Friends friend = getItem(position);

        Vector.setImageResource(friend.getProfile());
        Name.setText(friend.getName());
        Age.setText(friend.getAge());
        Sexe.setText(friend.getSexe());

        return MyView;
    }
}
