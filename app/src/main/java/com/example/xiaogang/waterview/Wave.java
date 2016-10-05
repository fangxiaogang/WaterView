package com.example.xiaogang.waterview;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by xiaogang on 16/9/27.
 */
public class Wave {
    public float x;
    public float y;
    public Paint paint;
    public float width;
    public int radius;
    public int ranNum;
    public int [] randowColor = {Color.WHITE,Color.BLUE,Color.CYAN, Color.GREEN,Color.MAGENTA,Color.RED,Color.YELLOW};
    public Wave(float x, float y) {
        this.x = x;
        this.y = y;
        initData();
    }
    private void initData(){
        paint = new Paint();
        paint.setAntiAlias(true);
        ranNum=(int) (Math.random()*7);//[0,6]的随机数
        paint.setColor(randowColor[ranNum]);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(230);
        radius = 0;
        width = 0;

    }

}
