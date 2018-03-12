package k.mihir.cloud.filemanager;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {
    TextView intSD,extSD,OTG;
    RelativeLayout internal,sdcard,otg;
    ProgressBar pb1,pb2,pb3;
    Animation animation;
    String sd1,sd2,sd3;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton video,audio,download,doc,zip,fav,img,recent,apk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        initIDs();
        ifSDCardAvailable();
        ifOTGAvailable();
        assingValues();
        setOnClickListeners();
        
    }
    private void setOnClickListeners() {
        internal.setOnClickListener(this);
        sdcard.setOnClickListener(this);
        otg.setOnClickListener(this);
    }

    private void ifOTGAvailable() {
    }

    private void assingValues() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        Long freeSpace = stat.getAvailableBytes()/(1024*1024);
        Long totalSize = stat.getTotalBytes()/(1024*1024);
        Long space = 100-(freeSpace*100/totalSize);
        Log.e("FileManager",freeSpace+" "+totalSize+" "+ space);
        String s = String.valueOf(space);
        String a = s+" %";
        intSD.setText(a);
        intSD.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tv_zoom_in));
        ObjectAnimator animation2 = ObjectAnimator.ofInt (pb1, "progress", 0, Integer.parseInt(s));
        animation2.setDuration (1000);
        animation2.setInterpolator (new DecelerateInterpolator());
        animation2.start ();
        video.setAnimation(animation);
        audio.setAnimation(animation);
        apk.setAnimation(animation);
        doc.setAnimation(animation);
        zip.setAnimation(animation);
        fav.setAnimation(animation);
        recent.setAnimation(animation);
        img.setAnimation(animation);
        download.setAnimation(animation);

    }

    private void ifSDCardAvailable() {
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();
        if(isSDSupportedDevice && isSDPresent)
        {
            extSD = findViewById(R.id.tv_sdcard_inside_progress);
            pb2=findViewById(R.id.storage_sdcard_progress_bar);
        }
        else {
            sdcard.setVisibility(View.INVISIBLE);
            otg.setVisibility(View.INVISIBLE);
        }
    }

    private void initIDs() {
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.tool_bar);
        intSD = findViewById(R.id.tv_internal_inside_progress);
        OTG = findViewById(R.id.tv_otg_inside_progress);
        internal=findViewById(R.id.relative_internal);
        sdcard=findViewById(R.id.relative_sdCard);
        otg=findViewById(R.id.relative_otg);
        pb1=findViewById(R.id.storage_internal_progress_bar);
        pb3=findViewById(R.id.storage_otg_progress_bar);
        video=findViewById(R.id.icon_video);
        apk=findViewById(R.id.icon_apks);
        audio=findViewById(R.id.icon_audio);
        download=findViewById(R.id.icon_download);
        doc=findViewById(R.id.icon_document);
        zip=findViewById(R.id.icon_compress_files);
        fav=findViewById(R.id.icon_favourite);
        recent=findViewById(R.id.icon_recent);
        img=findViewById(R.id.icon_image);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(this, R.anim.zoom_in, R.anim.tv_zoom_in);
    switch (view.getId()){
        case R.id.relative_internal:
            startActivity(new Intent(FirstScreen.this,FilesListDisplay.class)
            .putExtra("location","internal"));
            break;
        case R.id.relative_sdCard:
            break;
        case R.id.relative_otg:
            break;
    }
    }
}
