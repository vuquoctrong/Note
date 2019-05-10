package rekkisoft.trongvu.com.note.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import rekkisoft.trongvu.com.note.R;
import rekkisoft.trongvu.com.note.utils.DateUtils;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<String> mUrlImage;
    private ImageOnClickListener imageOnClickListener;

    public ImageAdapter() {
        this.mUrlImage = new ArrayList<>();
    }

    public void setImageOnclickListener(ImageOnClickListener imageOnclickListener) {
        this.imageOnClickListener = imageOnclickListener;
    }

    public void setImages(List<String> mUrlImage) {
        this.mUrlImage = mUrlImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.itemimage_activity, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder viewHolder, int i) {
        final String url = mUrlImage.get(i);
        final int position = i;
        viewHolder.imView.setImageURI(Uri.parse(url));
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

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imView;
        private ImageView imRemove;

        public ImageViewHolder(@NonNull View itemView) {
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
