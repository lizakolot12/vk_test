package proj.kolot.photosocial.provider;

import proj.kolot.photosocial.data.ResponseNews;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//https://android-school.ru/tag/retrofit-2/
public interface VkApi {

    @GET("method/newsfeed.get?filters=photo")
    Call<ResponseNews> getNewsPhoto(
            @Query("access_token") String accessToken,
            @Query("count") int count) ;




}
