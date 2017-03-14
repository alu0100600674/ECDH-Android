package com.jonay.ecdh;

import android.util.Base64;

import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.math.ec.ECCurve;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;

/**
 * Created by jonay on 24/02/17.
 */

public class ECDH {

    public static KeyPair generateKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPair keyPair = null;

        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp128r1");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDH", "SC");
        keyPairGenerator.initialize(ecGenParameterSpec);
        keyPair = keyPairGenerator.generateKeyPair();

        return keyPair;
    }

    public static String generateAgreedKey(PrivateKey privateKey, PublicKey publicKey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", "SC");
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);

        byte[] sharedKeyBytes = keyAgreement.generateSecret();
        return Base64.encodeToString(sharedKeyBytes, Base64.DEFAULT);
    }

//    public static PublicKey stringToPublicKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
//        byte[] keyBytes = Base64.decode(key.getBytes("utf-8"), Base64.DEFAULT);
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey publicKey = keyFactory.generatePublic(spec);
//        return publicKey;
//    }
//
//    public static String publicKeyToString(PublicKey key){
//        byte[] keyBytes = Base64.encode(key.getEncoded(), Base64.DEFAULT);
//        String publicKey = new String(keyBytes);
//        return publicKey;
//    }

    public static PublicKey stringToPublicKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        byte[] keyBytes = Base64.decode(key.getBytes("utf-8"), Base64.DEFAULT);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDH", "SC");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }

    public static String publicKeyToString(PublicKey key){
        byte[] keyBytes = Base64.encode(key.getEncoded(), Base64.DEFAULT);
        String publicKey = new String(keyBytes);
        return publicKey;
    }

    public static PrivateKey stringToPrivateKey(String key) throws UnsupportedEncodingException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.decode(key.getBytes("utf-8"), Base64.DEFAULT);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDH", "SC");
        PrivateKey privateKey = keyFactory.generatePrivate(spec);
        return privateKey;
    }

    public static String privateKeyToString(PrivateKey key){
        byte[] keyBytes = Base64.encode(key.getEncoded(), Base64.DEFAULT);
        String privateKey = new String(keyBytes);
        return privateKey;
    }

    public static PublicKey pointToPublicKey(String x, String y) throws InvalidKeySpecException, NoSuchProviderException, NoSuchAlgorithmException {

//        ECParameterSpec ecParameters = new ECParameterSpec();
        ECNamedCurveParameterSpec param = null;

        ECPoint ecPoint = new ECPoint(new BigInteger(x, 16), new BigInteger(y, 16));
//        ECPublicKeySpec spec = new ECPublicKeySpec(ecPoint, null);
//        KeyFactory keyFactory = KeyFactory.getInstance("ECDH", "SC");
//        PublicKey publicKey = keyFactory.generatePublic(spec);
//        return publicKey;
        return null;
    }

}
