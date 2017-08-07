package br.com.help;

import android.text.Editable;
import android.text.TextWatcher;

public class PhoneWatcher implements TextWatcher {
    private String masked = "";

    private final String phoneMask = "%s-%s";
    private final String DDDPhoneMask = "(%s)%s-%s";
    private final String completePhoneMask = "+%s(%s)%s-%s";


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String digited = s.toString();
        if (digited.length() > masked.length() || digited.length() < masked.length() ) {
            if(!masked.equals(digited)) {
                this.masked = maskPhone(s.toString());
                s.replace(0, s.length(), masked);
            }
        }
    }

    private String maskPhone(String phone) {
        String cleanPhone = phone.replaceAll("[^0-9.]", "");
        cleanPhone = cleanPhone.replaceAll("\\.", "");
        
        String countryCode;
        String DDD;
        String phonePrefix;
        String phoneSufix;

        switch (cleanPhone.length()) {
            case 8:
                phonePrefix = cleanPhone.substring(0, 4);
                phoneSufix = cleanPhone.substring(4, 8);

                return  String.format(phoneMask, phonePrefix, phoneSufix);
            case 9:
                phonePrefix = cleanPhone.substring(0, 5);
                phoneSufix = cleanPhone.substring(5, 9);

                return  String.format(phoneMask, phonePrefix, phoneSufix);
            case 10:
                DDD = cleanPhone.substring(0, 2);
                phonePrefix = cleanPhone.substring(2, 6);
                phoneSufix = cleanPhone.substring(6, 10);

                return  String.format(DDDPhoneMask, DDD, phonePrefix, phoneSufix);
            case 11:
                DDD = cleanPhone.substring(0, 2);
                phonePrefix = cleanPhone.substring(2, 7);
                phoneSufix = cleanPhone.substring(7, 11);

                return  String.format(DDDPhoneMask, DDD, phonePrefix, phoneSufix);
            case 12:
                countryCode = cleanPhone.substring(0, 2);
                DDD = cleanPhone.substring(2, 4);
                phonePrefix = cleanPhone.substring(4, 8);
                phoneSufix = cleanPhone.substring(8, 12);

                return  String.format(completePhoneMask, countryCode, DDD, phonePrefix, phoneSufix);
            case 13:
                countryCode = cleanPhone.substring(0, 2);
                DDD = cleanPhone.substring(2, 4);
                phonePrefix = cleanPhone.substring(4, 9);
                phoneSufix = cleanPhone.substring(9, 13);

                return  String.format(completePhoneMask, countryCode, DDD, phonePrefix, phoneSufix);
            default:
                return  cleanPhone;
        }
    }
}
