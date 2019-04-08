package com.base_dao.dao;


import com.base_dao.helper.DaoHelper;
import com.base_dao.table.MusicPlayBean;
import com.base_dao.table.MusicPlayBeanDao;

import java.util.List;

/**
 * 音乐播放数据库操作
 * Created by LMY on 2018/1/1.
 */

public class DaoMusciPlay {

    /***
     * 插入数据
     */
    public static void insertLove(MusicPlayBean musicPlayBean) {
        try {
            DaoHelper
                    .getDaoInstant()
                    .getMusicPlayBeanDao()
                    .insertOrReplace(musicPlayBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updataMusic(MusicPlayBean musicPlayBean) {
        DaoHelper
                .getDaoInstant()
                .getMusicPlayBeanDao()
                .update(musicPlayBean);
    }

    /**
     * 插入集合
     *
     * @param list
     */
    public static void insertList(List<MusicPlayBean> list) {
        DaoHelper
                .getDaoInstant()
                .getMusicPlayBeanDao()
                .insertInTx(list);
    }


    /**
     * 根据主键删除数据
     *
     * @param mainId 主键
     */
    public static void deleteId(long mainId) {
        try {
            DaoHelper
                    .getDaoInstant()
                    .getMusicPlayBeanDao()
                    .deleteByKey(mainId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据对象删除数据
     *
     * @param musicPlayBean 对象
     */
    public static void deleteMusicPlay(MusicPlayBean musicPlayBean) {
        DaoHelper
                .getDaoInstant()
                .getMusicPlayBeanDao()
                .delete(musicPlayBean);
    }

    /**
     * 删除所有数据
     */
    public static void deleteAll(List<MusicPlayBean> list) {
        DaoHelper
                .getDaoInstant()
                .getMusicPlayBeanDao()


//                .deleteAll()
                .deleteInTx(list);
//                .deleteAll();
    }


    /**
     * 根据条件查找播放的音乐数据
     *
     * @param musicType 查找类型
     * @return 数据集合
     */
    public static List<MusicPlayBean> queryWhere(int musicType) {
        return DaoHelper.getDaoInstant()
                .getMusicPlayBeanDao()
                .queryBuilder()
                .where(MusicPlayBeanDao.Properties.AudioPlayType.eq(musicType))
                .build()
                .list();
    }

    /**
     * 最近播放查询
     */
    public static List<MusicPlayBean> queryLateMusic() {
        return DaoHelper.getDaoInstant()
                .getMusicPlayBeanDao()
                .queryBuilder()
                .where(MusicPlayBeanDao.Properties.AudioPlayType.eq(MusicPlayBean.MUSIC_LATE))
                .orderDesc(MusicPlayBeanDao.Properties.SaveLateTime)
                .build()
                .list();
    }

    /**
     * 最近播放分页查询
     */
    public static List<MusicPlayBean> queryLatePagerMusic() {
        return DaoHelper.getDaoInstant()
                .getMusicPlayBeanDao()
                .queryBuilder()
                .where(MusicPlayBeanDao.Properties.AudioPlayType.eq(MusicPlayBean.MUSIC_LATE))
                .orderDesc(MusicPlayBeanDao.Properties.SaveLateTime)
                .build()
                .list();
    }

    /**
     * 搜索最近播放
     * 模糊查找
     *
     * @param searchText 关键字
     * @param musicType  查询类型
     */
    public static List<MusicPlayBean> searchLateMusic(String searchText, int pager, int musicType) {
        return DaoHelper.getDaoInstant()
                .getMusicPlayBeanDao()
                .queryBuilder()
                .where(MusicPlayBeanDao.Properties.AudioPlayType.eq(musicType)
                        , MusicPlayBeanDao.Properties.AudioName.like("%" + searchText + "%"))
//                .offset((pager - 1) * 20)
//                .limit(20)
                .list();

    }

    /***
     * 查找一个对象
     * @param musicType 类型
     * @return 对象
     */
    public static MusicPlayBean queryBean(int musicType) {
        try {
            return DaoHelper.getDaoInstant()
                    .getMusicPlayBeanDao()
                    .queryBuilder()
                    .where(MusicPlayBeanDao.Properties.AudioPlayType.eq(musicType))
                    .build()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MusicPlayBean();
    }

    /***
     * 查找一个对象
     * @param musicId 音乐id
     * @return 对象
     */
    public static MusicPlayBean queryWhereMusicId(int musicId, int musicPlayType) {
        try {
            return DaoHelper.getDaoInstant()
                    .getMusicPlayBeanDao()
                    .queryBuilder()
                    .where(MusicPlayBeanDao.Properties.Id.eq(musicId)
                            , MusicPlayBeanDao.Properties.AudioPlayType.eq(musicPlayType))
                    .build()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MusicPlayBean();
    }

    /***
     * 查找一个对象
     * @param musicId 音乐id
     * @param  musicPlayType 类型1
     * @param  musicPlayType2 类型2
     * @return 对象
     */
    public static MusicPlayBean queryWhereAndOrMusicId(int musicId, int musicPlayType, int musicPlayType2) {
        try {
            return DaoHelper.getDaoInstant()
                    .getMusicPlayBeanDao()
                    .queryBuilder()
                    .where(MusicPlayBeanDao.Properties.Id.eq(musicId)
                            , MusicPlayBeanDao.Properties.AudioPlayType.eq(musicPlayType))
                    .whereOr(MusicPlayBeanDao.Properties.Id.eq(musicId)
                            , MusicPlayBeanDao.Properties.AudioPlayType.eq(musicPlayType2))
                    .build()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MusicPlayBean();
    }

    /***
     * 查找集合列表
     * @param musicId 音乐id
     * @return 集合列表
     */
    public static List<MusicPlayBean> queryWhereList(int musicId, int musicPlayType) {
        return DaoHelper.getDaoInstant()
                .getMusicPlayBeanDao()
                .queryBuilder()
                .where(MusicPlayBeanDao.Properties.Id.eq(musicId)
                        , MusicPlayBeanDao.Properties.AudioPlayType.eq(musicPlayType))
                .build()
                .list();
    }

    /**
     * 扫描本地文件
     *
     * @param path          本地文件路径
     * @param musicPlayType 缓存或者下载
     * @return 对象
     */
    public static List<MusicPlayBean> queryWhereLoad(String path, int musicPlayType) {
        return DaoHelper.getDaoInstant()
                .getMusicPlayBeanDao()
                .queryBuilder()
                .where(MusicPlayBeanDao.Properties.DownloadUrl.eq(path)
                        , MusicPlayBeanDao.Properties.AudioPlayType.eq(musicPlayType))
                .build()
                .list();
    }

    /**
     * 更新下载的状态
     */
    public static void updateDown(MusicPlayBean playBean) {
        playBean.setAudioPlayType(MusicPlayBean.MUSIC_DOWN);
        playBean.setMusicDownOver(MusicPlayBean.DOWN_OVER);
        DaoMusciPlay.updataMusic(playBean);
//        MusicPlayBean musicPlayBean = new MusicPlayBean();
//        musicPlayBean.setMainId(playBean.getMainId());
//        musicPlayBean.setShareUrl(playBean.getShareUrl());
//        musicPlayBean.setShareTitle(playBean.getShareTitle());
//        musicPlayBean.setShareImageUrl(playBean.getShareImageUrl());
//        musicPlayBean.setShareDescription(playBean.getShareDescription());
//        musicPlayBean.setId(playBean.getId());
//        musicPlayBean.setAudioUrl(playBean.getAudioUrl());
//        musicPlayBean.setAudioTitle(playBean.getAudioTitle());

//        musicPlayBean.setAudioName(playBean.getAudioName());
//        musicPlayBean.setDownloadUrl(playBean.getDownloadUrl());

//        musicPlayBean.setAudioNoteCount(playBean.getAudioNoteCount());
//        musicPlayBean.setAudioSectionBeatCount(playBean.getAudioSectionBeatCount());
//        musicPlayBean.setAudioMinBeatCount(playBean.getAudioMinBeatCount());
//        musicPlayBean.setAudioBlankBeatCount(playBean.getAudioBlankBeatCount());
//        musicPlayBean.setAudioBeatFlag(playBean.getAudioBeatFlag());


    }
}
