package com.example.medicordapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MedicineList extends AppCompatActivity {
    ListView medicineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        medicineList=findViewById(R.id.medicineList);
        ArrayList<String> medicine=addData();
        ArrayAdapter<String> medicineAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,medicine);
        medicineList.setAdapter(medicineAdapter);
        medicineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Item clicked",medicine.get(i));
//                Intent intent=new Intent(this,Desciption.class());
//                startActivity(intent);
            }
        });
    }

    ArrayList<String> addData(){
        //add data from database to array list
        ArrayList<String> medicine=new ArrayList<>();
        medicine.add("abcd");
        medicine.add("zxc");
        return medicine;
    }
}