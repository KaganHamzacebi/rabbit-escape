package rabbitescape.ui.android.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.artificialworlds.rabbitescape.R;

public class MySettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new MySettingsFragment())
                .commit();
    }
}