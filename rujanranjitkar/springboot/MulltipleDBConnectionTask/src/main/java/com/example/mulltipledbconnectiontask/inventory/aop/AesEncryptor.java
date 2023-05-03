package com.example.mulltipledbconnectiontask.inventory.aop;

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
public class AesEncryptor implements AttributeConverter<Object, String> {
    //key may be 8 bytes (8 characters), 16 bytes (12 characters)or 32 bytes (16 characters)
    //to make 32 bytes(16 characters) key
    public final String encryptionKey = "this-is-test-key";

    //to defined that we are using Aes(Advanced Encryption Standard algorithm for encryption and decryption
    public final String encryptionCipher = "AES";

    private Key key; //uses out encryptionKey
    private Cipher cipher; //uses our encryptionCipher

    //return the encryption key stored in memory
    public Key getKey() {
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        return key;
    }

    //return the encryption Cipher stored in memory
    public Cipher getCipher() throws GeneralSecurityException {
        if (cipher == null)
            cipher = Cipher.getInstance(encryptionCipher);
        return cipher;
    }

    //used to initialize cipher in the memory
    private void initCipher(int encryptMode) throws GeneralSecurityException {
        getCipher().init(encryptMode, getKey());
    }

    //to convert plain text to encrypted text
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null)
            return null;
        initCipher(Cipher.ENCRYPT_MODE);

        //conversion logic
        //serialize the attribute(converting the object into a stream of byte)
        byte[] bytes = SerializationUtils.serialize(attribute);
        /*
        Base64.getEncoder().encodeToString:=> used to encode a byte array inot a Base64-encoded String
        getCipher():=> returns the initialized cipher object
        doFinal(bytes):=> process the byte and return the encrypted bytes in the form of a byte array
         */
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
    }

    //to decrypt value
    @SneakyThrows
    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        initCipher(Cipher.DECRYPT_MODE);
        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
        return SerializationUtils.deserialize(bytes);
    }
}
