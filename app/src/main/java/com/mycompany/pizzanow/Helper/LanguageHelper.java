package com.mycompany.pizzanow.Helper;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHelper {
    public static void changeLocale(Resources res, String locale){

        Configuration config = new Configuration(res.getConfiguration());

        switch (locale){
            case "en":
                config.locale = new Locale("en");
                break;

            case "fr":
                config.locale = Locale.FRENCH;
                break;

                default:
                    config.locale = Locale.ENGLISH;
        }

        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
