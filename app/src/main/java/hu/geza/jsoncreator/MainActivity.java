package hu.geza.jsoncreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {
    private JSONObject mJsonObject;
    private EditText mKeyInput;
    private EditText mValueInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKeyInput = (EditText) findViewById(R.id.key_input);
        mValueInput = (EditText) findViewById(R.id.value_input);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mJsonObject = new JSONObject();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnAddButtonClicked(View view) {
        if(mJsonObject== null)mJsonObject = new JSONObject();
        if(mKeyInput.getText()!=null && mValueInput.getText()!=null){
            if(!mKeyInput.getText().toString().isEmpty() &&
                    !mValueInput.getText().toString().isEmpty()){
                try {
                    mJsonObject.put(mKeyInput.getText().toString(),mValueInput.getText().toString
                            ());
                } catch (JSONException e) {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

    public void OnDoneButtonClicked(View view) {
        Intent i = new Intent(this,DisplayActivity.class);
        try {
            i.putExtra(DisplayActivity.JSON_PARAM,mJsonObject.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(i);
    }
}
