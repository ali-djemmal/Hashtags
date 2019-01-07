package com.example.alilo.hashtags;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class detaillActivity extends AppCompatActivity {
TextView textView,textView2; String name,name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill);
        textView=(TextView) findViewById(R.id.hashtext);
        textView2=(TextView) findViewById(R.id.textView2);
        name= (String) getIntent().getSerializableExtra("name");
     //   name= (String) getIntent().getSerializableExtra("name2");
        textView.setText(name);
     //   textView.setText(name2);
    }

    public void copy(View view) {
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(this.CLIPBOARD_SERVICE);
        // Creates a new text clip to put on the clipboard
        ClipData clip = ClipData.newPlainText("simple text",name);
        // Set the clipboard's primary clip.
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "the hashtag is copeid in press papie", Toast.LENGTH_SHORT).show();
    }
}
