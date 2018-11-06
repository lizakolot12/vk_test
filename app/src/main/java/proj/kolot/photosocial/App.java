package proj.kolot.photosocial;

import android.app.Application;

import proj.kolot.photosocial.provider.VkApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yarik on 1/23/17.
 */

public class App extends Application {

    private static VkApi vkApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        vkApi = retrofit.create(VkApi.class);
    }

    public static VkApi getApi() {
        return vkApi;
    }
}