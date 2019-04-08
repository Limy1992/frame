package com.base_dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 视频下载的角度存储
 * Created by on 2018/4/27.
 *
 * @author lmy
 */
@Entity
public class TeaVideoAngleBean {
    @Id(autoincrement = true)
    private Long id;

    /**关联的单节id*/
    private Long reClassId;

    /**镜头title*/
    private String angHeadLine;
    /**镜头id*/
    @Unique
    private Long angId;
    /**镜头播放连接*/
    private String angVideoUrl;
    @Generated(hash = 477252971)
    public TeaVideoAngleBean(Long id, Long reClassId, String angHeadLine,
            Long angId, String angVideoUrl) {
        this.id = id;
        this.reClassId = reClassId;
        this.angHeadLine = angHeadLine;
        this.angId = angId;
        this.angVideoUrl = angVideoUrl;
    }
    @Generated(hash = 1430284220)
    public TeaVideoAngleBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getReClassId() {
        return this.reClassId;
    }
    public void setReClassId(Long reClassId) {
        this.reClassId = reClassId;
    }
    public String getAngHeadLine() {
        return this.angHeadLine;
    }
    public void setAngHeadLine(String angHeadLine) {
        this.angHeadLine = angHeadLine;
    }
    public Long getAngId() {
        return this.angId;
    }
    public void setAngId(Long angId) {
        this.angId = angId;
    }
    public String getAngVideoUrl() {
        return this.angVideoUrl;
    }
    public void setAngVideoUrl(String angVideoUrl) {
        this.angVideoUrl = angVideoUrl;
    }


    @Override
    public String toString() {
        return "TeaVideoAngleBean{" +
                "id=" + id +
                ", reClassId=" + reClassId +
                ", angHeadLine='" + angHeadLine + '\'' +
                ", angId=" + angId +
                ", angVideoUrl='" + angVideoUrl + '\'' +
                '}';
    }
}
