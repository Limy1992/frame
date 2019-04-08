package com.base_dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * 视频 下载和最近播放
 * Created by on 2018/1/5.
 *
 * @author lmy
 */

@Entity
public class VideoDownLoadBean {

    @Id(autoincrement = true)
    private Long mainId;
    /**
     * 视频我的下载
     */
    public static final String VIDEO_DOWN = "videos_down";
    /**
     * 视频最近播放
     */
    public static final String VIDEO_LATE = "videos_late";
    private String videoTitle;

    /**标签*/
    private String videoTag;
    /**视频播放uri*/
    public String videoUrl;
    /**视频封面url*/
    public String videoCoverUrl;
    /**
     * 视频类型， 是下载还是最近播放
     */
    private String videoType;
    /**
     * 保存的时间
     */
    private Long videoSaveTime;
    /**视频id*/
    public Integer videoId;
    /**是否下载完毕*/
    private Boolean isDownOver;
    /**下载的本地路径*/
    private String videoDownPath;

    @Generated(hash = 1291390585)
    public VideoDownLoadBean(Long mainId, String videoTitle, String videoTag,
            String videoUrl, String videoCoverUrl, String videoType,
            Long videoSaveTime, Integer videoId, Boolean isDownOver,
            String videoDownPath) {
        this.mainId = mainId;
        this.videoTitle = videoTitle;
        this.videoTag = videoTag;
        this.videoUrl = videoUrl;
        this.videoCoverUrl = videoCoverUrl;
        this.videoType = videoType;
        this.videoSaveTime = videoSaveTime;
        this.videoId = videoId;
        this.isDownOver = isDownOver;
        this.videoDownPath = videoDownPath;
    }

    @Generated(hash = 2044523335)
    public VideoDownLoadBean() {
    }

    public Long getMainId() {
        return this.mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoType() {
        return this.videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public Long getVideoSaveTime() {
        return this.videoSaveTime;
    }

    public void setVideoSaveTime(Long videoSaveTime) {
        this.videoSaveTime = videoSaveTime;
    }

    public Integer getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Boolean getIsDownOver() {
        return this.isDownOver;
    }

    public void setIsDownOver(Boolean isDownOver) {
        this.isDownOver = isDownOver;
    }

    public String getVideoDownPath() {
        return this.videoDownPath;
    }

    public void setVideoDownPath(String videoDownPath) {
        this.videoDownPath = videoDownPath;
    }

    public String getVideoCoverUrl() {
        return this.videoCoverUrl;
    }

    public void setVideoCoverUrl(String videoCoverUrl) {
        this.videoCoverUrl = videoCoverUrl;
    }

    public String getVideoTag() {
        return this.videoTag;
    }

    public void setVideoTag(String videoTag) {
        this.videoTag = videoTag;
    }

}
