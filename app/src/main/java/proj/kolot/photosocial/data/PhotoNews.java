package proj.kolot.photosocial.data;


public class PhotoNews {
    private Profile profile;
    private ListPhotos<Photo> photos;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ListPhotos<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ListPhotos<Photo> photos) {
        this.photos = photos;
    }
}
