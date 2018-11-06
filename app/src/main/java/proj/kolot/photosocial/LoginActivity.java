package proj.kolot.photosocial;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;


import proj.kolot.photosocial.data.ResponseNews;
import proj.kolot.photosocial.newsphotos.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://oauth.vk.com/authorize?client_id=5836524&display=page&
// //redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.52
public class LoginActivity extends AppCompatActivity {

    private final String clientId = "5836524";
/*    private static final String redirectUri = "https://oauth.vk.com/blank.html";
    public static final String API_LOGIN_URL = "https://oauth.vk.com/authorize?client_id=5836524&display=page&"
            // +"redirect_uri=https://oauth.vk.com/blank.html

            + "redirect_uri=" + redirectUri + "&scope=friends,wall&response_type=token&v=5.52";*/
private static final String redirectUri = "https://www.facebook.com/connect/login_success.html";
    public static final String API_LOGIN_URL ="www.facebook.com/v2.9/dialog/oauth?client_id=362664297469300&response_type=token&redirect_uri=" + redirectUri;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
   /* public static final String API_OAUTH_CLIENTID = "replace-me";
    public static final String API_OAUTH_CLIENTSECRET = "replace-me";
    public static final String API_OAUTH_REDIRECT = "nl.jpelgrm.retrofit2oauthrefresh://oauth";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.loginVk);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog authDialog = new Dialog(LoginActivity.this);
                authDialog.setContentView(R.layout.auth_dialog);
                WebView web = (WebView) authDialog.findViewById(R.id.webv);
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl(API_LOGIN_URL);
                web.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);

                    }

                    String token;

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        if (url.contains("token=")) {
                            try {
                                String newStr = url.replaceFirst("#", "?");
                                Uri uri = Uri.parse(newStr);

                                token = uri.getQueryParameter("access_token");
                                Log.e("my test", " token = " + token);
                            } catch (Exception e) {
                            }
                            authDialog.dismiss();
                            callFirst(token);
                        }
                    }
                });
                authDialog.show();
                authDialog.setTitle("Authorize Learn2Crack");
                authDialog.setCancelable(true);
            }

        };

    }

    private void callFirst(String accessToken) {
        App.getApi().getNewsPhoto(accessToken, 1).enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                ResponseNews resp = response.body();
            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {
                Toast toast = Toast.makeText(LoginActivity.this, "SHOTO PEREKRUTILOS!!!!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String code = uri.getQueryParameter("token");
            if (code != null) {

                Log.e("my test", "token=" + code);
            } else {
                // TODO Handle a missing code in the redirect URI
            }
        }
    }

  /*  https://api.vk.com/method/newsfeed.get?filters=photo&access_token=4abf091aa06f00413d77e4cc78e4a6ee2bfc63b52df31fe902c106804d61623c9b76add30d3d612c08683





    https://oauth.vk.com/authorize?client_id=5836524&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall&response_type=token&v=5.52
*/
}
