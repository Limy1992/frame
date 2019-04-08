package com.base_dao.dao;


import com.base_dao.helper.DaoHelper;
import com.base_dao.table.MyEdTabBean;
import com.base_dao.table.MyEdTabBeanDao;

import java.util.List;

/**
 * 自定义标签
 * Created by on 2017/12/25.
 *
 * @author lmy
 */

public class MyEdTabDao {
    /**
     * 添加数据
     *
     * @param myEdTabBean
     */
    public static void insertLove(MyEdTabBean myEdTabBean) {
        DaoHelper
                .getDaoInstant()
                .getMyEdTabBeanDao()
                .insertOrReplace(myEdTabBean);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteId(long id) {
        DaoHelper
                .getDaoInstant()
                .getMyEdTabBeanDao()
                .deleteByKey(id);
    }

    /**
     * 删除所有数据
     */
    public static void deleteAll(String type){
        List<MyEdTabBean> myEdTabBeans = queryLove(type);
        for (MyEdTabBean myEdTabBean : myEdTabBeans) {
            deleteId(myEdTabBean.getId());
        }
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<MyEdTabBean> queryLove(String queryType) {
        return DaoHelper
                .getDaoInstant()
                .getMyEdTabBeanDao()
                .queryBuilder()
                .where(MyEdTabBeanDao.Properties.QueryType.eq(queryType))
                .list();

    }

    /**
     * 根据内容查询数据
     * @param title 内存
     * @return 对象
     */
    public static MyEdTabBean queryMyEd(String title, String tagType) {
        return DaoHelper
                .getDaoInstant()
                .getMyEdTabBeanDao()
                .queryBuilder()
                .where(MyEdTabBeanDao.Properties.TabTitle.eq(title)
                        ,MyEdTabBeanDao.Properties.TabTitle.eq(tagType))
                .unique();

    }

}
