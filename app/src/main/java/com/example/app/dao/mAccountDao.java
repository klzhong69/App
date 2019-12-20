package com.example.app.dao;

import com.example.app.Sqlentity.Account;
import com.example.app.cofig.Initialization;
import com.example.app.gen.AccountDao;

import java.util.List;

public class mAccountDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param account
     */
    public static void insert(Account account) {
        Initialization.getDaoInstantAccount().getAccountDao().insertOrReplace(account);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        Initialization.getDaoInstantAccount().getAccountDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param account
     */
    public static void update(Account account) {
        Initialization.getDaoInstantAccount().getAccountDao().update(account);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Account> query(int state) {
        return Initialization.getDaoInstantAccount().getAccountDao().queryBuilder().where(AccountDao.Properties.State.eq(state)).list();
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Account> querys(int type) {
        return Initialization.getDaoInstantAccount().getAccountDao().queryBuilder().where(AccountDao.Properties.Type.eq(type)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<Account> queryAll() {
        return Initialization.getDaoInstantAccount().getAccountDao().loadAll();
    }

    /**
     * 分页
     */
    public static List<Account> queryBuilder(int set , int lim) {

        return Initialization.getDaoInstantAccount().getAccountDao().queryBuilder().offset(set).limit(lim).list();
    }

    /**
     * 多表查询
     */
    /*public static List<User> queryBuilder(int set , int lim) {

        QueryBuilder<User> queryBuilder = My.getAccountDao().getUserDao().queryBuilder();

        queryBuilder.join(Address.class, AddressDao.Properties.userId)
                .where(AddressDao.Properties.Street.eq("Sesame Street"));

        return queryBuilder.list();

    }*/

    /**
     * 排序
     */

    public static List<Account> queryBuilder() {
        // 正序
        return Initialization.getDaoInstantAccount().getAccountDao().queryBuilder().orderAsc(AccountDao.Properties.State).list();

        // 反序
        //Initialization.getDaoInstantAccount().getUserDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //Initialization.getDaoInstantAccount().getUserDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
