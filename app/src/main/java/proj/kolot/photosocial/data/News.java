package proj.kolot.photosocial.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles = null;
    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;
    @SerializedName("new_offset")
    @Expose
    private Integer newOffset;
    @SerializedName("new_from")
    @Expose
    private String newFrom;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public Integer getNewOffset() {
        return newOffset;
    }

    public void setNewOffset(Integer newOffset) {
        this.newOffset = newOffset;
    }

    public String getNewFrom() {
        return newFrom;
    }

    public void setNewFrom(String newFrom) {
        this.newFrom = newFrom;
    }

}
