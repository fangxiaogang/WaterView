package com.example.xiaogang.waterview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by xiaogang on 16/9/27.
 */
public class WaterView extends View {
    private ArrayList<Wave> list;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };
    public WaterView(Context context) {
        super(context);
    }

    public WaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        list = new ArrayList<>();
    }

    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (list.size() > 0 ){
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        for (Wave wave : list){
            canvas.drawCircle(wave.x, wave.y, wave.radius, wave.paint);
            wave.radius += 3;
            int alpha = wave.paint.getAlpha();
            if (alpha <=60){
                alpha = 0;
            }else {
                alpha -=3;
            }
            wave.paint.setStrokeWidth(wave.radius / 8);
            wave.paint.setAlpha(alpha);
            mhandler.sendEmptyMessageDelayed(1,50);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                float x = event.getX();
                float y = event.getY();
                deleteItem();
                Wave wave = new Wave(x, y);
                list.add(wave);
                //刷新界面
                invalidate();
                System.out.println("11");
                Log.d("1","22");
                break;
            case MotionEvent.ACTION_MOVE:
                float x1 = event.getX();
                float y1 = event.getY();
                deleteItem();
                Wave wave2 = new Wave(x1,y1);
                list.add(wave2);
                invalidate();
                System.out.println("22");
                break;
        }
        return true;
    }

    private void deleteItem() {
        for (int i = 0;i < list.size();i++){
            if(list.get(i).paint.getAlpha() == 0){
                list.remove(i);
            }
        }
    }
}
