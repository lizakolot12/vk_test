package proj.kolot.photosocial.newsphotos;

import android.content.Context;

import java.util.List;

import proj.kolot.photosocial.data.PhotoNews;

/**
 * Created by yarik on 1/11/17.
 */

public interface PhotoNewsContract {
    interface View{
        void showNews(List<PhotoNews> data);
        void showError();
        void setLoadingIndicator(boolean active);
        void setPresenter(Presenter presenter);
        Context getViewContext();
        void showLogin(String url, String endKey);
    }
    interface Presenter{
        void loadNews();
        void handleUrlResultLogin(String url);
    }
}
