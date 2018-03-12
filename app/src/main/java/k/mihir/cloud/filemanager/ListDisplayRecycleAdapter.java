package k.mihir.cloud.filemanager;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ListDisplayRecycleAdapter extends RecyclerView.Adapter<ListDisplayRecycleAdapter.ListDisplay> {
    private static final String TAG = "ListDisplayRecycleAdapt";
    private List<FilesDetails> files=new ArrayList<>();
    private Context context;
    FilesDetails filesDetails;
    ListDisplayRecycleAdapter(Context context,List<FilesDetails> files) {
        this.context=context;
        this.files = files;
    }
    @Override
    public ListDisplay onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.file_card_view,parent,false);
        return new ListDisplay(view);
    }
    @Override
    public void onBindViewHolder(ListDisplay holder, int position) {
       filesDetails = files.get(position);
       if(filesDetails.isDirectory()) {
           holder.imageView.setImageResource(R.drawable.icon_folder);
       }
       else holder.imageView.setImageResource(R.drawable.icon_file);
        holder.textView.setText(filesDetails.getName());
        if (files.get(0).isAllSelected()){
            if (holder.checkBox.getVisibility()==View.INVISIBLE){
                holder.checkBox.setVisibility(View.VISIBLE);
            }
            if (files.get(position).isSelected()){
                Log.e(TAG,position+" is Checked");
                holder.cardView.setBackgroundColor((Color.parseColor("#dedede")));
                holder.checkBox.setChecked(true);
            }
            else{
                holder.cardView.setBackgroundColor((Color.parseColor("#ffffff")));
                holder.checkBox.setChecked(false);
            }
        }
        else {
            if (holder.checkBox.getVisibility()==View.VISIBLE){
                holder.checkBox.setVisibility(View.INVISIBLE);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        filesDetails = files.get(position);
        String folder="folder",audio="audio",video="video";
        return super.getItemViewType(position);
    }
    @Override
    public int getItemCount() {
        return files.size();
    }
    class  ListDisplay extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        CheckBox checkBox;
        ListDisplay(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.file_cardview);
            imageView=itemView.findViewById(R.id.file_image_icon);
            checkBox=itemView.findViewById(R.id.file_checkbox);
            textView = itemView.findViewById(R.id.file_textView_name);
        }
    }

}
