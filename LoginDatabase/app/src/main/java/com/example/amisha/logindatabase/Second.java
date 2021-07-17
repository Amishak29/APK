package com.example.amisha.logindatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Second extends AppCompatActivity {
    EditText e3,e4,e5;
    Button b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        e5.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Second.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e3.getText().toString();
                String s2=e4.getText().toString();
                String s3=e5.getText().toString();
                if (s1.equals("")||s2.equals("")||s3.equals(""))
                {
                    Toast.makeText(Second.this,"Please,fill all the blanks",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase sql=openOrCreateDatabase("India",MODE_PRIVATE,null);
                    sql.execSQL("create table if not exists student(name varchar,email varchar,password varchar)");
                    String s4 = "select * from student where name='"+s1+"'and email='"+s2+"'";
                    Cursor cs=sql.rawQuery(s4,null);
                    if(cs.getCount()>0)
                    {
                        Toast.makeText(Second.this,"user exists",Toast.LENGTH_SHORT).show();
                        Intent j=new Intent(Second.this,MainActivity.class);
                        startActivity(j);
                        finish();
                    }
                    else
                    {
                        sql.execSQL("insert into student values('"+s1+"','"+s2+"','"+s3+"')");
                        Toast.makeText(Second.this,"registeration is done",Toast.LENGTH_SHORT).show();
                        Intent k=new Intent(Second.this,MainActivity.class);
                        startActivity(k);

                    }
                }
            }
        });
    }
}
