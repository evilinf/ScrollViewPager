package com.example.evil.scrollviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private MyScrollView msv;
    private int[] ids=new int[]{R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4,R.mipmap.a5,R.mipmap.a6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("MainActivity", "onCreate: ");打印一些调试信息

        msv=(MyScrollView)findViewById(R.id.myscrollView);
        for(int i=0;i<ids.length;i++){
            ImageView image=new ImageView(this);
            image.setBackgroundResource(ids[i]);
            msv.addView(image);
        }
    }
}
