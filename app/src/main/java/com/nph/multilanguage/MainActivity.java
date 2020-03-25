package com.nph.multilanguage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.nph.multilanguage.Utils.BaseActivity;
import com.nph.multilanguage.Utils.LocaleManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_en:
                setNewLocale(this, LocaleManager.ENGLISH);
                return true;
            case R.id.item_fr:
                setNewLocale(this, LocaleManager.FRENCH);
                return true;
            case R.id.item_ja:
                setNewLocale(this, LocaleManager.JAPANESE);
                return true;
            case R.id.item_my:
                setNewLocale(this, LocaleManager.MYANMAR);
                return true;
            case R.id.item_ko:
                setNewLocale(this, LocaleManager.KOREA);
                return true;
            case R.id.item_ch:
                setNewLocale(this, LocaleManager.CHINA);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);

        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
