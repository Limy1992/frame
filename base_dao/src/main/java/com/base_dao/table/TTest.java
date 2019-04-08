package com.base_dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 测试表
 * CreateDate:2019/4/8
 * Author:lmy
 */
@Entity
public class TTest {
    @Id(autoincrement = true)
    private Long pId;
    private String testData;
    @Generated(hash = 1184138228)
    public TTest(Long pId, String testData) {
        this.pId = pId;
        this.testData = testData;
    }
    @Generated(hash = 934692722)
    public TTest() {
    }
    public Long getPId() {
        return this.pId;
    }
    public void setPId(Long pId) {
        this.pId = pId;
    }
    public String getTestData() {
        return this.testData;
    }
    public void setTestData(String testData) {
        this.testData = testData;
    }
}
