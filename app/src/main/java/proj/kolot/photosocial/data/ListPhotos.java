package proj.kolot.photosocial.data;

import com.google.gson.annotations.JsonAdapter;

import java.util.List;

@JsonAdapter(PhotoListDeserializer.class)
public class ListPhotos  <T>{
    int count;
    List<T> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public ListPhotos(int count, List<T> photos) {
        this.count = count;
        this.list = photos;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
