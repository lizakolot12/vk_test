package proj.kolot.photosocial.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PhotoListDeserializer<T> implements JsonDeserializer<ListPhotos<T>> {
    @Override
    public ListPhotos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonArray data = json.getAsJsonArray();
        int size = data.get(0).getAsInt();

        ParameterizedType type = (ParameterizedType)typeOfT;
        Class parameter = (Class)type.getActualTypeArguments()[0];
        List<T> photos = new ArrayList<T>();
        for (int i = 1; i < data.size(); i++) {
            photos.add((T) context.deserialize(data.get(i).getAsJsonObject(),parameter));
        }
        ListPhotos<T> list = new ListPhotos<T>(size, photos);

        return list;
    }


}
