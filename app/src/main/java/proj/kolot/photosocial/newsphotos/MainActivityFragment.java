package proj.kolot.photosocial.newsphotos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import proj.kolot.photosocial.R;
import proj.kolot.photosocial.data.Photo;
import proj.kolot.photosocial.data.PhotoNews;
import proj.kolot.photosocial.personphotos.GalleryActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements PhotoNewsContract.View {

    private ProgressBar loadIndicator;
    private RecyclerView list;
    private PhotoNewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private PhotoNewsContract.Presenter mPresenter;

    @Override
    public void setPresenter(PhotoNewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNews(List<PhotoNews> data) {
        mAdapter.replaceData(data);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void showError(){
        Toast toast = Toast.makeText(getViewContext(), "SHOTO PEREKRUTILOS!!!!", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    public void showLogin(String url, final String endKey) {
        final Dialog authDialog = new Dialog(getContext());
        authDialog.setContentView(R.layout.auth_dialog);
        WebView web = (WebView) authDialog.findViewById(R.id.webv);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains(endKey)) {
                    authDialog.dismiss();
                    mPresenter.handleUrlResultLogin(url);

                }

            }
        });
        authDialog.show();
        authDialog.setTitle(getString(R.string.dialog_login_title));
        authDialog.setCancelable(true);
    }


    @Override
    public void setLoadingIndicator(boolean active) {
        loadIndicator.setVisibility(active ? View.VISIBLE : View.GONE);

    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
       loadIndicator = (ProgressBar) root.findViewById(R.id.load_indicator);
        list = (RecyclerView) root.findViewById(R.id.data);
        Log.e("my test", " fixed size " + list.hasFixedSize());

        mLayoutManager =  new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        Log.e("my test", "mLayoutManager.isAutoMeasureEnabled()= " + mLayoutManager.isAutoMeasureEnabled() +
                "     mLayoutManager.isMeasurementCacheEnabled() =" + mLayoutManager.isMeasurementCacheEnabled());

        list.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new PhotoNewsAdapter(new ArrayList<PhotoNews>());
        list.setAdapter(mAdapter);

        mPresenter.loadNews();
        return root;
    }

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }


    public class PhotoNewsAdapter extends RecyclerView.Adapter<PhotoNewsAdapter.ViewHolder> {
        private List<PhotoNews> mDataset;

        private void setList(List<PhotoNews> list) {
            this.mDataset = list;
        }


        public void replaceData(List<PhotoNews> data) {
            setList(data);


        }


        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mProfile;
            public TextView mDescription;
            GridView mPhotos;
            int position;

            ViewHolder(View itemView) {

                super(itemView);
                mProfile = (TextView) itemView.findViewById(R.id.profile);
                mDescription = (TextView) itemView.findViewById(R.id.description_photo);
                mPhotos = (GridView) itemView.findViewById(R.id.photos);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public PhotoNewsAdapter(List<PhotoNews> data) {
            mDataset = data;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public PhotoNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.line_rv_photo_news_adapter, parent, false);
            // set the view's size, margins, paddings and layout parameters
            RecyclerView.LayoutParams params = ( RecyclerView.LayoutParams) view.getLayoutParams();

            ViewHolder vh = new ViewHolder(view);
//            params.height = vh.mPhotos.getMeasuredHeight();
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            PhotoNews item = mDataset.get(position);
            holder.mProfile.setText(item.getProfile().getFirstName() + " " + item.getProfile().getLastName());
            holder.mDescription.setText("All photos " + item.getPhotos().getCount());
            holder.mPhotos.setAdapter(new ImageAdapter(getContext(), item.getPhotos().getList()));
            holder.position=position;
            holder.mPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int index = holder.position;
                    List<String> urls = new ArrayList<String>();
                    for(Photo photo:mDataset.get(index).getPhotos().getList()){
                        urls.add(photo.getSrcXbig());
                    }
                    Intent intent = new Intent(getActivity(), GalleryActivity.class);
                    String[]data = new String[urls.size()];
                    urls.toArray(data);
                    intent.putExtra("urls", data);
                    intent.putExtra("index", position);
                    startActivity(intent);

            }});
        }



        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }


}
