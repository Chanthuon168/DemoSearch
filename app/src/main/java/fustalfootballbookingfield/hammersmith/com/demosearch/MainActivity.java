package fustalfootballbookingfield.hammersmith.com.demosearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        listView = (ListView)findViewById(R.id.listView);
        initList();
        editText.toString().toLowerCase();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                }else{
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable ed) {

            }
        });

    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.toLowerCase().contains(textToSearch.toLowerCase())){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        items = new String[]{"Cambodia","Canada","Japan","Thailand","Korea","Chinese","Laos","Vietnam"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.custom_list,R.id.textView,listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),""+ listItems.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ShowDetail.class);
                intent.putExtra("key",listItems.get(position));
                startActivity(intent);
            }
        });
    }
}
