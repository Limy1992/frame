package com.base_dao.table;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 正在播放的音乐， 和暂停的音乐
 * Created by on 2018/1/2.
 *
 * @author lmy
 */
@Entity
public class MusicPlayNowBean {
    @Id(autoincrement = true)
    private Long mainId;
    @Unique
    private Integer uId;
    /**音乐id*/
    private Integer musicId;
    /**音乐名称*/
    private String audioName;
    /**音乐播放地址*/
    private String audioPlayUrl;
    /**播放音乐的最大值*/
    private Integer audioPlayDuration = 0;
    /**播放的索引位置*/
    private Integer audioPlatPosition=0;
    /**播放的进度*/
    private Integer audioPlayCurrentPosition = 0;
    /**播放的进度0-100*/
    private Integer audioPlayIntPosition = 0;
    /**当前是否正在播放*/
    private boolean isMusicPlay;
    @Generated(hash = 2041741399)
    public MusicPlayNowBean(Long mainId, Integer uId, Integer musicId,
            String audioName, String audioPlayUrl, Integer audioPlayDuration,
            Integer audioPlatPosition, Integer audioPlayCurrentPosition,
            Integer audioPlayIntPosition, boolean isMusicPlay) {
        this.mainId = mainId;
        this.uId = uId;
        this.musicId = musicId;
        this.audioName = audioName;
        this.audioPlayUrl = audioPlayUrl;
        this.audioPlayDuration = audioPlayDuration;
        this.audioPlatPosition = audioPlatPosition;
        this.audioPlayCurrentPosition = audioPlayCurrentPosition;
        this.audioPlayIntPosition = audioPlayIntPosition;
        this.isMusicPlay = isMusicPlay;
    }
    @Generated(hash = 1128122848)
    public MusicPlayNowBean() {
    }
    public Long getMainId() {
        return this.mainId;
    }
    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }
    public Integer getUId() {
        return this.uId;
    }
    public void setUId(Integer uId) {
        this.uId = uId;
    }
    public String getAudioName() {
        return this.audioName;
    }
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
    public String getAudioPlayUrl() {
        return this.audioPlayUrl;
    }
    public void setAudioPlayUrl(String audioPlayUrl) {
        this.audioPlayUrl = audioPlayUrl;
    }
    public Integer getAudioPlayDuration() {
        return this.audioPlayDuration;
    }
    public void setAudioPlayDuration(Integer audioPlayDuration) {
        this.audioPlayDuration = audioPlayDuration;
    }
    public Integer getAudioPlatPosition() {
        return this.audioPlatPosition;
    }
    public void setAudioPlatPosition(Integer audioPlatPosition) {
        this.audioPlatPosition = audioPlatPosition;
    }
    public Integer getAudioPlayCurrentPosition() {
        return this.audioPlayCurrentPosition;
    }
    public void setAudioPlayCurrentPosition(Integer audioPlayCurrentPosition) {
        this.audioPlayCurrentPosition = audioPlayCurrentPosition;
    }
    public Integer getMusicId() {
        return this.musicId;
    }
    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }
    public boolean getIsMusicPlay() {
        return this.isMusicPlay;
    }
    public void setIsMusicPlay(boolean isMusicPlay) {
        this.isMusicPlay = isMusicPlay;
    }
    public Integer getAudioPlayIntPosition() {
        return this.audioPlayIntPosition == null ? 0:audioPlayIntPosition;
    }
    public void setAudioPlayIntPosition(Integer audioPlayIntPosition) {
        this.audioPlayIntPosition = audioPlayIntPosition;
    }

}
