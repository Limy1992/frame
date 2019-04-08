package com.base_dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 音乐url和beta的版本管理
 * CreateDate:2018/12/30
 * Author:lmy
 */
@Entity
public class MusicUrlAndBetaVersion {

    @Id(autoincrement = true)
    private Long mainId;

    /**
     * 音乐id
     */
    @Unique
    private int audioId;

    private Integer beatParamVersion = 0;

    private Integer audioUrlVersion = 0;

    @Generated(hash = 1762238028)
    public MusicUrlAndBetaVersion(Long mainId, int audioId,
            Integer beatParamVersion, Integer audioUrlVersion) {
        this.mainId = mainId;
        this.audioId = audioId;
        this.beatParamVersion = beatParamVersion;
        this.audioUrlVersion = audioUrlVersion;
    }

    @Generated(hash = 85465404)
    public MusicUrlAndBetaVersion() {
    }

    public Long getMainId() {
        return this.mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public int getAudioId() {
        return this.audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public Integer getBeatParamVersion() {
        return this.beatParamVersion;
    }

    public void setBeatParamVersion(Integer beatParamVersion) {
        this.beatParamVersion = beatParamVersion;
    }

    public Integer getAudioUrlVersion() {
        return this.audioUrlVersion;
    }

    public void setAudioUrlVersion(Integer audioUrlVersion) {
        this.audioUrlVersion = audioUrlVersion;
    }
}
