package auroraaa.yr.androidart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mysql.jdbc.log.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import auroraaa.yr.library.utils.DBUtils;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                final String name = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
                else {
//                    HashMap<String, String> map = DBUtils.getUserInfoByName(name);
//                    if(map.get("password").equals(password)){
//                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        intent.putExtra("username", name);
//                        setResult(RESULT_OK, intent);
//                        finish();
//                    }
                    new Thread(){
                        @Override
                        public void run(){
                            try {
                                String path = "http://49.232.112.110:8080/ForAndroid/loginServlet?name="+name+"&password="+password;
                                URL url = new URL(path);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.connect();
                                int responseCode = conn.getResponseCode();
                                System.out.println(responseCode);
                                if(responseCode==200){
                                    System.out.println("200");
                                    InputStream is = conn.getInputStream();
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    byte[] buffer = new byte[1024];
                                    int len = -1;
                                    System.out.println("这里");
                                    while((len=is.read(buffer))!=-1){
                                        baos.write(buffer, 0, len);
                                    }
                                    final String result = baos.toString();
                                    System.out.println("result："+result);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(result.equals("1")){
                                                loadingProgressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent();
                                                intent.putExtra("username", name);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            }
                                            else{
                                                loadingProgressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(LoginActivity.this, "账号或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        });
    }
}