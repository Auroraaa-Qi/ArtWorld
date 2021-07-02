package auroraaa.yr.androidart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextname;
    EditText editTextpwd1, editTextpwd2;
    EditText editTextphone;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextname = this.findViewById(R.id.editUsername);
        editTextphone = this.findViewById(R.id.editPhone);
        editTextpwd1 = this.findViewById(R.id.edit_pwd);
        editTextpwd2 = this.findViewById(R.id.pwd_verity);
        registerBtn = this.findViewById(R.id.register_button);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                final String name = editTextname.getText().toString().trim();
                final String password = editTextpwd1.getText().toString().trim();
                final String phone = editTextphone.getText().toString().trim();

                if(check()){
                    new Thread(){
                        @Override
                        public void run(){
                            try {
                                String path = "http://49.232.112.110:8080/ForAndroid/registerServlet?name="+name+"&password="+password+"&phone="+phone;
                                URL url = new URL(path);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.connect();
                                int responseCode = conn.getResponseCode();
                                if(responseCode==200){
                                    InputStream is = conn.getInputStream();
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    byte[] buffer = new byte[1024];
                                    int len = -1;
                                    while((len=is.read(buffer))!=-1){
                                        baos.write(buffer, 0, len);
                                    }
                                    final String result = baos.toString();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(result.equals("1")){
                                                Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent();
                                                intent.putExtra("username", name);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(RegisterActivity.this, "当前用户名已存在",Toast.LENGTH_SHORT).show();
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
                break;
        }
    }

    public boolean check(){
        boolean isOK = false;
        String name1 = editTextname.getText().toString();
        if(name1.isEmpty()){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return  isOK;
        }
        if(name1.length()>10){
            Toast.makeText(this,"昵称不能超过10位",Toast.LENGTH_LONG).show();
            return isOK;
        }

        String pw1 = editTextpwd1.getText().toString();
        String pw2 = editTextpwd2.getText().toString();
        if(pw1.isEmpty()){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
            return isOK;
        }
        if(!pw2.equals(pw1)){
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_LONG).show();
            return isOK;
        }

        String telenum1 = editTextphone.getText().toString();
        if(telenum1.isEmpty()){
            Toast.makeText(this,"手机不能为空",Toast.LENGTH_LONG).show();
            return isOK;
        }
        else{
            String p="[1][3578]\\d{9}";
            boolean match= Pattern.matches(p, telenum1);
            if (!match) {
                Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                return isOK;
            }
            else{
                isOK=true;
                return isOK;
            }
        }
    }
}
