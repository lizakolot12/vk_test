package proj.kolot.photosocial.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("photo_medium_rec")
    @Expose
    private String photoMediumRec;
    @SerializedName("online")
    @Expose
    private Integer online;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoMediumRec() {
        return photoMediumRec;
    }

    public void setPhotoMediumRec(String photoMediumRec) {
        this.photoMediumRec = photoMediumRec;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

}
