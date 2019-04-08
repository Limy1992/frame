package com.base_dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.base_dao.table.DaoMaster;
import com.base_dao.table.DaoSession;

/**
 * 初始化数据库
 * Created by on 2018/10/4.
 *
 * @author lmy
 */
public class DaoHelper {

    private static DaoSession daoSession;

    /**
     * 配置数据库
     */
    public static void instantDatabase(Context applicationContext) {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(applicationContext, "project.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
