package com.example.app.dao;

import com.example.app.Sqlentity.User;
import com.example.app.cofig.Initialization;
import com.example.app.gen.UserDao;

import java.util.List;

public class mUserDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param user
     */
    public static void insert(User user) {
        Initialization.getDaoInstantUser().getUserDao().insertOrReplace(user);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        Initialization.getDaoInstantUser().getUserDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param user
     */
    public static void update(User user) {
        Initialization.getDaoInstantUser().getUserDao().update(user);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> query(Long id) {
        return Initialization.getDaoInstantUser().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).list();
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> querys(Long userid) {
        return Initialization.getDaoInstantUser().getUserDao().queryBuilder().where(UserDao.Properties.UserId.eq(userid)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<User> queryAll() {
        return Initialization.getDaoInstantUser().getUserDao().loadAll();
    }

    /**
     * 分页
     */
    public static List<User> queryBuilder(int set , int lim) {

        return Initialization.getDaoInstantUser().getUserDao().queryBuilder().offset(set).limit(lim).list();
    }

    /**
     * 多表查询
     */
    /*public static List<User> queryBuilder(int set , int lim) {

        QueryBuilder<User> queryBuilder = My.getDaoInstantUser().getUserDao().queryBuilder();

        queryBuilder.join(Address.class, AddressDao.Properties.userId)
                .where(AddressDao.Properties.Street.eq("Sesame Street"));

        return queryBuilder.list();

    }*/

    /**
     * 排序
     */

    public static List<User> queryBuilder() {
        // 正序
        return Initialization.getDaoInstantUser().getUserDao().queryBuilder().orderAsc(UserDao.Properties.State).list();

        // 反序
        //Initialization.getDaoInstantUser().getUserDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //Initialization.getDaoInstantUser().getUserDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
