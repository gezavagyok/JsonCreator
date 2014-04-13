package hu.geza.jsoncreator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class DisplayActivity extends ActionBarActivity {
    private JSONObject mJsonObject;
    public static final String JSON_PARAM = "json param";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String json = getIntent().getStringExtra(JSON_PARAM);
        try {
            mJsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        ((TextView)findViewById(R.id.json_tv)).setText(json);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.copy) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = null;
            try {
                clip = ClipData.newPlainText("JSON", mJsonObject.toString(4));
            } catch (JSONException e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this,"Vágólapra másoltam!",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
