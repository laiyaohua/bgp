package com.meizu.boardgameplatform;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.meizu.boardgameplatform.AvaLonGame.JumpActivity.AvalonWelcomeActivity;
import com.meizu.boardgameplatform.Werewolfkill.Activity.Activity.HomeActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv1 = (ImageView)findViewById(R.id.avalon_btn);
        ImageView iv2 = (ImageView)findViewById(R.id.wolf_btn);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvalonWelcomeActivity.class);
                startActivity(intent);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
//                intent.setComponent(new ComponentName("com.werewolfgame.werewolfgame",".activity.MainActivity"));
                Intent intent = new Intent();
                intent.setAction("com.intent.action.werewwolfgame");
//                intent.setComponent(new ComponentName("com.werewolfgame.werewolfgame","com.werewolfgame.werewolfgame.Activity.Activity.MainActivity"));
                startActivity(intent);

            }
        });
//        Button mButton = (Button) findViewById(R.id.avalon);
//        Button mButton1 = (Button) findViewById(R.id.werewolfkill);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AvalonWelcomeActivity.class);
//                startActivity(intent);
//            }
//        });
//        mButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
