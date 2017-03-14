package com.jonay.ecdh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    KeyPair keyPair;
    KeyPair keyPair2;

    TextView text_publicKey;
    TextView text_privateKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generar par de claves
        try {
            keyPair = ECDH.generateKeyPair();
            keyPair2 = ECDH.generateKeyPair();
//            com1 = ECDH.generateAgreedKey(keys1.getPrivate(), keys2.getPublic());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        // Mostrar par de claves
        text_publicKey = (TextView) findViewById(R.id.public_key);
        text_privateKey = (TextView) findViewById(R.id.private_key);
        text_publicKey.setText(keyPair.getPublic().toString());
        text_privateKey.setText(keyPair.getPrivate().toString());

        // Botón regenerar claves
        Button btn_regenerate = (Button) findViewById(R.id.btn_regenerate);
        btn_regenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    keyPair = ECDH.generateKeyPair();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                }

                text_publicKey.setText(keyPair.getPublic().toString());
                text_privateKey.setText(keyPair.getPrivate().toString());
            }
        });


        // ECDH ejemplo
        try {
            PublicKey publicKey1 = ECDH.stringToPublicKey("MDYwEAYHKoZIzj0CAQYFK4EEABwDIgAEruGXPxl2BAaTLAVVL+TjKMIU+VJX1BJgkPONygFh6xQ=");
            System.out.println(publicKey1);
            PrivateKey privateKey1 = ECDH.stringToPrivateKey("MF0CAQAwEAYHKoZIzj0CAQYFK4EEABwERjBEAgEBBBCc6SrqdL/jqbzywFvGdqQjoAcGBSuBBAAcoSQDIgAEruGXPxl2BAaTLAVVL+TjKMIU+VJX1BJgkPONygFh6xQ=");
            System.out.println(privateKey1);
            PublicKey publicKey2 = ECDH.stringToPublicKey("MDYwEAYHKoZIzj0CAQYFK4EEABwDIgAExDFaavY8hlP1siLq+U4JVp/GbquU14g4znKBX48vmfo=");
            System.out.println(publicKey2);
            PrivateKey privateKey2 = ECDH.stringToPrivateKey("MF0CAQAwEAYHKoZIzj0CAQYFK4EEABwERjBEAgEBBBBfiVJRH6Ay2uJNw9GYSJWpoAcGBSuBBAAcoSQDIgAExDFaavY8hlP1siLq+U4JVp/GbquU14g4znKBX48vmfo=");
            System.out.println(privateKey2);

            System.out.println(ECDH.generateAgreedKey(privateKey1, publicKey2));
            System.out.println(ECDH.generateAgreedKey(privateKey2, publicKey1));


            System.out.println(publicKey1);


            PublicKey aa = ECDH.pointToPublicKey("aee1973f19760406932c05552fe4e328", "c214f95257d4126090f38dca0161eb14");
            System.out.println(aa);
            System.out.println("ESTA -------------> " + ECDH.publicKeyToString(aa));
            System.out.println();
            System.out.println(ECDH.publicKeyToString(publicKey1));

//            System.out.println(">>>>>>  " + ECDH.publicKeyToPoint(publicKey1));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }


    }
}
