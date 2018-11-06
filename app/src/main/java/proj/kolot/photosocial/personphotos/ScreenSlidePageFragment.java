package proj.kolot.photosocial.personphotos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import proj.kolot.photosocial.R;


public class ScreenSlidePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        ImageView image = (ImageView) rootView.findViewById(R.id.fullscreen_content);

        String url = getArguments().getString("url");
        Picasso.with(getContext()).setLoggingEnabled(true);
        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                // .resize(120, 60)
                .into(image);
        return rootView;
    }
}

