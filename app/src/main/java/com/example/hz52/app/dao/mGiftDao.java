package com.example.hz52.app.dao;

import com.example.hz52.app.Sqlentity.Gift;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.gen.GiftDao;
import com.example.hz52.app.gen.MusicDao;

import java.util.List;

public class mGiftDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param gift
     */
    public static void insert(Gift gift) {
        Initialization.getDaoInstantGift().getGiftDao().insertOrReplace(gift);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        Initialization.getDaoInstantGift().getGiftDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param gift
     */
    public static void update(Gift gift) {
        Initialization.getDaoInstantGift().getGiftDao().update(gift);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Gift> query(Long giftid) {
        return Initialization.getDaoInstantGift().getGiftDao().queryBuilder().where(GiftDao.Properties.Id.eq(giftid)).list();
    }


    /**
     * 查询全部数据
     */
    public static List<Gift> queryAll() {
        return Initialization.getDaoInstantGift().getGiftDao().loadAll();
    }

    /**
     * 分页
     */
    public static List<Gift> queryBuilder(Long giftid,int set , int lim) {

        return Initialization.getDaoInstantGift().getGiftDao().queryBuilder().where(GiftDao.Properties.Id.eq(giftid)).offset(set).limit(lim).list();
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

    public static List<Gift> queryBuilder() {
        // 正序
        return Initialization.getDaoInstantGift().getGiftDao().queryBuilder().orderAsc(GiftDao.Properties.Id).list();

        // 反序
        //Initialization.getDaoInstantGift().getGiftDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //Initialization.getDaoInstantGift().getGiftDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
