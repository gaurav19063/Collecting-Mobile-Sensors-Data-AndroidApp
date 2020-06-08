package com.example.mt19044_ass4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorEventListener;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.List;
import android.location.LocationListener;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener{


    int i = 0;
    int j=0;
    List<ScanResult> data;


    String path_link = "";

    ContentValues content;

   Helper_db Helper;
    LocationManager locManager;

    String Acc_x, Acc_y, Acc_z;
    LocationListener LocLis;
    Context context;


    double latitude, longitude;
    WifiManager WifiMan;
    MediaRecorder MediaRec;
    SQLiteDatabase DB;
     SensorManager SenMan;
    private View view;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper=new Helper_db( this);
        MediaRec = new MediaRecorder();
        setContentView(R.layout.activity_main);
        SenMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        content = new ContentValues();
    }
    @Override



    protected void onDestroy() {
        super.onDestroy();

    }






    public void onClicked_acc(View view) {



        SenMan.registerListener((SensorEventListener) this, SenMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Acc_x = Float.toString(sensorEvent.values[0]);
            Acc_y = Float.toString(sensorEvent.values[1]);
            Acc_z = Float.toString(sensorEvent.values[2]);
            Log.i("tagx", Acc_x);
            Log.i("tagy", Acc_y);
            Log.i("tagz", Acc_z);
            String Time = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
            Log.i("date_acc", Time);
            Helper.addData(Acc_x, Acc_y, Acc_z,Time);

        }
    }

    public void onClicked_acc(View view) {






    }







































    public void onToggleClicked_acc(View view) {
        if (((ToggleButton) view).isChecked()) {
            // handle toggle on
            accelerometer_start();
        } else {
//
            stop_Accelerometer();
        }
    }
















    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onToggleClicked_loc(View view) {
        if (((ToggleButton) view).isChecked()) {
            // handle toggle on
            loc_start();
        } else {
//
            loc_stop();
        }
    }

    public void onToggleClicked_wifi(View view) {
        if (((ToggleButton) view).isChecked()) {
            // handle toggle on
            wifi_start();
        } else {
//
            wifi_stop();
        }
    }


//    public void onClicked_acc(View view) {
//
//        Log.i("tagx", "no data acc");
//
//
//        Cursor result=Helper.getAllData_acc();
//        if(result.getCount()==0)
//        { Print_msg("ERROR","Nothing Found");
//            return;
//        }
//        StringBuffer buffer=new StringBuffer();
//        while (result.moveToNext())
//        {
//            buffer.append("Id:"+result.getString(0)+"\n");
//            buffer.append("xcord:"+result.getString(1)+"\n");
//            buffer.append("ycord:"+result.getString(2)+"\n");
//            buffer.append("zcord:"+result.getString(3)+"\n");
//            buffer.append("time_stamp:"+result.getString(4)+"\n\n");
//
//        }
//        Print_msg("Data",buffer.toString());
////        Log.i("tags");
//    }

    public void Print_msg(String title, String Message)
    {
        AlertDialog.Builder build=new AlertDialog.Builder(this);
        build.setCancelable(true);
        build.setTitle(title);
        build.setMessage(Message);
        build.show();
    }


    public void onClicked_loc(View view) {
//        Log.i("tagx", "no data loc");

        Cursor result=Helper.getAllData_loc();

        if(result.getCount()==0)
        { Print_msg("ERROR","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (result.moveToNext())
        {
            buffer.append("Id:"+result.getString(0)+"\n");
            buffer.append("lattitude:"+result.getString(1)+"\n");
            buffer.append("longitude"+result.getString(2)+"\n");
            buffer.append("time_stamp:"+result.getString(3)+"\n\n");

        }
        Print_msg("Data",buffer.toString());
    }

    public void onClicked_wifi(View view) {
        Log.i("tagx", "no data wifi");
        Cursor result=Helper.getAllData_wifi();


        if(result.getCount()==0)
        { Print_msg("ERROR","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (result.moveToNext())
        {
            buffer.append("Id:"+result.getString(0)+"\n");
            buffer.append("ssid"+result.getString(1)+"\n");
            buffer.append("name"+result.getString(2)+"\n");
            buffer.append("time_stamp:"+result.getString(3)+"\n\n");

        }
        Print_msg("Data",buffer.toString());



    }

    public void onClicked_mic(View view) {
        Log.i("tagx", "no data mic");
        Cursor result=Helper.getAllData_micro();

        if(result.getCount()==0)
        { Print_msg("ERROR","Nothing Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (result.moveToNext())
        {
            buffer.append("Id:"+result.getString(0)+"\n");
            buffer.append("link"+result.getString(1)+"\n");
//            buffer.append("longitude"+result.getString(2)+"\n");
            buffer.append("time_stamp:"+result.getString(2)+"\n\n");
        }
        Print_msg("Data",buffer.toString());



    }


//    public void accelerometer_start() {
//
//        SenMan.registerListener((SensorEventListener) this, SenMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
//    }


//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            Acc_x = Float.toString(sensorEvent.values[0]);
//            Acc_y = Float.toString(sensorEvent.values[1]);
//            Acc_z = Float.toString(sensorEvent.values[2]);
//            Log.i("tagx", Acc_x);
//            Log.i("tagy", Acc_y);
//            Log.i("tagz", Acc_z);
//            String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
//            Log.i("date_acc", date);
//            Helper.insertData_acc(Acc_x, Acc_y, Acc_z,date);
//
//        }
//    }

    public void stop_Accelerometer() {
        SenMan.unregisterListener(this);
    }
//    }

    public void onClicked_Mic_play(View view) {
        mic_Play();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loc_start() {
        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

    }


    public void loc_stop() {
        locManager.removeUpdates(this);

        locManager = null;
    }


    public void wifi_start() {

        WifiMan = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (WifiMan.isWifiEnabled() == false) {
            Toast.makeText(getApplicationContext(), "Enable WiFi", Toast.LENGTH_LONG).show();
            WifiMan.setWifiEnabled(true);
        }

        WifiMan.startScan();
        data = WifiMan.getScanResults();
        n = data.size() - 1;
        Toast.makeText(this, "Scanning....", Toast.LENGTH_SHORT).show();
        String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
        try {
            while (n >= 0) {

                Log.i("tagy", data.get(n).SSID);

                Log.i("tagy", Integer.toString(data.get(n).level));

                Helper.insertData_wifi(data.get(n).SSID, Integer.toString(data.get(n).level),date);

                n--;

            }
        } catch (Exception e) {
        }


    }

    public void wifi_stop() {
        Toast.makeText(this, "Stopped....", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.i("tag_l", "hello");
        Log.i("tag_lo", Double.toString(latitude));
        Log.i("tag", Double.toString(longitude));
        String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
        Log.i("date_acc", date);
        Helper.insertData(Double.toString(latitude),Double.toString(longitude),date);


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



    public void onToggleClicked_mic(View view) throws IOException {
        if (((ToggleButton) view).isChecked()) {
            // handle toggle on
            mic_start();
        } else {
//
            mic_stop();
        }
    }


    public void mic_start() throws IOException {


        if (!path_link.startsWith("/")) {
            path_link = "/" + path_link;
        }
        if (!path_link.contains(".")) {
            path_link += ".3gp";
        }

        path_link = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording"+m+".3gp";
        m++;
        Log.i("Mic_path", path_link);
        String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
        Helper.insertData_mic(path_link,date);

//        String state = android.os.Environment.getExternalStorageState();
//        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
////            throw new IOException("SD Card is not mounted.  It is " + state+ ".");
//            Log.i("Mic_error","error mic");
//        }
//
//        // make sure the directory we plan to store the recording in exists
//        File directory = new File(path_link).getParentFile();
//
//        if (!directory.exists() && !directory.mkdirs()) {
////            throw new IOException("Path to file could not be created.");
//
//       }
        MediaRec=new MediaRecorder();
        MediaRec.setAudioSource(MediaRecorder.AudioSource.MIC);

        MediaRec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        MediaRec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        MediaRec.setOutputFile(path_link);

//        record.prepare();
        Log.i("tagr", "hellor");
//          MR.stop();
        MediaRec.prepare();
        MediaRec.start();
        Log.i("tagt", "hellot");
        Toast.makeText(getApplicationContext(), "Recording....", Toast.LENGTH_LONG).show();

    }
    public void mic_Play()
    {
        MediaPlayer MP = new MediaPlayer();
        try {
            MP.setDataSource(path_link);
            MP.prepare();
            MP.start();

            Toast.makeText(getApplicationContext(), "Playing....", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            // make something
        }



    }

    public void mic_stop() {
        MediaRec.stop();
        MediaRec.release();

//        MR = null;
        Toast.makeText(getApplicationContext(), "Recording stopped", Toast.LENGTH_LONG).show();
    }
















}
