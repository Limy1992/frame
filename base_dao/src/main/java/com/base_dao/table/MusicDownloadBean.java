package com.base_dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 音乐，我的下载(暂无使用)
 * Created by on 2017/12/11.
 *
 * @author lmy
 */
@Entity
public class MusicDownloadBean {
    public static final String MUSIC_DOWN_TYPE = "0";
    @Id(autoincrement = true)
    private Long id;
    /**
     * 0我的下载数据， 1网络的数据
     */
    private String musicType;
    /**
     * 下载的音乐名
     */
    private String musicName;

    /**
     * 音乐的标签
     */
    private String musicTag;

    /**
     * 下载的路径
     */
    private String musicDownPath;

    /**
     * 音乐的网络url
     */
    private String musicUrl;
    /**
     * 下载音乐的id
     */
    @Unique
    private Integer musicId;

    /**评论的数量*/
    private String commentCount;
    /**
     * 下载的音乐的评论链接
     */
    private String musicCommentUrl;

    @Generated(hash = 616789841)
    public MusicDownloadBean(Long id, String musicType, String musicName,
            String musicTag, String musicDownPath, String musicUrl, Integer musicId,
            String commentCount, String musicCommentUrl) {
        this.id = id;
        this.musicType = musicType;
        this.musicName = musicName;
        this.musicTag = musicTag;
        this.musicDownPath = musicDownPath;
        this.musicUrl = musicUrl;
        this.musicId = musicId;
        this.commentCount = commentCount;
        this.musicCommentUrl = musicCommentUrl;
    }

    @Generated(hash = 572671083)
    public MusicDownloadBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusicName() {
        return this.musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public Integer getMusicId() {
        return this.musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicCommentUrl() {
        return this.musicCommentUrl;
    }

    public void setMusicCommentUrl(String musicCommentUrl) {
        this.musicCommentUrl = musicCommentUrl;
    }

    public String getMusicType() {
        return this.musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getMusicTag() {
        return this.musicTag;
    }

    public void setMusicTag(String musicTag) {
        this.musicTag = musicTag;
    }

    public String getMusicDownPath() {
        return this.musicDownPath;
    }

    public void setMusicDownPath(String musicDownPath) {
        this.musicDownPath = musicDownPath;
    }

    public String getMusicUrl() {
        return this.musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

}
