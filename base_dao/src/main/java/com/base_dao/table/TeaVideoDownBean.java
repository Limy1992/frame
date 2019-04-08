package com.base_dao.table;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 专辑和单节使用一张表， 使用teaAlbumSingleType记录下载类型
 * Created by on 2018/4/3.
 *
 * @author lmy
 */
@Entity
public class TeaVideoDownBean {
    /**
     * 下载
     */
    public static final int TEACHING_DOWN = 0;

    /**
     * 浏览足迹
     */
    public static final int TEACHING_LOOK = 1;

    /**
     * 下载的专辑
     */
    public static final String DOWN_ALBUM = "down_ablum";
    /**
     * 下载的课程
     */
    public static final String DOWN_SINGLE = "down_single";

    @Id(autoincrement = true)
    private Long mainId;

    /**
     * 视频专辑id
     */
    private Integer teaVideoAlbumId;
    /**
     * 视频单节id
     */
    private Integer teaVideoSingleId;
    /**
     * 教学视频标题
     */
    private String teaVideoTitle;
    /**
     * 教学视频dance
     */
    private String teaVideoDanceText;
    /**
     * 教学的type
     */
    private String teaVideoTypeText;

    /**
     * 是否购买
     */
    private Boolean teaIsVideoBuy;

    /**
     * 浏览时间
     */
    private Long teaVideoLateTime;

    /**
     * 记录类型  0下载  1浏览足迹
     */
    private Integer teaVideoType;

    /**
     * 下载路径
     */
    private String teaDownPath;

    /**
     * 记录下载   专辑和单节  (浏览足迹，只记录是单节)
     */
    private String teaAlbumSingleType;

    /**
     * 专辑封面字节图片
     */
    private String teaPicPath;

    /**新  更新标记， 不记录数据库*/
    @Transient
    private String teaNewsUpFlag;

    /**关联镜头表 一对多管理*/
    @ToMany(referencedJoinProperty = "reClassId")
    private List<TeaVideoAngleBean> teaVideoAngleBeans;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1064343976)
    private transient TeaVideoDownBeanDao myDao;

    @Generated(hash = 1482009041)
    public TeaVideoDownBean() {
    }

    @Generated(hash = 1403723256)
    public TeaVideoDownBean(Long mainId, Integer teaVideoAlbumId, Integer teaVideoSingleId,
            String teaVideoTitle, String teaVideoDanceText, String teaVideoTypeText,
            Boolean teaIsVideoBuy, Long teaVideoLateTime, Integer teaVideoType, String teaDownPath,
            String teaAlbumSingleType, String teaPicPath) {
        this.mainId = mainId;
        this.teaVideoAlbumId = teaVideoAlbumId;
        this.teaVideoSingleId = teaVideoSingleId;
        this.teaVideoTitle = teaVideoTitle;
        this.teaVideoDanceText = teaVideoDanceText;
        this.teaVideoTypeText = teaVideoTypeText;
        this.teaIsVideoBuy = teaIsVideoBuy;
        this.teaVideoLateTime = teaVideoLateTime;
        this.teaVideoType = teaVideoType;
        this.teaDownPath = teaDownPath;
        this.teaAlbumSingleType = teaAlbumSingleType;
        this.teaPicPath = teaPicPath;
    }

    public Long getMainId() {
        return this.mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Integer getTeaVideoAlbumId() {
        return this.teaVideoAlbumId;
    }

    public void setTeaVideoAlbumId(Integer teaVideoAlbumId) {
        this.teaVideoAlbumId = teaVideoAlbumId;
    }

    public Integer getTeaVideoSingleId() {
        return this.teaVideoSingleId;
    }

    public void setTeaVideoSingleId(Integer teaVideoSingleId) {
        this.teaVideoSingleId = teaVideoSingleId;
    }

    public String getTeaVideoTitle() {
        return this.teaVideoTitle;
    }

    public void setTeaVideoTitle(String teaVideoTitle) {
        this.teaVideoTitle = teaVideoTitle;
    }

    public String getTeaVideoDanceText() {
        return this.teaVideoDanceText;
    }

    public void setTeaVideoDanceText(String teaVideoDanceText) {
        this.teaVideoDanceText = teaVideoDanceText;
    }

    public String getTeaVideoTypeText() {
        return this.teaVideoTypeText;
    }

    public void setTeaVideoTypeText(String teaVideoTypeText) {
        this.teaVideoTypeText = teaVideoTypeText;
    }

    public Boolean getTeaIsVideoBuy() {
        return this.teaIsVideoBuy;
    }

    public void setTeaIsVideoBuy(Boolean teaIsVideoBuy) {
        this.teaIsVideoBuy = teaIsVideoBuy;
    }

    public Long getTeaVideoLateTime() {
        return this.teaVideoLateTime;
    }

    public void setTeaVideoLateTime(Long teaVideoLateTime) {
        this.teaVideoLateTime = teaVideoLateTime;
    }

    public Integer getTeaVideoType() {
        return this.teaVideoType;
    }

    public void setTeaVideoType(Integer teaVideoType) {
        this.teaVideoType = teaVideoType;
    }

    public String getTeaDownPath() {
        return this.teaDownPath;
    }

    public void setTeaDownPath(String teaDownPath) {
        this.teaDownPath = teaDownPath;
    }

    public String getTeaAlbumSingleType() {
        return this.teaAlbumSingleType;
    }

    public void setTeaAlbumSingleType(String teaAlbumSingleType) {
        this.teaAlbumSingleType = teaAlbumSingleType;
    }

    public String getTeaNewsUpFlag() {
        return teaNewsUpFlag;
    }

    public void setTeaNewsUpFlag(String teaNewsUpFlag) {
        this.teaNewsUpFlag = teaNewsUpFlag;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Keep
    @Generated(hash = 1277385683)
    public List<TeaVideoAngleBean> getTeaVideoAngleBeans() {
        if (teaVideoAngleBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeaVideoAngleBeanDao targetDao = daoSession.getTeaVideoAngleBeanDao();
            List<TeaVideoAngleBean> teaVideoAngleBeansNew = targetDao
                    ._queryTeaVideoDownBean_TeaVideoAngleBeans((long)teaVideoSingleId);
            synchronized (this) {
                if (teaVideoAngleBeans == null) {
                    teaVideoAngleBeans = teaVideoAngleBeansNew;
                }
            }
        }
        return teaVideoAngleBeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1716571923)
    public synchronized void resetTeaVideoAngleBeans() {
        teaVideoAngleBeans = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public String getTeaPicPath() {
        return this.teaPicPath;
    }

    public void setTeaPicPath(String teaPicPath) {
        this.teaPicPath = teaPicPath;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 911428744)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeaVideoDownBeanDao() : null;
    }
}
