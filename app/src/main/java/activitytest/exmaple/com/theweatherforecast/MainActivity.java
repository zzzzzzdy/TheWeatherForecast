package activitytest.exmaple.com.theweatherforecast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText editText = null;
    private Button button = null;
    public String ediStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ediStr = editText.getText().toString();
               Intent intent = new Intent(MainActivity.this,Main2Activity.class);
               intent.putExtra("city1",ediStr);
               startActivity(intent);
           }
       });
    }


}
