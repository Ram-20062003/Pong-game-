package Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.canvas1.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class MultiCustomview extends View {
    Rect rect,r,lr,br1,br2,box,net,lr1,lr2,menu;
    Paint paint,paint_text1,paint_text2,paint_box,score_paint;
    int p2=0,j=0,net_left=0,tchk=1,rein=0,b=0,sound=0,first=0,vx=1,vy=1,pcheck=0,squarecolor,squaresize,p1=0,width,height,score,tl;
    float cx,cy,l=300f,s=11.0f,speed;
    public int u=0;
    boolean value;
    MediaPlayer mediaPlayer,mediaPlayerout;
    private static final int size=80;
    public Timer timer;
    Bitmap restart,home,crown ;
    Typeface typeface;
    public MultiCustomview(Context context) {
        super(context);

        init(null);
    }

    public MultiCustomview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MultiCustomview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MultiCustomview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(@Nullable AttributeSet set){
        rect=new Rect();
        r=new Rect();
        lr=new Rect();
        br1=new Rect();
        br2=new Rect();
        box=new Rect();
        net=new Rect();
        lr1=new Rect();
        lr2=new Rect();
        menu=new Rect();
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray typedArray=getContext().obtainStyledAttributes(set, R.styleable.Customview);
        squarecolor=typedArray.getColor(R.styleable.Customview_Square_color, BLACK);
        typedArray.recycle();
        paint.setColor(squarecolor);
        paint.setTextSize(70);
    }
    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth()/3;
        height=getHeight()/3;
        paint_text1=new Paint();
        paint_text1.setColor(GREEN);
        paint_text1.setTextSize(90);
        typeface= getResources().getFont(R.font.permanent_marker);
        paint_text1.setTypeface(typeface);
        paint_text2=new Paint();
        paint_box=new Paint();
        paint_text2.setColor(RED);
        paint_text2.setTextSize(90);
        paint_text2.setTypeface(typeface);
        paint_box.setColor(Color.parseColor("#FFFF99"));
        int stroke=10;
        paint_box.setStrokeWidth(stroke);
        paint_box.setStyle(Paint.Style.STROKE);
        paint.setTypeface(typeface);
        net.left=net_left;
        net.top=getHeight()/2-20;
        net.right=net.left+getWidth();
        net.bottom=net.top+20;
        canvas.drawRect(net,paint);
        score_paint=new Paint();
        score_paint.setColor(WHITE);
        menu.left=0;
        menu.top=0;
        menu.right=menu.left+getWidth();
        menu.bottom=menu.top+150;
        canvas.drawRect(menu,paint);
        score_paint.setTextSize(200);
        canvas.drawText(String.valueOf(p1),getWidth()-300,(getHeight()/4),score_paint);
        canvas.drawText(String.valueOf(p2),getWidth()-300,3*(getHeight()/4),score_paint);
        if(p1>p2)
        {
            canvas.drawText("Computer",getWidth()/3-30,100,paint_text1);
        }
        else if(p1<p2)
        {
            canvas.drawText("Player",getWidth()/3+40,100,paint_text1);
        }
        else
        {
            canvas.drawText("Tie",getWidth()/2-50,100,paint_text1);
        }
        crown=BitmapFactory.decodeResource(getResources(),R.drawable.crown);
        canvas.drawBitmap(crown,getWidth()/3-200,30,null);
        canvas.drawBitmap(crown,2*getWidth()/3+50,30,null);
        if(pcheck==1||rein==1)
        {
            canvas.drawText("Tap to Start!!",getWidth()/6+100,getHeight()/2+100,paint_text1);
            rein=0;
        }

        r.left= (int) l;
        r.top=getHeight()-50;
        r.right=r.left+300;
        r.bottom=r.top+50;
        canvas.drawRect(r,paint);
        if(b==0)
        {
            p1=0;
            p2=0;
            s=11.0f;
            cx=(float)getWidth()/2;
            cy=(float)getHeight()/2;
            tl=getWidth()/2-300;
            if(first==0) {
                canvas.drawText("Tap  to  Start!!", cx / 3 + 100, cy - 100, paint_text1);
                first=1;
            }
             u++;
        }
//        canvas.drawText(String.valueOf(u),getWidth()/3+300,getHeight()/2-100,paint_text1);
//        canvas.drawText(String.valueOf(b),getWidth()/3+50,getHeight()/2-100,paint_text1);
        rect.left=(int)cx;
        rect.top= (int)cy;
        rect.right=rect.left+50;
        rect.bottom=rect.top+50;
        canvas.drawRect(rect,paint);
        if(tchk%2==0)
        {
            lr.left=tl;
            lr.top=180;
            lr.right=lr.left+300;
            lr.bottom=lr.top+50;
            speed = (float) (getWidth()-300) / (getWidth() - 50);
            canvas.drawRect(lr,paint);
        }
        else
        {
            if(j==0)
            {
            lr1.left=tl;
            lr1.top=180;
            lr1.right=lr1.left+300;
            lr1.bottom=lr1.top+50;
            speed=(float)(getWidth()-500)/(getWidth()-50);
            canvas.drawRect(lr1,paint);
            }
            if(j==1){
                lr2.left=tl+175;
                lr2.top=180;
                lr2.right=lr2.left+300;
                lr2.bottom=lr2.top+50;
                speed = (float) (getWidth()-450) / (getWidth() - 50);
                canvas.drawRect(lr2,paint);
            }
        }
        restart = BitmapFactory.decodeResource(getResources(), R.drawable.restart);
        canvas.drawBitmap(restart,10,10,null);
        home=BitmapFactory.decodeResource(getResources(),R.drawable.home);
        canvas.drawBitmap(home,getWidth()-150,10,null);
    }
    private void soundplay()  {
        sound=1;
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.hit_slide);
      mediaPlayer.start();
    }
    private void soundplaylose(){
        if(sound==1)
            mediaPlayer.stop();
        mediaPlayerout=MediaPlayer.create(getContext(),R.raw.lose);
        mediaPlayerout.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        value = super.onTouchEvent(event);
        timer=new Timer();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                b++;
                if (b ==u&&pcheck==0) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            cx = cx + (vx * s);
                            cy = cy + (vy * s);
                            if (cy < getHeight() / 2&&cy>0) {
                                tl = (int) (cx * speed);
                                postInvalidate();
                            }
                            postInvalidate();
                            collide();
                            runvar();
                        }
                    }, 0, 10);
                    return true;
                }
            case MotionEvent.ACTION_MOVE: {
                float x3 = event.getX();
                if (x3 + 300 <= getWidth() && x3 >= 0)
                    l = x3;
                postInvalidate();
                return true;
            }
            case MotionEvent.ACTION_UP:{
                int rx=(int)event.getX();
                int ry=(int)event.getY();
                if(rx>getWidth()-150&&rx<getWidth()&&ry>10&&ry<150)
                {
                    System.exit(0);
                }
                if(rx>10&&rx<200&&ry>10&&ry<150)
                {
                   // timer.cancel();
                    b=0;
                    u=0;
                    rein=1;
                    pcheck=0;
                    cx=getWidth()/2;
                    cy=getHeight()/2;
                }
                if(pcheck==1&& rx>0&&rx<getWidth()&&ry>0&&ry<getHeight()&&b!=0){
                    cx=getWidth()/2;
                    cy=getHeight()/2;
                    s=11.0f;
                    postInvalidate();
                    pcheck=0;
                    vx=-vx;
                    vy=-vy;
                }
                return true;
            }
        }
        return value;
    }
        private void collide() {
        if(cy>=r.top-(50+s)&&cy+50<=r.top)
        {
            if((cx>=r.left &&cx+50<=r.right)||(cx<=r.left&&cx+50>=r.left)||(cx<=r.right&&cx+50>r.right)){
                vy=-vy;
                soundplay();
                tchk++;
                if(tchk%10==1||tchk%10==5)
                    j=1;
                if(tchk%10==3||tchk%10==7||tchk%10==9)
                    j=0;

            }}
        else if((cy+50>getHeight()-51&&cx==l-50)||(cy+50>getHeight()-51&&cx==l+300)){
            vy=-vy;
            soundplay();
            tchk++;
            if(tchk%10==1||tchk%10==5)
                j=0;
            if(tchk%10==3||tchk%10==7||tchk%10==9)
                j=1;
        }
        else
        {
            vx=vx;
        }

    }
    public void runvar()
    {

        int l_top,l_left,l_bottom ,l_right;
        if(cx+50>=getWidth()||cx<=0) {
            vx = -vx;

        }
        if(tchk%2==0)
        {
           l_top=lr.top;
           l_bottom=lr.bottom;
           l_right=lr.right;
           l_left=lr.left;

        }
        else
        {
            if(j==0){
            l_top=lr1.top;
            l_bottom=lr1.bottom;
            l_right=lr1.right;
            l_left=lr1.left;}
            else{
                l_top=lr2.top;
                l_bottom=lr2.bottom;
                l_right=lr2.right;
                l_left=lr2.left;
            }
        }
        if(cy<=l_bottom+s &&cy+20>=l_bottom){


            if(cy>l_bottom&&cy<l_bottom+25) {
                if((cx+50>=l_left&&cx+50<=l_left+25)||(cx+25<=l_right&&cx>=l_right-25))
                vx = -vx;
            }
            if((cx>=l_left &&cx<=l_right)||(cx+50>=l_left&&cx+50<=(l_left+l_right)/2)){
            vy=-vy;
                soundplay();
            if(s<20)
                s=s+1;
        }



        }
        if(cy<=0&&cy+50>=0){
            soundplaylose();
            if(pcheck==0)
                p2++;
            pcheck=1;

        }
        if(cy+50>=getHeight()&&cy<=getHeight()) {
            soundplaylose();
            if(pcheck==0)
               p1++;
            pcheck=1;
        }
    }
}
