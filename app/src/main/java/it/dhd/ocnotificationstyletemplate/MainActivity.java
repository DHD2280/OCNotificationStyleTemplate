package it.dhd.ocnotificationstyletemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String OXYGEN_CUSTOMIZER = "it.dhd.oxygencustomizer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getPackageManager().getLaunchIntentForPackage(OXYGEN_CUSTOMIZER);
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.oc_not_installed, Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
