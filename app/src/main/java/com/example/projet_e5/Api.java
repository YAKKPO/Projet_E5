package com.example.projet_e5;

import android.annotation.SuppressLint;
import android.os.Build;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Base64.*;
import org.json.*;


public class Api {

    private  String Url = "https://www.zeyu.site/api/function/func.php?func=c950j8gYQj8dbAT9XlTNaA==&token=S8jlgW4doHq1Lh9mfqSu6Q&t=s9MMfSKn4u46ET2uRKz5Xw==&values=SZCaozopISyQRjADuhiSMQ==";
    private String Token = "S8jlgW4doHq1Lh9mfqSu6Q";

    private String Key_AES = "bUYJ3nTV6VBasdJF";

    public String Send_Api(String name_function,String Values,String name_table){
        String Prepare_Url = this.Url;

        try{
            Runnable networkTask = new Runnable() {
                @Override
                public void run() {
                    try{
                        URL url = new URL(Prepare_Url);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        int code = connection.getResponseCode();
                        InputStream is = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is,"utf-8");
                        BufferedReader br = new BufferedReader(isr);
                        StringBuffer buffer = new StringBuffer();
                        JSONObject object = null;
                        String res="";
                        String str = null;
                        while ((str = br.readLine())!=null){
                            buffer.append(str);
                        }
                        br.close();
                        isr.close();
                        is.close();
                        res = buffer.toString();
                        System.out.println(res);

                    }catch (Exception e){
                        System.out.println(e);
                    }

                }
            };

            new Thread(networkTask).start();

        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        Encoder encoder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encoder = Base64.getEncoder();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new String(encoder.encodeToString(crypted));
        }else{
            return "null";
        }
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            Decoder decoder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                decoder = Base64.getDecoder();
            }
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                output = cipher.doFinal(decoder.decode(input));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(output);
    }

}
