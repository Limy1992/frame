package com.base_dao.dao;


import com.base_dao.helper.DaoHelper;
import com.base_dao.table.VideoDownLoadBean;
import com.base_dao.table.VideoDownLoadBeanDao;

import java.util.List;

/**
 * 视频的下载和最近播放
 * Created by on 2018/1/5.
 *
 * @author lmy
 */

public class DaoVideo {
    /***
     * 插入数据
     */
    public static void insertLove(VideoDownLoadBean videoDownLoadBean) {
        try {
            DaoHelper
                    .getDaoInstant()
                    .getVideoDownLoadBeanDao()
                    .insertOrReplace(videoDownLoadBean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteVidoes(VideoDownLoadBean videoDownLoadBean) {
        try {
            DaoHelper
                    .getDaoInstant()
                    .getVideoDownLoadBeanDao()
                    .delete(videoDownLoadBean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteVidoes(long videoId) {
        try {
            DaoHelper
                    .getDaoInstant()
                    .getVideoDownLoadBeanDao()
                    .deleteByKey(videoId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static VideoDownLoadBean queryPathVideo(String videoPath, String videoType) {
        try {
            return DaoHelper
                    .getDaoInstant()
                    .getVideoDownLoadBeanDao()
                    .queryBuilder()
                    .where(VideoDownLoadBeanDao.Properties.VideoDownPath.eq(videoPath),
                            VideoDownLoadBeanDao.Properties.VideoType.eq(videoType))
                    .unique();
        }catch (Exception e){
            e.printStackTrace();
        }

        return new VideoDownLoadBean();
    }

    /**
     * 根据视频名称查询
     */
    public static List<VideoDownLoadBean> queryNameVideo(String videoTitle, String videoType) {
        return DaoHelper
                .getDaoInstant()
                .getVideoDownLoadBeanDao()
                .queryBuilder()
                .where(VideoDownLoadBeanDao.Properties.VideoTitle.eq(videoTitle),
                        VideoDownLoadBeanDao.Properties.VideoType.eq(videoType))
                .list();
    }


    /**
     * 更新
     *
     * @param videoDownLoadBean
     */
    public static void updateVideo(VideoDownLoadBean videoDownLoadBean) {
        DaoHelper
                .getDaoInstant()
                .getVideoDownLoadBeanDao()
                .update(videoDownLoadBean);

    }


    /**
     * 查找视频
     *
     * @param videoType 是下载的还是最近播放  倒序排序
     */
    public static List<VideoDownLoadBean> queryVidoes(String videoType) {
        return DaoHelper
                .getDaoInstant()
                .getVideoDownLoadBeanDao()
                .queryBuilder()
                .orderDesc(VideoDownLoadBeanDao.Properties.VideoSaveTime)
                .where(VideoDownLoadBeanDao.Properties.VideoType.eq(videoType))
                .list();

    }

    /**
     * 查找视频
     */
    public static VideoDownLoadBean queryVetoesBean(int videoId, String videoType) {
        try {
            return DaoHelper
                    .getDaoInstant()
                    .getVideoDownLoadBeanDao()
                    .queryBuilder()
                    .where(VideoDownLoadBeanDao.Properties.VideoId.eq(videoId),
                            VideoDownLoadBeanDao.Properties.VideoType.eq(videoType))
                    .unique();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new VideoDownLoadBean();
    }






    public static void saveToDb(String url
            , String coverUrl
            , String videoName
            , String downVideoPath
            , int videoId
            , String videoTag
            , boolean isDownOver) {
        VideoDownLoadBean pathVideo = DaoVideo.queryPathVideo(downVideoPath, VideoDownLoadBean.VIDEO_DOWN);
        VideoDownLoadBean videoDownLoadBean = new VideoDownLoadBean();
        videoDownLoadBean.setIsDownOver(isDownOver);
        videoDownLoadBean.setVideoUrl(url);
        videoDownLoadBean.setVideoCoverUrl(coverUrl);
        videoDownLoadBean.setVideoType(VideoDownLoadBean.VIDEO_DOWN);
        videoDownLoadBean.setVideoTitle(videoName);
        videoDownLoadBean.setVideoSaveTime(System.currentTimeMillis());
        videoDownLoadBean.setVideoDownPath(downVideoPath);
        videoDownLoadBean.setVideoId(videoId);
        videoDownLoadBean.setVideoTag(videoTag);
        if (pathVideo != null) {
            videoDownLoadBean.setMainId(pathVideo.getMainId());
            DaoVideo.updateVideo(videoDownLoadBean);
        }else {
            DaoVideo.insertLove(videoDownLoadBean);
        }
    }

}
