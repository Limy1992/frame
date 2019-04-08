package com.base_dao.dao;

import com.base_dao.helper.DaoHelper;
import com.base_dao.table.TeaVideoDownBean;
import com.base_dao.table.TeaVideoDownBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 教学浏览记录和
 * Created by on 2018/4/3.
 *
 * @author lmy
 */
public class DaoTeaDown {
    /**
     * 插入数据
     */
    public static void teaInsert(TeaVideoDownBean teaVideoDownBean) {
        try {
            DaoHelper.getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .insertOrReplace(teaVideoDownBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询单条数据
     *
     * @param teaType 数据类型
     */
    public static TeaVideoDownBean teaQuery(int teaType) {
        try {
            return DaoHelper.getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .queryBuilder()
                    .where(TeaVideoDownBeanDao.Properties.TeaVideoType.eq(teaType))
                    .build()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TeaVideoDownBean();
    }

    /**
     * 查询 单节 单条数据
     *
     * @param classId 单节id
     * @param teaType 查询类型 0下载 1浏览足迹
     */
    public static TeaVideoDownBean teaQueryId(int classId, int teaType) {
        try {
            return DaoHelper
                    .getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .queryBuilder()
                    .where(TeaVideoDownBeanDao.Properties.TeaVideoSingleId.eq(classId)
                            , TeaVideoDownBeanDao.Properties.TeaVideoType.eq(teaType)
                            ,TeaVideoDownBeanDao.Properties.TeaAlbumSingleType.eq(TeaVideoDownBean.DOWN_SINGLE))
                    .build()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TeaVideoDownBean();
    }

    /**
     * 查询 单节 List数据
     *
     * @param albumId 使用专辑的id
     */
    public static List<TeaVideoDownBean> teaQueryListSingleId(int albumId) {
        try {
            return DaoHelper
                    .getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .queryBuilder()
                    .where(TeaVideoDownBeanDao.Properties.TeaVideoAlbumId.eq(albumId)
                            , TeaVideoDownBeanDao.Properties.TeaVideoType.eq(TeaVideoDownBean.TEACHING_DOWN)
                            , TeaVideoDownBeanDao.Properties.TeaAlbumSingleType.eq(TeaVideoDownBean.DOWN_SINGLE))
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 查询专辑 list数据
     *
     * @param albumId 专辑id
     */
    public static List<TeaVideoDownBean> teaQueryAlbumId(int albumId) {
        try {
            return DaoHelper
                    .getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .queryBuilder()
                    .where(TeaVideoDownBeanDao.Properties.TeaVideoAlbumId.eq(albumId)
                            , TeaVideoDownBeanDao.Properties.TeaVideoType.eq(TeaVideoDownBean.TEACHING_DOWN)
                            , TeaVideoDownBeanDao.Properties.TeaAlbumSingleType.eq(TeaVideoDownBean.DOWN_ALBUM))
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 查询专辑 List数据
     */
    public static List<TeaVideoDownBean> teaQueryListAlbum() {
        try {
            return DaoHelper
                    .getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .queryBuilder()
                    .where(TeaVideoDownBeanDao.Properties.TeaVideoType.eq(TeaVideoDownBean.TEACHING_DOWN)
                            , TeaVideoDownBeanDao.Properties.TeaAlbumSingleType.eq(TeaVideoDownBean.DOWN_ALBUM))
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 查询全部数据集合
     *
     * @param teaType 数据类型
     */
    public static List<TeaVideoDownBean> teaQueryList(int teaType) {
        try {
            return DaoHelper.getDaoInstant()
                    .getTeaVideoDownBeanDao()
                    .queryBuilder()
                    .where(TeaVideoDownBeanDao.Properties.TeaVideoType.eq(teaType))
                    .orderDesc(TeaVideoDownBeanDao.Properties.TeaVideoLateTime)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 删除选中的数据集合
     */
    public static void deleteListAll(List<TeaVideoDownBean> videoDownBeans) {
        DaoHelper
                .getDaoInstant()
                .getTeaVideoDownBeanDao()
                .deleteInTx(videoDownBeans);
    }

    /**
     * 删除单个数据
     * @param mainId 主键
     */
    public static void deleteOne(long mainId){
        DaoHelper
                .getDaoInstant()
                .getTeaVideoDownBeanDao()
                .deleteByKey(mainId);
    }
}
