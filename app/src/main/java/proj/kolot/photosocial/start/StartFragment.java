package proj.kolot.photosocial.start;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proj.kolot.photosocial.R;

public class StartFragment extends Fragment implements StartAppContract.View{

    private StartAppContract.Presenter mPresenter;

    @Override
    public void setPresenter(StartAppContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_start, container, false);
        if (mPresenter.isLogin()){
            showPhotoNews();
        }else {
            hidePhotoNews();
            mPresenter.login();

        }
        return root;

    }

    @Override
    public void showPhotoNews() {

    }

    @Override
    public void hidePhotoNews() {

    }

    @Override
    public void showUnautorization() {

    }

    @Override
    public void showLogin() {

    }

    public StartFragment() {
        // Required empty public constructor
    }



}
