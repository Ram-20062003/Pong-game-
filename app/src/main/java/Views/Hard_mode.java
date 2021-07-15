package Views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationAttributes;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.canvas1.MainActivity;
import com.example.canvas1.MainActivity3;
import com.example.canvas1.R;

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.getSystemServiceName;
import static com.example.canvas1.MainActivity3.*;

public class Hard_mode extends View{
    Rect rect,r,lr,br1,br2,box;
    Paint paint,paint_text1,paint_text2,paint_box;
    int change=1,lc=400,rc=50,b=0,vx=1,vy=1,pcheck=0,squarecolor,squaresize,p1=-1,width,height,score,tl,lives=3;
    float cx,cy,l=300f,s= 7.0f,speed;
    public int u=0;
    boolean value;
    public MediaPlayer mediaPlayer1;
    private static final int size=80;
    Context c=getContext();
    public Timer timer;
    Typeface typeface;
    public Hard_mode(Context context) {
        super(context);

        init(null);
    }
    public Hard_mode(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public Hard_mode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    public Hard_mode (Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray typedArray=getContext().obtainStyledAttributes(set, R.styleable.Customview);
        squarecolor=typedArray.getColor(R.styleable.Customview_Square_color, BLACK);
        typedArray.recycle();
        paint.setColor(squarecolor);
        paint.setTextSize(70);
    }
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
        score=p1+1;
        if(score==5*change) {
            change++;
        }
        canvas.drawText("LEVEL="+String.valueOf(change),10,80,paint);
        canvas.drawText("LIVES="+String.valueOf(lives),3*getWidth()/4-15,80,paint);
        if(pcheck==1) {
            br1.left=width-80;
            br1.top=height+100;
            br1.right=br1.left+500;
            br1.bottom=br1.top+130;
            br2.left=width-80;
            br2.top=br1.bottom+90;
            br2.right=br2.left+500;
            br2.bottom=br2.top+130;
            box.left=width-250;
            box.top=height-120;
            box.right=box.left+885;
            box.bottom=box.top+640;
            canvas.drawRect(box,paint_box);
            canvas.drawRect(br1,paint);
            canvas.drawRect(br2,paint);
            canvas.drawText("Restart", (float) (((br1.left+br1.right))/(2.8)), (float) ((br1.top+br1.bottom)/(1.95)), paint_text1);
            canvas.drawText("HOME", (float) (((br2.left+br2.right))/(2.7)), (float) ((br2.top+br2.bottom)/1.95), paint_text2);
            canvas.drawText("YOUR RECORD LEVEL:" + String.valueOf(change), width - 200, height, paint);
        }
        if(p1==10){
            lc=300;
            rc=40;
       }
        if(p1==15)
        {
            lc=200;
        }
        if(p1==20)
        {
            rc=30;
        }
        if(p1>=30){
            if(rc>=10)
               rc=rc-5;
        }
        r.left= (int) l;
        r.top=getHeight()-50;
        r.right=r.left+lc;
        r.bottom=r.top+50;
        canvas.drawRect(r,paint);
        if(b==0){
            cx=getWidth()/2;
            cy=getHeight()/2;
            tl=getWidth()/2-300;
            speed = (float) (getWidth()-300) / (getWidth() - 50);
            canvas.drawText("Tap  to  Play!!",cx/3+50,cy-100,paint_text1);}
        rect.left=(int)cx;
        rect.top= (int)cy;
        rect.right=rect.left+rc;
        rect.bottom=rect.top+rc;
        canvas.drawRect(rect,paint);
        lr.left=tl;
        lr.top=120;
        lr.right=lr.left+300;
        lr.bottom=lr.top+50;
        canvas.drawRect(lr,paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        value = super.onTouchEvent(event);

        timer=new Timer();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                b++;
                if (b == 1 ) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            cx = cx + (vx*s);
                            cy = cy + (vy*s);
                            if(cy<getHeight()/2)
                                tl = (int) (cx * speed);
                            postInvalidate();
                            collide();
                            runvar();
                        }
                    }, 0, 10);
                    return true;
                }
            case MotionEvent.ACTION_MOVE: {
                float x3=event.getX();
                if(x3+lc<=getWidth()&&x3>=0)
                    l=x3;
                postInvalidate();
                return true;
            }
            case MotionEvent.ACTION_UP:
                int bx=(int) event.getX();
                int by=(int)event.getY();
                if(pcheck==1&&bx>br1.left && bx<=br1.right&&by>=br1.top&&by<= br1.bottom)
                {
                    pcheck=0;
                    lives=3;
                    p1=-1;
                    lc=400;
                    rc=50;
                    s=7;
                    cx=getWidth()/2;
                    cy=getHeight()/2;
                    postInvalidate();
                }
                if(pcheck==1&&bx>br2.left && bx<=br2.right&&by>=br2.top&&by<= br2.bottom)
                {
                    System.exit(0);
                }
                return true;
        }

        return value;
    }

    private void collide() {

        if(cy>=r.top-(rc+s)&&cy+rc<=r.top+5)
        {
            if((cy+rc>rect.top&&cy+rc<rect.top+rc)){
                if((cx+rc>=r.left&&cx+rc<=r.left+rc/2)||(cx<=r.right&&cx>=r.right-10))
                    vx = -vx;
            }
            if((cx>=r.left &&cx+rc<=r.right)||(cx<=r.left&&cx+rc>=r.left)||(cx<=r.right&&cx+rc>r.right)){
                soundplay();

                vy=-vy;

            }}
    }

    private void soundplay()  {
        mediaPlayer1=MediaPlayer.create(getContext(),R.raw.hit_slide);
        mediaPlayer1.start();
    }
    private void soundplaylose(){
        mediaPlayer1=MediaPlayer.create(getContext(),R.raw.lose);
        mediaPlayer1.start();
    }
    public void runvar()
    {
        if(cx+rc>=getWidth()||cx<=0) {
                u=0;
            vx = -vx;
        }
        if(cy+rc<=getHeight()){
        if(cy<=lr.bottom){
            if((cx>=lr.left &&cx<=lr.right)||(cx+rc>=lr.left&&cx+rc<=(lr.left+lr.right)/2)){
                soundplay();
                vy=-vy;
                p1++;
                if(s<15)
                    s=s+1;
            }
        }}
        if(cy+rc>=getHeight()&&cy<=getHeight()) {
            lives--;
            soundplaylose();
            if(lives>0){
                cx=getWidth()/2;
                cy=getHeight()/2;
            }
            if(lives==0)
            {
                pcheck=1;}

        }
    }

}
