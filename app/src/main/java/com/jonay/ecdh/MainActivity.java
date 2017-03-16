package com.jonay.ecdh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.spongycastle.bcpg.ECDHPublicBCPGKey;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

public class MainActivity extends AppCompatActivity {

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    GlobalClass globalData;

    TextView text_publicKey;
    TextView text_privateKey;
    Button btn_regenerate;
    Button btn_connect;
    EditText text_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalData = (GlobalClass) getApplicationContext();
        globalData.setContext(getApplicationContext());
        try {
            globalData.init();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        // Mostrar par de claves
        text_publicKey = (TextView) findViewById(R.id.public_key);
        text_privateKey = (TextView) findViewById(R.id.private_key);
        text_publicKey.setText(globalData.getPublicKey().toString());
        text_privateKey.setText(globalData.getPrivateKey().toString());

        // Botón regenerar claves
        btn_regenerate = (Button) findViewById(R.id.btn_regenerate);
        btn_regenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    globalData.generateKeys();
                } catch (InvalidAlgorithmParameterException e1) {
                    e1.printStackTrace();
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                } catch (NoSuchProviderException e1) {
                    e1.printStackTrace();
                }

                text_publicKey.setText(globalData.getPublicKey().toString());
                text_privateKey.setText(globalData.getPrivateKey().toString());
            }
        });

        text_ip = (EditText) findViewById(R.id.ip_address);
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + text_ip.getText().toString() + ":3000/test";
                KeysExchange.publicKeyExchange(globalData.getRequestQueue(), url, ECDH.publicKeyToPoint(globalData.getPublicKey()), globalData.getApplicationContext());
            }
        });

//        String url = "http://10.209.2.83:3000/test";
//        System.out.println("SERVER KEY ---->>> " + KeysExchange.publicKeyExchange(queue, url, ECDH.publicKeyToPoint(publicKey1)));

    }
}
