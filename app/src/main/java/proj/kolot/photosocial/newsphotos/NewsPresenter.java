package proj.kolot.photosocial.newsphotos;


import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import proj.kolot.photosocial.App;
import proj.kolot.photosocial.data.AccessToken;
import proj.kolot.photosocial.data.News;
import proj.kolot.photosocial.data.PhotoNews;
import proj.kolot.photosocial.data.ResponseNews;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class NewsPresenter implements PhotoNewsContract.Presenter {
    private final String PREFERENCES_NAME_GENERAL = "photo_news";
    private final String PREFERENCES_NAME_TOKEN = "photo_news";
/*    private static final String redirectUri = "https://oauth.vk.com/blank.html";
    private static final String API_LOGIN_URL = "https://oauth.vk.com/authorize?client_id=5836524&display=page&"

            // +"redirect_uri=https://oauth.vk.com/blank.html

            + "redirect_uri=" + redirectUri + "&scope=friends,wall&response_type=token&v=5.52";*/
private static final String redirectUri = "https://www.facebook.com/connect/login_success.html";
    public static final String API_LOGIN_URL ="http://www.facebook.com/v2.9/dialog/oauth?client_id=362664297469300&response_type=token&redirect_uri=" + redirectUri;


    private static final String END_KEY = "token=";
    private final PhotoNewsContract.View mNewsView;

    private boolean isAuthorized() {
        AccessToken accessToken = getAccessToken();
        if (accessToken != null) {
            if (isValidAccessToken(accessToken)) {
                return true;
            } else {
                clearAccessToken();
                return false;
            }
        }
        return false;
    }

    private void clearAccessToken() {
        SharedPreferences mPrefs = mNewsView.getViewContext().getSharedPreferences(PREFERENCES_NAME_GENERAL, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.remove(PREFERENCES_NAME_TOKEN);
    }

    private boolean isValidAccessToken(AccessToken token) {
        long now = new Date().getTime() / 1000; //now in seconds
        long a = new Date().getTime();
        long b = now - token.getTimeOfCreation();
        Log.e("my test", "new Date().getTime() = " + new Date().getTime() + " now = " + now + "  time of creation = " + token.getTimeOfCreation() + "  expires = " + token.getExpiresIn());
        Log.e("my test", " now - time of creation " + (now - token.getTimeOfCreation()));
        return (now - token.getTimeOfCreation()) < token.getExpiresIn();
    }

    private void saveAccessToken(AccessToken token) {
        SharedPreferences mPrefs = mNewsView.getViewContext().getSharedPreferences(PREFERENCES_NAME_GENERAL, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(token);
        prefsEditor.putString(PREFERENCES_NAME_TOKEN, json);
        prefsEditor.commit();
    }

    private AccessToken getAccessToken() {
        SharedPreferences mPrefs = mNewsView.getViewContext().getSharedPreferences(PREFERENCES_NAME_GENERAL, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(PREFERENCES_NAME_TOKEN, "");
        AccessToken accessToken = gson.fromJson(json, AccessToken.class);
        return accessToken;
    }

    private void runAutorization(AutorizationListener listener) {
    }

    private void loadPhotoNews() {
        final List<PhotoNews> result = new ArrayList<>();
        App.getApi().getNewsPhoto(getAccessToken().getValue(), 4).enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                ResponseNews resp = response.body();
                News news = resp.getResponse();
                for (int i = 0; i < news.getProfiles().size(); i++) {
                    PhotoNews ph = new PhotoNews();
                    ph.setPhotos(news.getItems().get(i).getPhotos());
                    ph.setProfile(news.getProfiles().get(i));
                    result.add(ph);

                }
                mNewsView.setLoadingIndicator(false);
                mNewsView.showNews(result);
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                mNewsView.setLoadingIndicator(false);
                mNewsView.showError();
            }
        });
    }

    public void handleUrlResultLogin(String url) {
        boolean success = false;
        try {
            String newStr = url.replaceFirst("#", "?");
            Uri uri = Uri.parse(newStr);
            String token = uri.getQueryParameter("access_token");
            String expiresInStr = uri.getQueryParameter("expires_in");
            long expiresIn = Long.parseLong(expiresInStr);
            AccessToken accessToken = new AccessToken(token, expiresIn, new Date().getTime() / 1000);
            saveAccessToken(accessToken);
            success = true;
        } catch (Exception e) {
        }
        if (success) {
            loadNews();
        }
    }

    @Override
    public void loadNews() {
        mNewsView.setLoadingIndicator(true);

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNewsView.setLoadingIndicator(false);
                mNewsView.showNews(Arrays.asList(new PhotoNews("one"), new PhotoNews("two"), new PhotoNews("three")));

            }
        }, 3000);*/

        if (isAuthorized()) {
            loadPhotoNews();
        } else {
            mNewsView.showLogin(API_LOGIN_URL, END_KEY);
        }


    }

    public NewsPresenter(PhotoNewsContract.View tasksView) {

        mNewsView = tasksView;

        mNewsView.setPresenter(this);
    }
}

interface AutorizationListener {
    void onSuccess();

    void onError();
}