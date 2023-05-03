package com.example.multipledatabaseconnection.aop;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

@Configuration
//I want to store any type of data so Object
//I will store all encrypted data in the form of String in db
public class AesEncrypter implements AttributeConverter<Object, String> {
    //provide AESKey required for the encryption
    //16 byte ie. 32 bytes key will be generated
    private final String encryptionKey = "this-is-test-key";
    private final String encryptionCipher = "AES";
    private Key key; //uses our encryptionKey
    private Cipher cipher; //uses our encryptionCipher

    //returns the encryption key stored in memory
    private Key getKey() {
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        return key;
    }

    //returns the encryption ciper stored in memory
    private Cipher getCipher() throws GeneralSecurityException {
        if (cipher == null)
            cipher = Cipher.getInstance(encryptionCipher);
        return cipher;
    }

    //used to initalize cipher in the memory
    private void initCipher(int encryptionMode) throws GeneralSecurityException {
        getCipher().init(encryptionMode, getKey());

    }

    @SneakyThrows
    @Override
    //plain text object to encrypted text

    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null)
            return null;
        initCipher(Cipher.ENCRYPT_MODE);

        //conversion logic
        //serialize the attribute(converting the object into a stream of bytes)
        byte[] bytes = SerializationUtils.serialize(attribute);

        /*
        Base64.getEncoder().encodeToString:=> used to encode a byte array into a Base64-encoded string
        getCipher():=>returns the initialized cipher object
        doFinal(bytes):=> process the bytes and return the encrypted bytes in the form of a  byte array
         */
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
    }

    @SneakyThrows
    @Override
    //decrypt
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        initCipher(Cipher.DECRYPT_MODE);

        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
        return SerializationUtils.deserialize(bytes);
    }
}
