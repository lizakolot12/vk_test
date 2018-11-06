package proj.kolot.photosocial.data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("aid")
    @Expose
    private Integer aid;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("src_big")
    @Expose
    private String srcBig;
    @SerializedName("src_small")
    @Expose
    private String srcSmall;
    @SerializedName("src_xbig")
    @Expose
    private String srcXbig;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("access_key")
    @Expose
    private String accessKey;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("comments")
    @Expose
    private Comments comments;
    @SerializedName("can_comment")
    @Expose
    private Integer canComment;
    @SerializedName("can_repost")
    @Expose
    private Integer canRepost;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    public void setSrcBig(String srcBig) {
        this.srcBig = srcBig;
    }

    public String getSrcSmall() {
        return srcSmall;
    }

    public void setSrcSmall(String srcSmall) {
        this.srcSmall = srcSmall;
    }

    public String getSrcXbig() {
        return srcXbig;
    }

    public void setSrcXbig(String srcXbig) {
        this.srcXbig = srcXbig;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Integer getCanComment() {
        return canComment;
    }

    public void setCanComment(Integer canComment) {
        this.canComment = canComment;
    }

    public Integer getCanRepost() {
        return canRepost;
    }

    public void setCanRepost(Integer canRepost) {
        this.canRepost = canRepost;
    }

}
