package com.base_dao.dao;



import com.base_dao.helper.DaoHelper;
import com.base_dao.table.MusicPlayNowBean;


/**
 * 正在播放的音乐操作
 * 不处理SQLiteFullException异常
 * Created by on 2018/1/2.
 *
 * @author lmy
 */

public class DaoNowPlayMusic {
    /***
     * 插入数据
     */
    public static void insertLove(MusicPlayNowBean musicPlayNowBean) {
        DaoHelper
                .getDaoInstant()
                .getMusicPlayNowBeanDao()
                .insertOrReplace(musicPlayNowBean);
    }

    /**
     * 更新数据
     * @param currentPosition 播放索引位置
     */
    public static void upMusicData(Integer currentPosition){
        MusicPlayNowBean queryBean = DaoNowPlayMusic.queryBean();
//        MusicPlayNowBean musicPlayNowBean = new MusicPlayNowBean();
//        musicPlayNowBean.setAudioName(queryBean.getAudioName());
        queryBean.setAudioPlatPosition(currentPosition);
//        musicPlayNowBean.setAudioPlayDuration(queryBean.getAudioPlayDuration());
//        musicPlayNowBean.setAudioPlayCurrentPosition(queryBean.getAudioPlayCurrentPosition());
//        musicPlayNowBean.setAudioPlayUrl(queryBean.getAudioPlayUrl());
//        musicPlayNowBean.setMusicId(queryBean.getMusicId());
//        musicPlayNowBean.setUId(0);
//        musicPlayNowBean.setMainId(queryBean.getMainId());
//        musicPlayNowBean.setIsMusicPlay(queryBean.getIsMusicPlay());
        DaoHelper
                .getDaoInstant()
                .getMusicPlayNowBeanDao()
                .update(queryBean);
    }


    public static void insertNowMusic(int position, int musicId){
        MusicPlayNowBean musicPlayNowBean = new MusicPlayNowBean();
        musicPlayNowBean.setMusicId(musicId);
        musicPlayNowBean.setAudioPlatPosition(position);
        musicPlayNowBean.setUId(0);
        insertLove(musicPlayNowBean);
    }

    /**
     * 更新播放进度
     * @param audioPlayIntPosition  播放计算之后0-100之间的进度
     * @param audioPlayCurrentPosition  音乐的进度
     */
    public static void upMusicPlaySeelData(Integer audioPlayIntPosition, Integer audioPlayCurrentPosition){
        MusicPlayNowBean queryBean = DaoNowPlayMusic.queryBean();
        if (queryBean == null) {
            return;
        }
        queryBean.setAudioPlayIntPosition(audioPlayIntPosition);
//        MusicPlayNowBean musicPlayNowBean = new MusicPlayNowBean();
//        musicPlayNowBean.setAudioPlatPosition(queryBean.getAudioPlatPosition());
//        musicPlayNowBean.setAudioName(queryBean.getAudioName());
//        musicPlayNowBean.setAudioPlayDuration(queryBean.getAudioPlayDuration());
        queryBean.setAudioPlayCurrentPosition(audioPlayCurrentPosition);
//        musicPlayNowBean.setAudioPlayUrl(queryBean.getAudioPlayUrl());
//        musicPlayNowBean.setMusicId(queryBean.getMusicId());
//        musicPlayNowBean.setUId(0);
//        musicPlayNowBean.setMainId(queryBean.getMainId());
//        musicPlayNowBean.setIsMusicPlay(queryBean.getIsMusicPlay());
        DaoHelper
                .getDaoInstant()
                .getMusicPlayNowBeanDao()
                .update(queryBean);
    }




    /***
     * 删除全部数据
     */
    public static void deleteAll() {
        DaoHelper
                .getDaoInstant()
                .getMusicPlayNowBeanDao()
                .deleteAll();
    }

    /**
     * 查找数据
     */
    public static MusicPlayNowBean queryBean() {
        return DaoHelper
                .getDaoInstant()
                .getMusicPlayNowBeanDao()
                .queryBuilder()
                .unique();
    }
}
