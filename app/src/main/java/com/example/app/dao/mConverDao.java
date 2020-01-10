package com.example.app.dao;

import com.example.app.Sqlentity.Conver;
import com.example.app.cofig.Initialization;
import com.example.app.gen.ConverDao;

import java.util.List;

public class mConverDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param conver
     */
    public static void insert(Conver conver) {
        Initialization.getDaoInstantConver().getConverDao().insertOrReplace(conver);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        Initialization.getDaoInstantConver().getConverDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param conver
     */
    public static void update(Conver conver) {
        Initialization.getDaoInstantConver().getConverDao().update(conver);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Conver> query(Long sendid,Long userid) {
        return Initialization.getDaoInstantConver().getConverDao().queryBuilder().where(ConverDao.Properties.SendId.eq(sendid), ConverDao.Properties.UserId.eq(userid)).list();
    }


    /**
     * 查询全部数据
     */
    public static List<Conver> queryAll(Long id) {
        return Initialization.getDaoInstantConver().getConverDao().queryBuilder().where(ConverDao.Properties.UserId.eq(id)).list();
    }

    /**
     * 分页
     */
    public static List<Conver> queryBuilder(Long sendid,int set , int lim) {

        return Initialization.getDaoInstantConver().getConverDao().queryBuilder().where(ConverDao.Properties.SendId.eq(sendid)).offset(set).limit(lim).list();
    }

    /**
     * 多表查询
     */
    /*public static List<User> queryBuilder(int set , int lim) {

        QueryBuilder<User> queryBuilder = My.getChatDao().getUserDao().queryBuilder();

        queryBuilder.join(Address.class, AddressDao.Properties.userId)
                .where(AddressDao.Properties.Street.eq("Sesame Street"));

        return queryBuilder.list();

    }*/

    /**
     * 排序
     */

    public static List<Conver> queryBuilder() {
        // 正序
        return Initialization.getDaoInstantConver().getConverDao().queryBuilder().orderAsc(ConverDao.Properties.Type).list();

        // 反序
        //Initialization.getDaoInstantChat().getUserDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //Initialization.getDaoInstantChat().getUserDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
