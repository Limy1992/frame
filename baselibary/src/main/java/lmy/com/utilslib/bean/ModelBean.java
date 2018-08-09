package lmy.com.utilslib.bean;

/**
 * Created by on 2018/8/9.
 *
 * @author lmy
 */
public class ModelBean {

    /**
     * coverUrl : http://img.cdn.lanwuzhe.com/competition/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_2018080322441115333125008781dab.png
     * createTime : 1533312504000
     * description :
     * id : 39
     * isDelete : 0
     * isShow : true
     * matchId : 0
     * shareNum : 2
     * signUpStatus : 4
     * sort : 1
     * tag : 1
     * title : 2018中国.杭州第九届“华之舞杯”国际标准舞全国公开赛 暨第五届全国青少年舞蹈锦标赛
     */

    private String coverUrl;
    private long createTime;
    private String description;
    private int id;
    private int isDelete;
    private boolean isShow;
    private int matchId;
    private int shareNum;
    private int signUpStatus;
    private int sort;
    private String tag;
    private String title;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public int getSignUpStatus() {
        return signUpStatus;
    }

    public void setSignUpStatus(int signUpStatus) {
        this.signUpStatus = signUpStatus;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
