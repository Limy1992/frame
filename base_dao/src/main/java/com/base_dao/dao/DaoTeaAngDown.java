package com.base_dao.dao;

import com.base_dao.helper.DaoHelper;
import com.base_dao.table.TeaVideoAngleBean;
import com.base_dao.table.TeaVideoAngleBeanDao;

import java.util.List;

/**
 * 教学镜头
 * Created by on 2018/4/27.
 *
 * @author lmy
 */
public class DaoTeaAngDown {

    public static void angInsert(TeaVideoAngleBean teaVideoAngleBean){
        DaoHelper
                .getDaoInstant()
                .getTeaVideoAngleBeanDao()
                .insertOrReplace(teaVideoAngleBean);
    }

    /**
     * 根据关联的单节id 查询课程的镜头数量
     * @param reClassId 单节id
     */
    public static List<TeaVideoAngleBean> queryAngList(int reClassId){
        return DaoHelper
                .getDaoInstant()
                .getTeaVideoAngleBeanDao()
                .queryBuilder()
                .where(TeaVideoAngleBeanDao.Properties.ReClassId.eq(reClassId))
                .list();
    }

    /**
     * 删除镜头数据
     * @param mainId 镜头主键
     */
    public static void deleteAng(long mainId){
        DaoHelper
                .getDaoInstant()
                .getTeaVideoAngleBeanDao()
                .deleteByKey(mainId);

    }
}
