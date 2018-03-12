package k.mihir.cloud.filemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesListDisplay extends AppCompatActivity {
    private static final String TAG = "FilesListDisplay";
    String path;
    List<FilesDetails> files = new ArrayList<>();
    RecyclerView recyclerView;
    FilesDetails filesDetails;
    ListDisplayRecycleAdapter rVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files_list);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.files_recycle_view);
        String loc,internal="internal",sd="sd",otg="otg";
        rVAdapter = new ListDisplayRecycleAdapter(this,files);
        recyclerView.setAdapter(rVAdapter);
        recyclerView.setHasFixedSize(true);
        loc=intent.getStringExtra("location");
        if (loc.equalsIgnoreCase(internal)){
            path= Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        else if(loc.equalsIgnoreCase(sd)){

        }
        else if(loc.equalsIgnoreCase(otg)){

        }
        loadFiles();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        if (files.get(0).isAllSelected()){
                            if (files.get(position).isSelected()){
                                files.get(position).setSelected(false);
                            }
                            else files.get(position).setSelected(true);
                        }
                        rVAdapter.notifyDataSetChanged();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        if(!files.get(0).isAllSelected()){
                            files.get(0).setAllSelected(true);
                            files.get(position).setSelected(true);
                        }
                        else {
                            if (files.get(position).isSelected()){
                                files.get(position).setSelected(false);
                            }
                            else files.get(position).setSelected(true);
                        }
                            rVAdapter.notifyDataSetChanged();
                    }
                })
        );
    }
    private void loadFiles() {
        File file = new File(path);
        for (File f1: file.listFiles()){
            filesDetails = new FilesDetails(f1.getName(),String.valueOf(f1.length()),null,f1.isDirectory(),"folder");
            files.add(filesDetails);
            rVAdapter.notifyDataSetChanged();
        }
    }
}
