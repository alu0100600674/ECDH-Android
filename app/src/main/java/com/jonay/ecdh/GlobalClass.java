package com.jonay.ecdh;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by jonay on 16/03/17.
 */

public class GlobalClass extends Application {

    private Context context;

    private String appId;

    private PrivateKey privateKey;
    private PublicKey publicKey;
    private PublicKey serverPublicKey;
    private String agreedKey;

    RequestQueue queue;

    private TextView textViewECDH;

    public GlobalClass() {}

    public void init() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        queue = Volley.newRequestQueue(getApplicationContext());
        generateKeys();
    }

    public void setContext(Context ctx){
        context = ctx;
    }

    public void generateKeys() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        KeyPair keyPair = ECDH.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public PrivateKey getPrivateKey(){
        return privateKey;
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    public PublicKey getServerPublicKey(){
        return serverPublicKey;
    }

    public void setServerPublicKey(PublicKey key){
        serverPublicKey = key;
    }

    public String getAgreedKey(){
        return agreedKey;
    }

    public void setAgreedKey(String key){
        agreedKey = key;
    }

    public RequestQueue getRequestQueue(){
        return queue;
    }

    public TextView getTextViewECDH(){
        return textViewECDH;
    }

    public void setTextViewECDH(TextView textView){
        textViewECDH = textView;
    }

    public String getAppId(){
        return appId;
    }

    public void setAppId(String id){
        appId = id;
    }

}
