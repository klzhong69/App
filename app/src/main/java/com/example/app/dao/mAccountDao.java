package com.example.app.dao;

import com.example.app.Sqlentity.User;
import com.example.app.gen.UserDao;
import com.example.app.information;

import java.util.List;

public class mAccountDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param user
     */
    public static void insert(User user) {
        information.getDaoInstant().getUserDao().insertOrReplace(user);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        information.getDaoInstant().getUserDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param user
     */
    public static void update(User user) {
        information.getDaoInstant().getUserDao().update(user);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> query(Long id) {
        return information.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).list();
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> querys(Long userid) {
        return information.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.UserId.eq(userid)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<User> queryAll() {
        return information.getDaoInstant().getUserDao().loadAll();
    }

    /**
     * 分页
     */
    public static List<User> queryBuilder(int set , int lim) {

        return information.getDaoInstant().getUserDao().queryBuilder().offset(set).limit(lim).list();
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
        return information.getDaoInstant().getUserDao().queryBuilder().orderAsc(UserDao.Properties.State).list();

        // 反序
        //information.getDaoInstant().getUserDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //information.getDaoInstant().getUserDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
