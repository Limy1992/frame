package com.base_dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.base_dao.BuildConfig;
import com.base_dao.R;
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
    public static void getInstant(Context applicationContext) {
        String dbName = BuildConfig.DB_NAME;
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(applicationContext, dbName, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
