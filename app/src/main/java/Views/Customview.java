package Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.canvas1.R;

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class Customview extends View{
    Rect rect,r,lr,br1,br2,box;
    Paint paint,paint_text1,paint_text2,paint_box;
    int change=1,b=0,vx=1,vy=1,pcheck=0,squarecolor,p1=-1,width,height,score;
    public int sq=0;
    float cx,cy,l=300f,s= 10.0f,d,cd=0,x3;
    public int u=0;
    Context context1;
    boolean value;
    public MediaPlayer mediaPlayer;
    private static final int size=80;
    public Timer timer;
    Typeface typeface;
    public Customview(Context context) {
        super(context);
        init(null);
    }
    public Customview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public Customview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    public Customview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
             s = s + 1;
             change++;
         }
        canvas.drawText("SCORE="+String.valueOf(score),width,80,paint);
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
            canvas.drawText("YOUR RECORD SCORE:" + String.valueOf(score--), width - 200, height, paint);
        }
        r.left= (int) (l);
        r.top=getHeight()-50;
        r.right=r.left+300;
        r.bottom=r.top+50;
        canvas.drawRect(r,paint);
        if(b==0){
       cx=getWidth()/2;
        cy=getHeight()/2;
        canvas.drawText("Tap  to  Play!!",cx/3+50,cy-100,paint_text1);}
        rect.left=(int)cx;
        rect.top= (int)cy;
        rect.right=rect.left+50;
        rect.bottom=rect.top+50;
        canvas.drawRect(rect,paint);
        lr.left=0;
        lr.top=120;
        lr.right=lr.left+getWidth();
        lr.bottom=lr.top+20;
        canvas.drawRect(lr,paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        value = super.onTouchEvent(event);
        timer=new Timer();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                b++;
                d=event.getX();
                cd=d;
                if (b == 1 ) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            cx = cx + (vx*s);
                            cy = cy + (vy*s);
                            postInvalidate();
                            collide();
                            runvar();
                        }
                    }, 0, 10);
                   return true;
                }
            case MotionEvent.ACTION_MOVE: {

                x3=event.getX();
                if(l+x3-cd>0&&l+x3-cd<=getWidth()-300){
                l=l+x3-cd;
                cd=x3;}

                postInvalidate();
                return true;
            }
                case MotionEvent.ACTION_UP:
                    int bx=(int) event.getX();
                    int by=(int)event.getY();
                    if(pcheck==1&&bx>br1.left && bx<=br1.right&&by>=br1.top&&by<= br1.bottom)
                    {
                        pcheck=0;
                        p1=-1;
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
        if(cy>=r.top-(50+s)&&cy+50<=r.top+5)
        {
            if((cx>=r.left &&cx+50<=r.right)||(cx<=r.left&&cx+50>=r.left)||(cx<=r.right&&cx+50>r.right)){
             vy=-vy;
           soundplay();

            }}
        else if((cy+50>getHeight()-51&&cx==l-50)||(cy+50>getHeight()-51&&cx==l+300)){
                vy=-vy;
                soundplay();
        }
        else
        {
            vx=vx;
        }

    }

   private void soundplay()  {
       mediaPlayer=MediaPlayer.create(getContext(),R.raw.hit_slide);
       mediaPlayer.start();
    }
    private void soundplaylose()  {
        sq=1;
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.lose);
        mediaPlayer.start();
    }
    public void runvar()
    {
        if(cx+50>=getWidth()||cx<=0) {

            vx = -vx;
        }
      if(cy<=lr.bottom){
          vy=-vy;
          soundplay();
          p1++;
        }
      if(cy+50>=getHeight()&&cy-10<=getHeight()) {
          soundplaylose();
          cy=getHeight()*10;
          pcheck=1;/*   cx=getWidth()/2;
          cy=getHeight()/2;*/
      }
    }
}
