package com.base_dao.table.refer;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 教学下载进度存储
 * Created by on 2018/5/2.
 *
 * @author lmy
 */
@Entity
public class ReTeaDown {
    @Id(autoincrement = true)
    private Long id;

    /**下载的本地路径*/
    private String teaDownPath;
    /**下载的文件总大小*/
    private Long contentLength;
    /**已经下载的进度*/
    private Integer teaDownProgress;
    @Generated(hash = 883810430)
    public ReTeaDown(Long id, String teaDownPath, Long contentLength,
            Integer teaDownProgress) {
        this.id = id;
        this.teaDownPath = teaDownPath;
        this.contentLength = contentLength;
        this.teaDownProgress = teaDownProgress;
    }
    @Generated(hash = 995703257)
    public ReTeaDown() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTeaDownPath() {
        return this.teaDownPath;
    }
    public void setTeaDownPath(String teaDownPath) {
        this.teaDownPath = teaDownPath;
    }
    public Long getContentLength() {
        return this.contentLength;
    }
    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }
    public Integer getTeaDownProgress() {
        return this.teaDownProgress;
    }
    public void setTeaDownProgress(Integer teaDownProgress) {
        this.teaDownProgress = teaDownProgress;
    }
}
