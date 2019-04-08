package com.base_dao.dao;


import com.base_dao.helper.DaoHelper;
import com.base_dao.table.MusicDownloadBean;
import com.base_dao.table.MusicDownloadBeanDao;

import java.util.List;

/**
 * 操作我的下载音乐， 数据库
 * Created by on 2017/12/11.
 *
 * @author lmy
 */

public class MusicDownloadDao {

    /**
     * 添加数据
     *
     * @param musicDownloadBean 添加对象
     */
    public static void insertLove(MusicDownloadBean musicDownloadBean) {
        DaoHelper
                .getDaoInstant()
                .getMusicDownloadBeanDao()
                .insertOrReplace(musicDownloadBean);
    }

    /**
     * 删除我的下载音乐数据
     *
     * @param musicDownloadBean 删除的对象
     */
    public static void delete(MusicDownloadBean musicDownloadBean) {
        DaoHelper
                .getDaoInstant()
                .getMusicDownloadBeanDao()
                .delete(musicDownloadBean);
    }

    /**
     * 条件查找
     *
     * @param musicType 0查找已经下载的本地音乐， 1查找网络里面的音乐
     * @return 数据
     */
    public static List<MusicDownloadBean> queryMusicWhere(String musicType) {
        return DaoHelper
                .getDaoInstant()
                .getMusicDownloadBeanDao()
                .queryBuilder()
                .where(MusicDownloadBeanDao.Properties.MusicType.eq(musicType))
                .list();
    }

    /**
     * 数据库， 模糊查找
     *
     * @param musicName 查找的name
     * @return 数据
     */
    public static List<MusicDownloadBean> searchMusic(String musicName) {
        return DaoHelper
                .getDaoInstant()
                .getMusicDownloadBeanDao()
                .queryBuilder()
                .where(MusicDownloadBeanDao.Properties.MusicType.like("%" + musicName + "%"))
                .list();
    }

    /**
     * 根据id查询对象
     */
    public static MusicDownloadBean queryBean(int musicId){
        return DaoHelper
                .getDaoInstant()
                .getMusicDownloadBeanDao()
                .queryBuilder()
                .where(MusicDownloadBeanDao.Properties.MusicId.eq(musicId))
                .unique();
    }
}
