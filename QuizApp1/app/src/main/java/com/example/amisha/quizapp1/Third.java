package com.example.amisha.quizapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Third extends AppCompatActivity {
    Button b3;
    RadioButton r10,r11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        b3=(Button)findViewById(R.id.button);
        r10=(RadioButton)findViewById(R.id.radioButton10);
        r11=(RadioButton)findViewById(R.id.radioButton11);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(r11.isChecked())
                {
                    ++MainActivity.score;
                }
                else
                {
                    --MainActivity.score;
                }
                Intent k=new Intent(Third.this,Fourth.class);
                startActivity(k);
                finish();
            }
        });

    }
}
