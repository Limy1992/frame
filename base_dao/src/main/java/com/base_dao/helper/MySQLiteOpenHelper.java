package com.base_dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.base_dao.table.DaoMaster;
import com.base_dao.table.TTestDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

/**
 * 数据库升级使用
 *
 * @author lmy
 * @date 2017/10/10
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                }, TTestDao.class
        );
    }
}
