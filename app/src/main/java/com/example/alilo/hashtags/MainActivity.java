package com.example.alilo.hashtags;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView mRecyclerView ;
    private DatabaseReference mDatabaseReference ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("hashTag");
        mRecyclerView = (RecyclerView)  findViewById(R.id.RecylUserid) ;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<hashTag, HashTagVeiwHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<hashTag, HashTagVeiwHolder>(hashTag.class, R.layout.users_layout,
                HashTagVeiwHolder.class, mDatabaseReference) {
            @Override
            protected void populateViewHolder(HashTagVeiwHolder viewHolder, final hashTag model, int position) {
                viewHolder.setName(model.getName());

                viewHolder.setImage(model.getImage(),getApplicationContext());
                //  viewHolder.setInvisibale();
// go to profile detaill
              final String User_id = getRef(position).getKey();//get user id

               viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent profileIntent = new Intent(MainActivity.this,TagsActivity.class);
                        profileIntent.putExtra("name",model.getName());
                        startActivity(profileIntent);
                    }
                });
            }
        };
                mRecyclerView.setAdapter(firebaseRecyclerAdapter);
            }


            public static class HashTagVeiwHolder extends RecyclerView.ViewHolder {

                View view;

                public HashTagVeiwHolder(View itemView) {
                    super(itemView);
                    view = itemView;
                }

                /*  public void setInvisibale(){
                      ImageView mageView =(ImageView) view.findViewById(R.id.OnlineimageView);

                      mageView.setVisibility(View.INVISIBLE);

                  }*/
                public void setName(String name) {
                    TextView Tv_name = (TextView) view.findViewById(R.id.Single);
                    Tv_name.setText(name);
                }
    /*    public void setStatu(String status) {
            TextView Tv_statu = (TextView) view.findViewById(R.id.Status);
            Tv_statu.setText(status);
        }*/
        public void setImage(String image, Context context) {
            CircleImageView imageV = (CircleImageView) view.findViewById(R.id.circleImageSingl);
            Picasso.with(context).load(image).placeholder(R.drawable.com_facebook_button_icon).into(imageV) ;

            //  imageV.setImageURI();
        }
            }


        }


