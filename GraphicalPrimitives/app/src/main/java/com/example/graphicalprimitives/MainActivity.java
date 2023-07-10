package com.example.graphicalprimitives;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Bitmap bg;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating a bitmap
        //RGB_565 --> R(Red) have 5 bit, G(Green) have 6 bit, B(Blue) have 5 bit
        bg = Bitmap.createBitmap(720,1280,Bitmap.Config.RGB_565);  // this is mutable object that means we can make changes in that
        img = findViewById(R.id.imageView);
        img.setImageBitmap(bg);

        //creating canvas object canvas is provide the surface to draw all shap and it is hold all method to draw shap
        Canvas canvas = new Canvas(bg);
        //creating Paint object that is used to fill color and set text size
        Paint paint = new Paint();

        //set color and text size
        paint.setColor(Color.YELLOW);
        paint.setTextSize(50);

        //To draw rectangle
        canvas.drawText("Rectangle",420,150,paint);
        canvas.drawRect(400,200,650,700,paint);

        //To draw a circle
        canvas.drawText("Circle",120,150,paint);
        canvas.drawCircle(200,350,150,paint);  // cx-->center for x cordinates and cy-->center for y cordinates

        //To draw square
        canvas.drawText("Square",120,800,paint);
        canvas.drawRect(50,850,350,1150,paint);//To draw sqraue there is no method is available so we used drawRect() and we pass parameter such a way that become square


        //To draw line
        canvas.drawText("Line",480,800,paint);
        canvas.drawLine(520,850,520,1150,paint);
        /*startX--> The x-cordinates of the start point of the line
        * startY-->The y-cordinates of the start point of the line*/
    }
}