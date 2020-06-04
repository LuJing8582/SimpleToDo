package sg.edu.rp.c346.id19018582.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnAdd,btnClear,btnDel;
    ListView lv;
    ArrayList<String>todoList;
    ArrayAdapter<String>adapter;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editTextToDo);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonClear);
        btnDel=findViewById(R.id.buttonDelete);
        lv=findViewById(R.id.listView);
        spn=findViewById(R.id.spinner);


        todoList=new ArrayList<>();

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,todoList);

        lv.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thingsTodo=et.getText().toString();
                todoList.add(thingsTodo);
                adapter.notifyDataSetChanged();
                et.setText("");
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todoList.size()==0){
                    Toast.makeText(getApplicationContext(), "You don,t have any task to remove",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    int pos=Integer.parseInt(et.getText().toString());
                    if(todoList.size() <=pos ){
                        Toast.makeText(MainActivity.this, "Wrong index number" ,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        todoList.remove(pos);
                        adapter.notifyDataSetChanged();
                        et.setText("");
                    }
                }
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        et.setHint(getString(R.string.addTask));
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;
                    case 1:
                        et.setHint(getString(R.string.delTask));
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

