package com.example.apec_final;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MyPaintView myView, myView_2 ;
    Button btnCal, btnCal_2;
    int count = 0;
    Button connect_btn , grpchg;
    int selected = 0;
    Button solve, apply;
    TextView show_text , show_text2 , history1, history2, history3;
    Bitmap img;
    public Bitmap bitmap;
    String EncodedImage;
    public String function = "Sample " ;
    Bitmap img_scaled;
    int history = 0;
    public String tmp = " Sample " ;
    public String tmp_2 , tmp_3;
    public ImageView graph ;


    private Handler mHandler;

    private Socket socket;

    private DataOutputStream outstream;
    private DataInputStream instream;

    private int port = 9999;




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_action,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.help:{
                show();
                break;
            }

            case R.id.function_1: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function1.txt" );
                Toast.makeText(getApplicationContext(), function , Toast.LENGTH_LONG).show();
                break;
            }

            case R.id.save_1:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function", "function1.txt", function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_1:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function1.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;

            }
            case R.id.function_2: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function2.txt" );
                Toast.makeText(getApplicationContext(), function , Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_2:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function2.txt", function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_2:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function2.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_3: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function3.txt" );
                Toast.makeText(getApplicationContext(), function, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_3:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function3.txt",function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_3:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function3.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_4: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function4.txt" );
                Toast.makeText(getApplicationContext(), function , Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_4:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function4.txt", function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_4:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function4.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_5: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" + "/function/function5.txt");
                Toast.makeText(getApplicationContext(), function, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_5:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function5.txt", function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_5:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function5.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_6: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function6.txt");
                Toast.makeText(getApplicationContext(), function, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_6:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function6.txt",function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_6:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function6.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_7: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function7.txt");
                Toast.makeText(getApplicationContext(), function, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_7:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function7.txt",function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_7:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function7.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_8: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function8.txt" );
                Toast.makeText(getApplicationContext(), function, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_8:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function8.txt", function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_8:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function8.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_9: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function9.txt");
                Toast.makeText(getApplicationContext(), function, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_9:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function9.txt",function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_9:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function9.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.function_10: {
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function10.txt" );
                Toast.makeText(getApplicationContext(), function , Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.save_10:{
                WriteTextFile(Environment.getExternalStorageDirectory() + "/Documents"+ "/function", "function10.txt", function);
                Toast.makeText(getApplicationContext(), "수식을 저장합니다.", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.load_10:{
                function = ReadTextFile(Environment.getExternalStorageDirectory() + "/Documents" +"/function/function10.txt" );
                show_text.setText(function);
                Toast.makeText(getApplicationContext(), "수식을 불러옵니다.", Toast.LENGTH_LONG).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_lands);
        }
        setTitle("APEC");
        myView = new MyPaintView(this);
        myView_2 = new MyPaintView(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        connect_btn = (Button) findViewById(R.id.server);
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connect();

            }
        });

        solve = (Button) findViewById(R.id.solve);
        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = 1;
                Toast.makeText(getApplicationContext(), "식을 변환하고 있습니다. ", Toast.LENGTH_SHORT).show();

            }
        });

        grpchg = (Button) findViewById(R.id.graph);
        grpchg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Pictures/screenshot1.png");
                graph.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "그래프를 출력합니다. ", Toast.LENGTH_SHORT).show();

            }
        });

        apply = (Button) findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = 1;
                Toast.makeText(getApplicationContext(), "변수를 적용하고 있습니다. ", Toast.LENGTH_SHORT).show();

            }
        });


        //permission
        verifystoragePermission(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        btnCal = findViewById(R.id.btnCal);
        btnCal_2 = findViewById(R.id.btnCal_2);

        ((LinearLayout) findViewById(R.id.paintLayout)).addView(myView);
        ((LinearLayout) findViewById(R.id.paintLayout_2)).addView(myView_2);
        ((RadioGroup) findViewById(R.id.radioGroup)).setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId) {
                            case R.id.btnRed:
                                myView.mPaint.setColor(Color.rgb(255, 0, 0));
                                myView_2.mPaint.setColor(Color.rgb(255, 0, 0));
                                break;
                            case R.id.btnBlack:
                                myView.mPaint.setColor(Color.rgb(0, 0, 0));
                                myView_2.mPaint.setColor(Color.rgb(0, 0, 0));
                                break;
                            case R.id.btnGreen:
                                myView.mPaint.setColor(Color.rgb(0, 255, 0));
                                myView_2.mPaint.setColor(Color.rgb(0, 255, 0));
                                break;
                            case R.id.btnBlue:
                                myView.mPaint.setColor(Color.rgb(0, 0, 255));
                                myView_2.mPaint.setColor(Color.rgb(0, 0, 255));
                                break;
                        }
                    }
                });

        Button btnTh = findViewById(R.id.btnTh);
        btnTh.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (count % 3 == 1) {
                    btnTh.setText("30P");
                    myView.mPaint.setStrokeWidth(30);
                    myView_2.mPaint.setStrokeWidth(30);
                    count++;
                } else if (count % 3 == 2) {
                    btnTh.setText("10P");
                    myView.mPaint.setStrokeWidth(10);
                    myView_2.mPaint.setStrokeWidth(10);
                    count++;
                } else {
                    btnTh.setText("20P");
                    myView.mPaint.setStrokeWidth(20);
                    myView_2.mPaint.setStrokeWidth(20);
                    count++;
                }
            }
        }));


        ((Button) findViewById(R.id.btnClear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.mBitmap.eraseColor(Color.TRANSPARENT);
                myView_2.mBitmap.eraseColor(Color.TRANSPARENT);
                myView.invalidate();
                myView_2.invalidate();
            }
        });
        show_text = (TextView) findViewById(R.id.textCal);
        show_text2 = (TextView) findViewById(R.id.textCal_2);
        history1 = (TextView) findViewById(R.id.history1);
        history2 = (TextView) findViewById(R.id.history2);
        history3 = (TextView) findViewById(R.id.history3);
        graph = (ImageView) findViewById(R.id.imageView01);
    }

    public String ReadTextFile(String path){
        StringBuffer strBuffer = new StringBuffer();
        try{
            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";
            while((line=reader.readLine())!=null){
                strBuffer.append(line);
            }

            reader.close();
            is.close();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return strBuffer.toString();
    }

    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AFEC 이용 도움말");

        builder.setMessage("        =================================== \n\n\n\n" +
                "1. 인식 가능한 글자 : \n\n " +
                "   알파벳 대문자 , 알파벳 소문자 , 십진수 , 연산기호 \n\n" +
                "   연산기호 :  ( , { , [ , 사칙연산 , ! , = , 부정적분 , 부등호 \n\n\n" +
                "2. 인식 가능한 함수 : \n\n" +
                "   삼각함수 , 하이퍼볼릭 , 로그 ( 상용 , 자연 ), 조합 , 순열 , f(x) , e , i \n\n\n" +
                "3. 사용 방법 : \n\n\n\n" +
                "   a. 손글씨를 입력합니다. ( 입력에 맞춰 색을 변경해주세요 ! )\n\n" +
                "   b. 우측의 서버연결 -> 갤러리 저장 -> 계산의 순서로 버튼을 눌러주세요. \n\n" +
                "       (서버 연결은 어플리케이션 시작시에 한번만 연결해 주시면 됩니다.)\n\n" +
                "   c. 입력한 수식에 변수를 대입하고 싶으면 아랫쪽 화면에 손글씨를 추가로 입력합니다.\n\n" +
                "   d. 변수 적용버튼을 누르면 입력한 수식에 변수를 적용한 결과가 출력됩니다. \n\n\n\n" +
                "Tip. function 탭을 이용하여 나만의 수식을 저장 / 불러오기를 해보세요! \n\n\n\n" +
                "       ===================================\n\n\n\n");

        builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {
            }
        });
        builder.show();

    }


    public void ScreenshotButton(View view) {
        View rootView = myView;
        File screenshot = ScreenShot(rootView);

        if (screenshot != null) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenshot)));
        }

        btnCal.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(), "입력한 식을 갤러리에 저장합니다.", Toast.LENGTH_SHORT).show();

    }

    public File ScreenShot(View view) {
        btnCal.setVisibility(View.INVISIBLE);

        view.setDrawingCacheEnabled(true);
        Bitmap screenBitmap = view.getDrawingCache();

        String filename = "screenshot1" + ".png";
        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures" ,filename);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
            os.close();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        view.setDrawingCacheEnabled(false);
        return file;

    }
    public void ScreenshotButton_2(View view) {
        View rootView = myView_2;
        File screenshot = ScreenShot_2(rootView);

        if (screenshot != null) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenshot)));
        }

        btnCal_2.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(), "입력한 식을 갤러리에 저장합니다.", Toast.LENGTH_SHORT).show();

    }

    public File ScreenShot_2(View view) {
        btnCal_2.setVisibility(View.INVISIBLE);

        view.setDrawingCacheEnabled(true);
        Bitmap screenBitmap = view.getDrawingCache();

        String filename = "screenshot1" + ".png";
        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures" ,filename);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
            os.close();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        view.setDrawingCacheEnabled(false);
        return file;

    }

    public void WriteTextFile(String foldername, String filename, String contents){
        try{
            File dir = new File (foldername);
            //디렉토리 폴더가 없으면 생성함
            if(!dir.exists()){
                dir.mkdir();
            }
            //파일 output stream 생성
            FileOutputStream fos = new FileOutputStream(foldername+"/"+ filename ,false);
            //파일쓰기
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(contents);
            writer.flush();

            writer.close();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }







    public void connect() {

        mHandler = new Handler(Looper.getMainLooper());
        Log.w("connect", "연결 하는중");

        Thread checkUpdate = new Thread() {

            public void run() {

                // Access server
                try {
                    socket = new Socket("192.168.0.100", 9999);
                    Log.w("서버 접속됨", "서버 접속됨");
                } catch (IOException e1) {
                    Log.w("서버 접속 못함", "서버 접속 못함");
                    e1.printStackTrace();
                }
                Log.w("edit 넘어가야 할 값 : ","안드로이드에서 서버로 연결 요청");

                try{
                    outstream = new DataOutputStream(socket.getOutputStream());
                    instream = new DataInputStream(socket.getInputStream());
                    outstream.writeUTF("안드로이드에서 서버로 연결 요청");
                }catch(IOException e){
                    e.printStackTrace();
                    Log.w("버퍼","버퍼 생성 잘못 됨");
                }
                Log.w("버퍼","버퍼 생성 잘 됨");

                try{
                    while(true){

                        if (selected == 1){

                            String msg = function;
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            String filename = "screenshot1.png";
                            img = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Pictures"+"/"+ filename);
                            img_scaled = Bitmap.createScaledBitmap(img,500,200,true);
                            img_scaled.compress(Bitmap.CompressFormat.PNG,100,baos);
                            byte[] data = baos.toByteArray();
                            EncodedImage = Base64.encodeToString(data ,Base64.DEFAULT);
                            byte[] data2 = EncodedImage.getBytes();
                            ByteBuffer b1 = ByteBuffer.allocate(4);
                            b1.order(ByteOrder.BIG_ENDIAN);
                            b1.putInt(data.length);
                            outstream.write(b1.array(),0,4);
                            outstream.write(data);

                            byte[] data_2 = msg.getBytes();
                            ByteBuffer b5 = ByteBuffer.allocate(4);
                            b5.order(ByteOrder.BIG_ENDIAN);
                            b5.putInt(data_2.length);
                            outstream.write(b5.array(),0,4);
                            outstream.write(data_2);

                            show_text.setText("계산 중 입니다...");



                            data = new byte[4];
                            instream.read(data,0,4);
                            ByteBuffer b2 = ByteBuffer.wrap(data);
                            b2.order(ByteOrder.LITTLE_ENDIAN);
                            int length = b2.getInt();
                            data = new byte[length];
                            instream.read(data,0,length);
                            msg = new String(data,"UTF-8");
                            show_text.setText("입력 받은 수식 출력 : " + msg);

                            function = msg ;

                            data = new byte[4];
                            instream.read(data,0,4);
                            ByteBuffer b3 = ByteBuffer.wrap(data);
                            b3.order(ByteOrder.LITTLE_ENDIAN);
                            int length_2 = b3.getInt();
                            data = new byte[length_2];
                            instream.read(data,0,length_2);
                            msg = new String(data,"UTF-8");
                            show_text2.setText("계산 결과 출력 : " + msg);

                            tmp = msg ;


                            if ( history < 4 ) {
                                if (history % 3 == 0) {
                                    history1.setText(tmp);
                                    tmp_2 = tmp;
                                } else if (history % 3 == 1) {
                                    history1.setText(tmp);
                                    history2.setText(tmp_2);
                                    tmp_3 = tmp;
                                } else {
                                    history1.setText(tmp);
                                    history2.setText(tmp_3);
                                    history3.setText(tmp_2);
                                    tmp_2 = tmp_3 ;
                                    tmp_3 = tmp ;
                                }
                            } else {
                                history1.setText(tmp);
                                history2.setText(tmp_3);
                                history3.setText(tmp_2);
                                tmp_2 = tmp_3 ;
                                tmp_3 = tmp ;
                            }





                            data = new byte[4];
                            instream.read(data,0,4);
                            ByteBuffer b4 = ByteBuffer.wrap(data);
                            b4.order(ByteOrder.LITTLE_ENDIAN);
                            int length_3 = b4.getInt();
                            data = new byte[length_3];
                            instream.read(data,0,length_3);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,length_3);

                            File file = new File(Environment.getExternalStorageDirectory()+"/Pictures/graph1");
                            OutputStream out = null;
                            try {
                                file.createNewFile();
                                out = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                            }
                            catch(Exception e) {
                                e.printStackTrace();
                            }
                            finally{
                                try {
                                    out.close();
                                }
                                catch(IOException e) {
                                    e.printStackTrace();
                                }

                                }







                            history += 1 ;
                            selected = 0;

                        }
                        if (selected == 2){
                            //변환된 수식 전송 코드 추가 필요
                            String msg = function ;
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            String filename = "screenshot2.png";
                            img = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Pictures"+"/"+ filename);
                            img_scaled = Bitmap.createScaledBitmap(img,500,200,true);
                            img_scaled.compress(Bitmap.CompressFormat.PNG,100,baos);
                            byte[] data = baos.toByteArray();
                            /*EncodedImage = Base64.encodeToString(data ,Base64.DEFAULT);
                            byte[] data2 = EncodedImage.getBytes();*/
                            ByteBuffer b1 = ByteBuffer.allocate(4);
                            b1.order(ByteOrder.LITTLE_ENDIAN);
                            b1.putInt(data.length);
                            outstream.write(b1.array(),0,4);
                            outstream.write(data);
                            outstream.writeUTF(msg);


                            show_text2.setText("계산 중 입니다...");



                            data = new byte[8];
                            instream.read(data,0,8);
                            ByteBuffer b3 = ByteBuffer.wrap(data);
                            b3.order(ByteOrder.LITTLE_ENDIAN);
                            int length_2 = b3.getInt();
                            data = new byte[length_2];
                            instream.read(data,0,length_2);
                            msg = new String(data,"UTF-8");
                            show_text2.setText("계산 결과 출력 : " + msg);

                            tmp = msg ;


                            if ( history < 4 ) {
                                if (history % 3 == 0) {
                                    history1.setText(tmp);
                                    tmp_2 = tmp;
                                } else if (history % 3 == 1) {
                                    history1.setText(tmp);
                                    history2.setText(tmp_2);
                                    tmp_3 = tmp;
                                } else {
                                    history1.setText(tmp);
                                    history2.setText(tmp_3);
                                    history3.setText(tmp_2);
                                    tmp_2 = tmp_3 ;
                                    tmp_3 = tmp ;
                                }
                            } else {
                                history1.setText(tmp);
                                history2.setText(tmp_3);
                                history3.setText(tmp_2);
                                tmp_2 = tmp_3 ;
                                tmp_3 = tmp ;
                            }





                            /*data = new byte[16];
                            instream.read(data,0,16);
                            ByteBuffer b4 = ByteBuffer.wrap(data);
                            b4.order(ByteOrder.LITTLE_ENDIAN);
                            int length_3 = b4.getInt();
                            data = new byte[length_3];
                            instream.read(data,0,length_3);
                            msg = new String(data,"UTF-8");
                            show_text2.setText("계산 결과 출력 : " + msg); */



                            history += 1 ;
                            selected = 0;
                        }

                    }
                }catch(Exception e){

                }
            }
        };
        checkUpdate.start();
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private void verifystoragePermission(MainActivity Activity) {
        int permission = ActivityCompat.checkSelfPermission(Activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    private static class MyPaintView extends View {
        private Bitmap mBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mPaint;
        public MyPaintView(Context context) {
            super(context);
            mPath = new Path();
            mPaint = new Paint(Paint.DITHER_FLAG);
            mPaint.setColor(Color.rgb(0,0,0));
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(10);
            mPaint.setStyle(Paint.Style.STROKE);

        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(mBitmap, 0, 0, null); //지금까지 그려진 내용
            canvas.drawPath(mPath, mPaint); //현재 그리고 있는 내용
        }



        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            float press = event.getPressure();
            int press_1 = Math.round(255 * press);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPath.reset();
                    mPaint.setAlpha(press_1);
                    mPath.moveTo(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mPath.lineTo(x, y);
                    mPaint.setAlpha(press_1);
                    mCanvas.drawPath(mPath, mPaint); //mBitmap 에 기록
                    break;
                case MotionEvent.ACTION_UP:
                    mPath.lineTo(x, y);
                    mPath.reset();
                    break;
            }
            this.invalidate();
            return true;

        }
    }


}