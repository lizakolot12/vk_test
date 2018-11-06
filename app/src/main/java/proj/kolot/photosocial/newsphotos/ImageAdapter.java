package proj.kolot.photosocial.newsphotos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import proj.kolot.photosocial.R;
import proj.kolot.photosocial.data.Photo;



public class ImageAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Photo> mPhotos;

    public ImageAdapter(Context c, List<Photo> photos) {
        mContext = c;
        mPhotos = photos;

    }

    public int getCount() {
        return mPhotos.size();
    }

    public Object getItem(int position) {
        return mPhotos.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                mContext.LAYOUT_INFLATER_SERVICE);
        ImageView imageView;

        if (convertView == null) {
            imageView = (ImageView) inflater.inflate(R.layout.grid_view_item, null);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext).setLoggingEnabled(true);
        Picasso.with(mContext)
                .load(mPhotos.get(position).getSrc())
                .placeholder(R.drawable.image_placeholder)
               // .resize(120, 60)
                .into(imageView);
        Log.e("my test", "url load " +mPhotos.get(position).getSrc());
        return imageView;
    }

}