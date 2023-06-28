package org.example.util;

import org.springframework.stereotype.Component;

@Component
public class CheckValidationUtil {

    public boolean checkPhoneNumber(String phone) {
        if(phone.length()!=13 || !phone.startsWith("+998")){
            return false;
        }
        return true;
    }

    public Integer isId(String data) {
        for (int i=0; i<data.length(); i++){
            char c=data.charAt(i);
            if(!Character.isDigit(c)){
                return null;
            }
        }
       return Integer.valueOf(data);
    }
}
