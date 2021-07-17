package com.example.amisha.quizapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Second extends AppCompatActivity {
    Button b2;
    RadioButton r6,r7,r8,r9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        b2=(Button)findViewById(R.id.button);
        r6=(RadioButton)findViewById(R.id.radioButton6);
        r7=(RadioButton)findViewById(R.id.radioButton7);
        r8=(RadioButton)findViewById(R.id.radioButton8);
        r9=(RadioButton)findViewById(R.id.radioButton9);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(r6.isChecked())
                {
                    ++MainActivity.score;
                }
                else
                {
                    --MainActivity.score;
                }
                Intent i=new Intent(Second.this,Third.class);
                startActivity(i);
                finish();
            }

        });
    }
}
