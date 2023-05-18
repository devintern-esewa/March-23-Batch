package com.example.multipledatabaseconnectiontask.inventorydb.encdec;

import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AttributeEncryptor implements AttributeConverter<String,String> {
    private static final String AES="AES";
    private static final String SECRET="secret-key-55555";
    private final Key key;
    private final Cipher cipher;
    public AttributeEncryptor() throws NoSuchPaddingException, NoSuchAlgorithmException {
       key = new SecretKeySpec(SECRET.getBytes(),AES);
       cipher =Cipher.getInstance(AES);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try{
            cipher.init(Cipher.ENCRYPT_MODE,key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        }
        catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try{
            cipher.init(Cipher.DECRYPT_MODE,key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        }
        catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
}
