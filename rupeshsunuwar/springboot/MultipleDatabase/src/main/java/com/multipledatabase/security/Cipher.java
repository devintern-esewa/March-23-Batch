package com.multipledatabase.security;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@Data
public class Cipher {



    public static String generateKey(){

        String[] strArr = { "Pfjkj", "Qlfjf", "Rlffj", "Slfkk","Tlffk", "Ulflk", "Vlflf", "Wlkfn" };
        Random rand = new Random();

        int random=rand.nextInt(strArr.length);
        String key=strArr[random];
        return key;
    }
    public static String encryption(String code,String key){

        String cipherText= code + key;
        return cipherText;
    }

    public static String decryption(String encryptedText)  {


        String decipherText=encryptedText.substring(0,encryptedText.length()- 5);

        return decipherText;

    }







}



