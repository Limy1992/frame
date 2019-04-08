package com.base_dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 发布图文自定义标签和搜索历史
 * Created by on 2017/12/25.
 *
 * @author lmy
 */
@Entity
public class MyEdTabBean {

    /**
     * 发布的自定义标签 图文
     */
    public static final String MY_TAB = "0";

    /**
     * 自定义标签视频
     */
    public static final String MY_TAB_VIDEO = "1";

    /***
     * 2 查询搜索的历史数据
     *
     */
    public static final String SEARCH_HISTORY = "2";

    /**
     *查询教学的搜索历史数据
     */
    public static final String TEARCHING_TAG ="3";



    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String tabTitle;
    //查询类型
    private String queryType;
    @Generated(hash = 770921049)
    public MyEdTabBean(Long id, String tabTitle, String queryType) {
        this.id = id;
        this.tabTitle = tabTitle;
        this.queryType = queryType;
    }
    @Generated(hash = 1204504208)
    public MyEdTabBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTabTitle() {
        return this.tabTitle;
    }
    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }
    public String getQueryType() {
        return this.queryType;
    }
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
