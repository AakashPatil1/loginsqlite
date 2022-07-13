package com.aakash.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        username = (EditText) findViewById(R.id.username);
        //email  = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        signup.setOnClickListener(view -> {
            //EditText to String
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            //condition for check EditText
            if (user.equals("") || pass.equals("") || repass.equals("")){
                Toast.makeText(MainActivity.this, "Please enter all the fill", Toast.LENGTH_SHORT).show();
            }
            else{
                //condition for check password
                if(pass.equals(repass)){
                    //checkuser
                    Boolean checkuser = DB.checkusername(user);
                    //user not exists then insert
                    if (checkuser==false){
                        Boolean insert = DB.insertData(user,pass);
                        //data in database then show toast message
                        if (insert == true){
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this, "Registered failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "password not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signin.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });
    }
}