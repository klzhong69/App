package com.example.myapplication.dao;

import com.example.myapplication.My;
import com.example.myapplication.entity.User;
import com.example.myapplication.gen.UserDao;

import java.util.List;

public class mUserDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param user
     */
    public static void insert(User user) {
        My.getDaoInstant().getUserDao().insertOrReplace(user);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        My.getDaoInstant().getUserDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param user
     */
    public static void update(User user) {
        My.getDaoInstant().getUserDao().update(user);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> query(Long id) {
        return My.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<User> queryAll() {
        return My.getDaoInstant().getUserDao().loadAll();
    }

    /**
     * 分页
     */
    public static List<User> queryBuilder(int set , int lim) {

        return My.getDaoInstant().getUserDao().queryBuilder().offset(set).limit(lim).list();
    }

    /**
     * 多表查询
     */
    /*public static List<User> queryBuilder(int set , int lim) {

        QueryBuilder<User> queryBuilder = My.getDaoInstant().getUserDao().queryBuilder();

        queryBuilder.join(Address.class, AddressDao.Properties.userId)
                .where(AddressDao.Properties.Street.eq("Sesame Street"));

        return queryBuilder.list();

    }*/

    /**
     * 排序
     */

    public static List<User> queryBuilder() {
        // 正序
        return My.getDaoInstant().getUserDao().queryBuilder().orderAsc(UserDao.Properties.State).list();

        // 反序
        //My.getDaoInstant().getUserDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //My.getDaoInstant().getUserDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
