package rekkisoft.trongvu.com.note.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.utils.DateUtils;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<String> mUrlImage;
    private ImageOnClickListener imageOnClickListener;

    public ImageAdapter(Context context) {
        this.context = context;
        this.mUrlImage = new ArrayList<>();
    }

    public void setImageOnclickListener(ImageOnClickListener imageOnclickListener) {
        this.imageOnClickListener = imageOnclickListener;
        notifyDataSetChanged();
    }

    public void setImages(List<String> mUrlImage) {
        this.mUrlImage = mUrlImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.itemimage_activity, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int i) {
        final String url = mUrlImage.get(i);
        final int position = i;
        viewHolder.imView.setImageBitmap(DateUtils.stringToBitMap(url));
        viewHolder.imView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageOnClickListener.onClickItem(url);
            }
        });
        viewHolder.imRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageOnClickListener.onRemove(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUrlImage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imView;
        private ImageView imRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imView = itemView.findViewById(R.id.itemImage);
            imRemove = itemView.findViewById(R.id.buttonRemove);

        }
    }

    public interface ImageOnClickListener {
        void onClickItem(String url);

        void onRemove(int position);
    }



}
