package com.lm.costomtextcolor03;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Lm on 2018/1/23.
 * Email:1002464056@qq.com
 */

public class ColorTrackTextView extends TextView {

    //不变色字体的画笔
    private Paint mOriginPaint;
    //变色字体的画笔
    private Paint mChangePaint;
    //当前的进度
    private float mCurrentProgess=0.0f;
    //不同朝向
    private Direction mDirection = Direction.LEFT_TO_RIGHT;



    public enum Direction{
        LEFT_TO_RIGHT , RIGHT_TO_LEFT
    }

    private int mOriginColor= Color.BLACK;
    private int mChangeColor = Color.RED;

    public ColorTrackTextView(Context context) {
        this(context,null);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mOriginColor=array.getColor(R.styleable.ColorTrackTextView_originColor,mOriginColor);
        mChangeColor=array.getColor(R.styleable.ColorTrackTextView_changeColor,mChangeColor);
        mOriginPaint=getPaintByColor(mOriginColor);
        mChangePaint=getPaintByColor(mChangeColor);

        array.recycle();
    }

    private Paint getPaintByColor(int color) {
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        //防抖动
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;

    }

    //思路  左边用一个画笔去画，右边用一个画笔去画 不断改变中间值
    @Override
    protected void onDraw(Canvas canvas) {

        int middle=(int) (mCurrentProgess * getWidth());
        if (mDirection==Direction.LEFT_TO_RIGHT){
            //绘制不变色
            drawText(canvas,mChangePaint,0,middle);

            //绘制变色的
            drawText(canvas,mOriginPaint,middle,getWidth());
        }else {
            //绘制不变色
            drawText(canvas,mChangePaint,getWidth()-middle,getWidth());

            //绘制变色的
            drawText(canvas,mOriginPaint,0,getWidth()-middle);
        }


    }

    /**
     * 绘制text
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    public void drawText(Canvas canvas,Paint paint,int start,int end){
        canvas.save();
        //clipRect裁剪
        Rect rect=new Rect(start,0,end,getHeight());//左上右下
        canvas.clipRect(rect);

        String text=getText().toString();
        //获取字体宽度
        Rect bounds=new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        int x=getWidth()/2 - bounds.width()/2;

        //获取基线
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int dy = (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom;
        int baseLine=getHeight()/2 + dy;

        //画文字
        canvas.drawText(text,x,baseLine,paint);
        canvas.restore();//释放画布

    }

    public void setDirection(Direction direction){
        this.mDirection=direction;
    }
    public void setCurrentProgess(float currentProgess){
        this.mCurrentProgess=currentProgess;
        invalidate();
    }
    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }
    public void setOriginColor(int originColor) {
        this.mOriginPaint.setColor(originColor);
    }
}
