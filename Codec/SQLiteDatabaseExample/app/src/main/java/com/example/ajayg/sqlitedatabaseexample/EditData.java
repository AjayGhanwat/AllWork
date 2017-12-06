package com.example.ajayg.sqlitedatabaseexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.ajayg.sqlitedatabaseexample.R.id.titletext;

public class EditData extends AppCompatActivity {

    DatabaseHelper myData;
    EditText title, desc;
    Button updateData,deleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        myData = new DatabaseHelper(this);

        title = (EditText) findViewById(R.id.editTitle);
        desc = (EditText) findViewById(R.id.editDesc);
        updateData = (Button) findViewById(R.id.updateData);
        deleteData = (Button) findViewById(R.id.deleteData);

        Bundle bundle = getIntent().getExtras();

        final String id = bundle.getString("id");
        String usertitle = bundle.getString("title");
        String userdesc = bundle.getString("desc");

        title.setText(usertitle);
        desc.setText(userdesc);

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAdded = myData.updateData(new DataModel(id,title.getText().toString(), desc.getText().toString()));

                if(isAdded)
                    Toast.makeText(EditData.this, "Success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditData.this, "UnSuccess", Toast.LENGTH_SHORT).show();

            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted = myData.deleteData(new DataModel(id,title.getText().toString(), desc.getText().toString()));

                if(isDeleted)
                    Toast.makeText(EditData.this, "Success!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditData.this, "Unsuccess!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
