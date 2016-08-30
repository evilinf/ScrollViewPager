package com.example.evil.scrollviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liqianyu on 2016/7/25.
 */
public class MyScrollView extends ViewGroup {

    private Context ctx;

    public MyScrollView(Context context, AttributeSet attrs){
        super(context,attrs);
        this.ctx=context;
        initView();
    }
    public void initView(){
        detector=new GestureDetector(ctx, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }
//响应手指在屏幕滑动的事件
            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                //移动屏幕
                scrollBy((int)v,0);
                return false;

            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }
//判断快速滑动事件
            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }
    //对子view进行布局，确定子view的位置
    //changed若为true，则说明布局发生改变
    // l/t/r/b  是指当前viewgroup在其父类中的位置
    @Override
    protected void onLayout(boolean changed,int l,int t,int r,int b){
        for(int i=0;i<getChildCount();i++){
            View view=getChildAt(i);//取得下标为i的子view
            view.layout(0+i*getWidth(),0,getWidth()+i*getWidth(),getHeight());
            //指定子view的位置，左，上，右，下，是只在viewGroup中的位置
        }
    }

    //手势识别的工具类
    private GestureDetector detector;
    private int currId=0;//当前的ID值   显示在屏幕上imageView的下标
    private int firstX=0;//down事件时的x坐标
    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        detector.onTouchEvent(event);
        //添加自己的时间解析
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                firstX=(int)event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                int nextId=0;
                if(event.getX()-firstX>getWidth()/2){//屏幕向右滑动，当前id为currId-1
                    nextId=currId-1;
                }else if(event.getX()-firstX<getWidth()/2){
                    nextId=currId+1;
                }else{
                    nextId=currId;
                }
                moveToDest(nextId);
//                scrollTo(0,0);
                break;
            default:
                break;
        }
        return true;
    }
    //移动到指定的屏幕上（屏幕的下标）
    private void moveToDest(int nextId){
        //对nextId进行判断，确保是在合理的范围，即nextId<=getChildCount()-1
        currId=(nextId>=0)?nextId:0;
        currId=(nextId<=getChildCount()-1)?nextId:(getChildCount()-1);
        scrollTo(currId*getWidth(),0);
    }
}
