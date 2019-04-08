package com.base_dao.dao;

import com.base_dao.helper.DaoHelper;
import com.base_dao.table.MusicUrlAndBetaVersion;
import com.base_dao.table.MusicUrlAndBetaVersionDao;

/**
 * 音乐url和节拍版本
 * CreateDate:2018/12/30
 * Author:lmy
 */
public class DaoMusicUrlBetaVersion {
    /***
     * 插入数据
     */
    public static void insert(MusicUrlAndBetaVersion musicUrlAndBetaVersion) {
        DaoHelper
                .getDaoInstant()
                .getMusicUrlAndBetaVersionDao()
                .insertOrReplace(musicUrlAndBetaVersion);
    }

    public static MusicUrlAndBetaVersion getVersion(int audioId) {
        return DaoHelper
                .getDaoInstant()
                .getMusicUrlAndBetaVersionDao()
                .queryBuilder()
                .where(MusicUrlAndBetaVersionDao.Properties.AudioId.eq(audioId))
                .unique();
    }

    public static void updateVersion(MusicUrlAndBetaVersion musicUrlAndBetaVersion){
        DaoHelper
                .getDaoInstant()
                .getMusicUrlAndBetaVersionDao()
                .update(musicUrlAndBetaVersion);
    }
}
