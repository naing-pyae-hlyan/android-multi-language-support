package com.nph.multilanguage.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class LocaleManager {

    private static final String LANGUAGE_KEY = "language_key";  // SharePreference Key
    public static final String ENGLISH = "en";
    public static final String FRENCH = "fr";
    public static final String JAPANESE = "ja";
    public static final String MYANMAR = "my";
    public static final String KOREA = "ko";
    public static final String CHINA = "zh";

    /**
     * Define some specific constant and define all support locale in LocaleDef annotated interface
     */
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ ENGLISH, FRENCH, JAPANESE, MYANMAR, KOREA, CHINA})
    public @interface LocaleDef {
        String[] SUPPORTED_LOCALES = { ENGLISH, FRENCH, JAPANESE, MYANMAR, KOREA, CHINA};
    }

    /**
     * Saved Locale in SharedPreferences
     */
    public static String getLanguagePref(Context mContext){
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        return mPreferences.getString(LANGUAGE_KEY, ENGLISH);
    }

    /**
     * Set pref key
     */
    private static void setLanguagePref(Context mContext, String localeKey){
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mPreferences.edit().putString(LANGUAGE_KEY, localeKey).apply();
    }

    /**
     * Set current pref locale
     */
    public static Context setLocale(Context mContext){
        return updateResources(mContext, getLanguagePref(mContext));
    }

    /**
     * Set new Locale with context
     */
    public static Context setNewLocale(Context mContext, @LocaleDef String language){
        setLanguagePref(mContext, language);

        return updateResources(mContext, language);
    }

    /**
     * Update resource
     */
    private static Context updateResources(Context mContext, String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = mContext.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        config.setLocale(locale);
        mContext = mContext.createConfigurationContext(config);

        return mContext;
    }

    /**
     * Get current locale
     */
    public static Locale getLocale(Resources res){
        Configuration config = res.getConfiguration();

        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }
}
