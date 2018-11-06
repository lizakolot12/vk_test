package proj.kolot.photosocial.data;

/**
 * Created by yarik on 2/16/17.
 */

public class AccessToken
{
    private  String value;

    //время жизни в секундах
    private long expiresIn;
    //время создания в секундах
    private long timeOfCreation;

    public AccessToken(String value, long expiresIn, long timeOfCreation) {
        this.value = value;
        this.expiresIn = expiresIn;
        this.timeOfCreation = timeOfCreation;

    }

    public String getValue() {
        return value;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getTimeOfCreation() {
        return timeOfCreation;
    }


}
