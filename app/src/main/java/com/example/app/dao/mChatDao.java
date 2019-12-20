package com.example.app.dao;

import com.example.app.Sqlentity.Chat;
import com.example.app.cofig.Initialization;
import com.example.app.gen.ChatDao;

import java.util.List;

public class mChatDao {

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param chat
     */
    public static void insert(Chat chat) {
        Initialization.getDaoInstantChat().getChatDao().insertOrReplace(chat);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void delete(Long id) {
        Initialization.getDaoInstantChat().getChatDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param chat
     */
    public static void update(Chat chat) {
        Initialization.getDaoInstantChat().getChatDao().update(chat);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Chat> query(Long conver) {
        return Initialization.getDaoInstantChat().getChatDao().queryBuilder().where(ChatDao.Properties.Conversation.eq(conver)).list();
    }


    /**
     * 查询全部数据
     */
    public static List<Chat> queryAll() {
        return Initialization.getDaoInstantChat().getChatDao().loadAll();
    }

    /**
     * 分页
     */
    public static List<Chat> queryBuilder(Long conver,int set , int lim) {

        return Initialization.getDaoInstantChat().getChatDao().queryBuilder().where(ChatDao.Properties.Conversation.eq(conver)).offset(set).limit(lim).list();
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

    public static List<Chat> queryBuilder() {
        // 正序
        return Initialization.getDaoInstantChat().getChatDao().queryBuilder().orderAsc(ChatDao.Properties.Data).list();

        // 反序
        //Initialization.getDaoInstantChat().getUserDao().queryBuilder().orderDesc(UserDao.Properties.Id).list();

        // 多条件
        //Initialization.getDaoInstantChat().getUserDao().queryBuilder().orderAsc(UserDao.Properties.Id).orderDesc(UserDao.Properties.MemberSex).list();

    }
}
