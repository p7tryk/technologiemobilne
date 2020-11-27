package pl.pusb.kaniewski.myaplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity
{
    float a;
    float b;
    float c;
    String equation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_display_message);
//        String equation = a + "x^2 + " + b + "x - " + c;
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(equation);


        setContentView(new MyView(this));
        Intent intent = getIntent();
        a = (float) intent.getDoubleExtra(MainActivity.EXTRA_MESSAGE1,0);
        b = (float) intent.getDoubleExtra(MainActivity.EXTRA_MESSAGE2,0);
        c = (float) intent.getDoubleExtra(MainActivity.EXTRA_MESSAGE3,0);

    };
    public class MyView extends View
    {

        Pt[] myPath;
        class Pt{

            public float x, y;
            Pt(float _x, float _y){
                x = _x;
                y = _y;
            }
            public void input(float _x, float _y)
            {
                x = _x;
                y = _y;
            }
        }
        public MyView(Context context) {
            super(context);


        }



        @Override

        protected void onDraw(Canvas canvas) {

            // TODO Auto-generated method stub


            super.onDraw(canvas);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;

            int zoom =60;

            myPath = new Pt[500];

            for(int i=-(myPath.length/2); i<myPath.length/2; i++)
            {
                float j=(float)i/20;
                myPath[i+ myPath.length/2] = new Pt(width/2+j*zoom, ((float)height/2) - zoom*(a*j*j+b*j+c));
            }


            //x axis drawing
            Paint paintblack = new Paint();
            paintblack.setColor(Color.BLACK);
            paintblack.setStrokeWidth(3);
            paintblack.setStyle(Paint.Style.STROKE);

            Pt[] xaxis= {
                    new Pt(0,height/2),
                    new Pt(width, height/2)
            };
            Path pathxaxis = new Path();

            pathxaxis.moveTo(xaxis[0].x,xaxis[0].y);
            pathxaxis.lineTo(xaxis[1].x,xaxis[1].y);
            canvas.drawPath(pathxaxis,paintblack);
            //end xaxis

            //y axis drawing
            Pt[] yaxis= {
                    new Pt(width/2,0),
                    new Pt(width/2, height)
            };
            Path pathyaxis = new Path();

            pathyaxis.moveTo(yaxis[0].x,yaxis[0].y);
            pathyaxis.lineTo(yaxis[1].x,yaxis[1].y);
            canvas.drawPath(pathyaxis,paintblack);
            //end yaxis



            Paint paint = new Paint();

            paint.setColor(Color.RED);

            paint.setStrokeWidth(3);

            paint.setStyle(Paint.Style.STROKE);

            Path path = new Path();



            path.moveTo(myPath[0].x, myPath[0].y);

            for (int i = 1; i < myPath.length; i++){

                path.lineTo(myPath[i].x, myPath[i].y);

            }

            canvas.drawPath(path, paint);


            float x1, x2;
            float delta = b*b-4*a*c;
            x1 = (float) ((-b-Math.sqrt(delta))/(2*a));
            x2 = (float) ((-b+Math.sqrt(delta))/(2*a));



            equation = a + "x^2 + " + b + "x + " + c;
            paintblack.setTextSize(40);
            paintblack.setStyle(Paint.Style.FILL);
            canvas.drawText(equation,20,(float)(3.0/4.0)*height,paintblack);

            if(a!=0)
                equation = "x1=" + x1+" x2=" + x2 + "";
            else
                equation = "x0=" + (float)-c/b;
            canvas.drawText(equation,20,(float)(3.0/4.0)*height + 50,paintblack);



        }



    }

}

