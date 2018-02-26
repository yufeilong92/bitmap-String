package com.lawyee.myapplication;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private long time;
    private long time1;
    private long time2;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
//        initView();
        initData();
    }

    private void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        date = new Date();
        String s = bitmap2String(bitmap);
        Toast.makeText(MainActivity.this,""+s,Toast.LENGTH_SHORT).show();
        Bitmap bitmap1 = string2Bitmap(s);
        iv.setImageBitmap(bitmap1);
        time2 = date.getTime();
        Log.e("yfl", "开始解析的时间"+ time +"装换文字时间"+ time1 +"装换图片时间"+ time2);
        Log.e("yfl", "总共花的时间"+(time2 - time) );
    }

    private void initView() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/sdcard/8.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(fis);

        iv.setImageBitmap(bitmap);
        SharedPreferences sharedPreferences = getSharedPreferences("1.txt", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
    }

    public String bitmap2String(Bitmap bitmap){
       if (bitmap==null){
           try {
               throw  new IllegalArgumentException("参数异常");
           } catch (IllegalArgumentException e) {
               e.printStackTrace();
           }
           return null;
       }
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bStream);
        byte[]bytes=bStream.toByteArray();
        string= Base64.encodeToString(bytes,Base64.DEFAULT);
        time = date.getTime();
        return string;
    }
    public Bitmap string2Bitmap(String string) {
        // 将字符串转换成Bitmap类型
        if (string==null){
            try {
                throw  new IllegalArgumentException("参数异常");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return null;
        }
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        time1 = date.getTime();
        return bitmap;
    }
}
