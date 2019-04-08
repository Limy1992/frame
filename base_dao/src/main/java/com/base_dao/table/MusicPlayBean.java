package com.base_dao.table;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 音乐点击列表播放的所有音乐
 * Created by LMY on 2018/1/1.
 */
@Entity
public class MusicPlayBean implements Parcelable {

    /**
     * 未下载完成
     */
    public static final int DOWN_NO_OVER = 0;

    /**下载完成*/
    public static final int DOWN_OVER = 1;

    /**改变播放列表*/
    public static final int OTHER_MUSIC = 1000;

    /**
     * 最新
     */
    public static final int MUSIC_NEWEST = 2;
    /**
     * 播放列表
     */
    public static final int MUSIC_PLAY_ITEM = 4;
   /**
    * 最近播放
    */
    public static final int MUSIC_LATE = 5;
    /**
     * 下载的本地音乐
     */
    public static final int MUSIC_DOWN = 6;
    /**
     * 音乐的缓存
     */
    public static final int MUSIC_CACHE = 7;

    @Id(autoincrement = true)
    private Long mainId;
    /**
     * 音乐id
     */
    private int id;
    /**
     * 音乐类型 列如 恰恰 探戈
     */
    private String audioTitle;
    /**
     * 音乐名称
     */
    private String audioName;
    /**
     * 音乐的播放地址
     **/
    private String audioUrl;
    /**
     * 音乐的下载地址下载完毕的本地地址
     */
    private String downloadUrl;

    /**
     * 播放类型
     */
    private Integer audioPlayType;
    /**
     * 保存的时间
     */
    private Long saveLateTime;

    /**
     * Bate What is why? I dot
     */
    private Integer audioNoteCount =0;
    private Integer audioSectionBeatCount =0;
    private Integer audioMinBeatCount = 0;
    private Integer audioBlankBeatCount = 0;
    private Boolean audioBeatFlag = false;
    private Integer audioTypeInt = 0;
    private Integer audioUrlVer = 0;
    private Integer audioBeatParamVer = 0;





    /**
     * 下载的状态--------------------
     */

    /**下载的文件总大小*/
    private Long contentLength;
    /**已经下载的进度*/
    private Integer musicDownProgress;
    /**是否下载完成*/
    private Integer musicDownOver;

    @Generated(hash = 1213521830)
    public MusicPlayBean(Long mainId, int id, String audioTitle, String audioName,
            String audioUrl, String downloadUrl, Integer audioPlayType, Long saveLateTime,
            Integer audioNoteCount, Integer audioSectionBeatCount, Integer audioMinBeatCount,
            Integer audioBlankBeatCount, Boolean audioBeatFlag, Integer audioTypeInt,
            Integer audioUrlVer, Integer audioBeatParamVer, Long contentLength,
            Integer musicDownProgress, Integer musicDownOver) {
        this.mainId = mainId;
        this.id = id;
        this.audioTitle = audioTitle;
        this.audioName = audioName;
        this.audioUrl = audioUrl;
        this.downloadUrl = downloadUrl;
        this.audioPlayType = audioPlayType;
        this.saveLateTime = saveLateTime;
        this.audioNoteCount = audioNoteCount;
        this.audioSectionBeatCount = audioSectionBeatCount;
        this.audioMinBeatCount = audioMinBeatCount;
        this.audioBlankBeatCount = audioBlankBeatCount;
        this.audioBeatFlag = audioBeatFlag;
        this.audioTypeInt = audioTypeInt;
        this.audioUrlVer = audioUrlVer;
        this.audioBeatParamVer = audioBeatParamVer;
        this.contentLength = contentLength;
        this.musicDownProgress = musicDownProgress;
        this.musicDownOver = musicDownOver;
    }
    @Generated(hash = 1105425544)
    public MusicPlayBean() {
    }
    public Long getMainId() {
        return this.mainId;
    }
    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAudioTitle() {
        return this.audioTitle;
    }
    public void setAudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }
    public String getAudioName() {
        return this.audioName;
    }
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
    public String getAudioUrl() {
        return this.audioUrl;
    }
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
    public String getDownloadUrl() {
        return this.downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    public Integer getAudioPlayType() {
        return this.audioPlayType;
    }
    public void setAudioPlayType(Integer audioPlayType) {
        this.audioPlayType = audioPlayType;
    }
    public Long getSaveLateTime() {
        return this.saveLateTime;
    }
    public void setSaveLateTime(Long saveLateTime) {
        this.saveLateTime = saveLateTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mainId);
        dest.writeInt(this.id);
        dest.writeString(this.audioTitle);
        dest.writeString(this.audioName);
        dest.writeString(this.audioUrl);
        dest.writeString(this.downloadUrl);
        dest.writeValue(this.audioPlayType);
        dest.writeValue(this.saveLateTime);
    }

    protected MusicPlayBean(Parcel in) {
        this.mainId = (Long) in.readValue(Long.class.getClassLoader());
        this.id = in.readInt();
        this.audioTitle = in.readString();
        this.audioName = in.readString();
        this.audioUrl = in.readString();
        this.downloadUrl = in.readString();
        this.audioPlayType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.saveLateTime = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<MusicPlayBean> CREATOR = new Creator<MusicPlayBean>() {
        @Override
        public MusicPlayBean createFromParcel(Parcel source) {
            return new MusicPlayBean(source);
        }

        @Override
        public MusicPlayBean[] newArray(int size) {
            return new MusicPlayBean[size];
        }
    };


    public Long getContentLength() {
        return this.contentLength;
    }
    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }
    public Integer getMusicDownProgress() {
        return this.musicDownProgress;
    }
    public void setMusicDownProgress(Integer musicDownProgress) {
        this.musicDownProgress = musicDownProgress;
    }
    public Integer getMusicDownOver() {
        return this.musicDownOver;
    }
    public void setMusicDownOver(Integer musicDownOver) {
        this.musicDownOver = musicDownOver;
    }
    public Integer getAudioNoteCount() {
        return this.audioNoteCount;
    }
    public void setAudioNoteCount(Integer audioNoteCount) {
        this.audioNoteCount = audioNoteCount;
    }
    public Integer getAudioSectionBeatCount() {
        return this.audioSectionBeatCount;
    }
    public void setAudioSectionBeatCount(Integer audioSectionBeatCount) {
        this.audioSectionBeatCount = audioSectionBeatCount;
    }
    public Integer getAudioMinBeatCount() {
        return this.audioMinBeatCount;
    }
    public void setAudioMinBeatCount(Integer audioMinBeatCount) {
        this.audioMinBeatCount = audioMinBeatCount;
    }
    public Integer getAudioBlankBeatCount() {
        return this.audioBlankBeatCount;
    }
    public void setAudioBlankBeatCount(Integer audioBlankBeatCount) {
        this.audioBlankBeatCount = audioBlankBeatCount;
    }
    public Boolean getAudioBeatFlag() {
        return this.audioBeatFlag;
    }
    public void setAudioBeatFlag(Boolean audioBeatFlag) {
        this.audioBeatFlag = audioBeatFlag;
    }
    public Integer getAudioTypeInt() {
        return this.audioTypeInt;
    }
    public void setAudioTypeInt(Integer audioTypeInt) {
        this.audioTypeInt = audioTypeInt;
    }
    public Integer getAudioUrlVer() {
        return this.audioUrlVer;
    }
    public void setAudioUrlVer(Integer audioUrlVer) {
        this.audioUrlVer = audioUrlVer;
    }
    public Integer getAudioBeatParamVer() {
        return this.audioBeatParamVer;
    }
    public void setAudioBeatParamVer(Integer audioBeatParamVer) {
        this.audioBeatParamVer = audioBeatParamVer;
    }

}
