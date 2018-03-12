package k.mihir.cloud.filemanager;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StorageRecycleAdapter extends RecyclerView.Adapter<StorageRecycleAdapter.StorageViewHolder> {
    private String[] name,size;

    StorageRecycleAdapter(String[] name, String[] size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public StorageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.storage_details_view_progress,parent,false);
        return new StorageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StorageViewHolder holder, int position) {
        int s = Integer.parseInt(size[position]);
        ObjectAnimator animation = ObjectAnimator.ofInt (holder.progressBar, "progress", 0, s);
        animation.setDuration (1000);
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();
            holder.textView.setText(name[position]);
            String abc = size[position]+" %";
            holder.percentageTV.setText(abc);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class StorageViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView,percentageTV;

        StorageViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.storage_progress_bar);
            textView = itemView.findViewById(R.id.storage_text_view);
            percentageTV = itemView.findViewById(R.id.tv_inside_progress);
        }
    }
}
