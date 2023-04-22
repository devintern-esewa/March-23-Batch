package com.formatter;

import com.api.Phone;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class PhoneNumberFormatter implements Formatter<Phone> {
    public Phone parse(String completePhoneNumber, Locale locale) throws ParseException {

        // split the string received from user
        Phone phone = new Phone();
        String[] phoneNumberArray = completePhoneNumber.split("-");

        int index = completePhoneNumber.indexOf('-');
        if(index == -1){
            phone.setCountryCode("+977");
            phone.setUserNumber(phoneNumberArray[0]);
        }
        else{
            phone.setCountryCode(phoneNumberArray[0]);
            phone.setUserNumber(phoneNumberArray[1]);
        }

        return phone;
    }

    public String print(Phone phone, Locale locale) {
        return null;
    }
}
