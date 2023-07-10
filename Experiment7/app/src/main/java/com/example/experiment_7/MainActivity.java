package com.example.experiment_7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnUpdate, btnDelete, btnView;
    EditText txtName, txtContact, txtEn, txtId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.textId);
        txtName = findViewById(R.id.textName);
        txtContact = findViewById(R.id.textContact);
        txtEn = findViewById(R.id.textEn);

        btnInsert = findViewById(R.id.buttonInsert);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnView = findViewById(R.id.buttonView);

        DBHelper db = new DBHelper(this); //make db object of DBHelper class

        //-------------------insert data----------------------------------------------------
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtId.getText().toString();
                String name = txtName.getText().toString();
                String contact = txtContact.getText().toString();
                String enrollment = txtEn.getText().toString();

                if(id.isEmpty() == true && name.isEmpty() == true && contact.isEmpty() == true && enrollment.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "Please Enter data", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkDBop = db.insertUserData(id, name, contact, enrollment);

                    if(checkDBop == true){
                        Toast.makeText(MainActivity.this, "Record inserted Successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Unable to Insert Record", Toast.LENGTH_LONG).show();
                    }
                }
                }
        });

        //-------------------update data----------------------------------------------------
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String id = txtId.getText().toString();
                String name = txtName.getText().toString();
                String contact = txtContact.getText().toString();
                String enrollment = txtEn.getText().toString();

                if(id.isEmpty() == true && name.isEmpty() == true && contact.isEmpty() == true && enrollment.isEmpty() == true){
                    Toast.makeText(MainActivity.this, "Please Enter the data", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkupdate = db.updateUserData(id, name, contact, enrollment);

                    if(checkupdate == true){
                        Toast.makeText(MainActivity.this, "Record Updated Successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Unable to Update record", Toast.LENGTH_LONG).show();
                    }
                }
                }
        });

        //-------------------delete data----------------------------------------------------
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String id = txtId.getText().toString();
//                String name = txtName.getText().toString();


                if(id.isEmpty() == true){
                    Toast.makeText(MainActivity.this, "Please Enter Id", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkdelete = db.deleteUserData(id);

                    if(checkdelete == true){
                        Toast.makeText(MainActivity.this, "Record deleted Successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Unable to delete record", Toast.LENGTH_LONG).show();
                    }
                }
                }
        });

        //-------------------view data----------------------------------------------------
        btnView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Cursor records = db.viewUserData();

                if(records.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No record Found", Toast.LENGTH_LONG).show();
                    return;
                }

                StringBuffer allRecord = new StringBuffer();

                while(records.moveToNext()){
                    allRecord.append("ID:" + records.getString(0) + "\n");
                    allRecord.append("Name:" + records.getString(1) + "\n");
                    allRecord.append("Contact:" + records.getString(2) + "\n");
                    allRecord.append("Enrollment Number:" + records.getString(3) + "\n");
                    allRecord.append("\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);    //only one time alert dialog show in short we can cancel the AlertDialog
                builder.setTitle("View all Records");
                builder.setMessage(allRecord.toString());
                builder.show();
            }
        });
    }
}
