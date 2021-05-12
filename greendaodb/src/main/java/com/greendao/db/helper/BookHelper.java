package com.greendao.db.helper;

import com.greendao.db.bean.Food;
import com.greendao.db.config.DaoFactory;

import java.util.List;

/**
 * ClassName: UserHelper
 * Description: 描述
 * Author: HuangGuoHua
 * Date: 2021/1/20 17:16
 */
public class BookHelper {

    public static void save(Food book) {
        DaoFactory.getInstance().getBookDao().insert(book);
    }

    public static void delete(Food bk) {

        List<Food> books = findAll();



    }

//    public static void update(User user){
//        DaoFactory.getInstance().getUserDao().update(user);
//    }


    public static List<Food> findAll() {
        return DaoFactory.getInstance().getBookDao().queryAll();
    }

    public static Food find(String name) {

        Food bk = null;

        List<Food> books = findAll();


        return bk;
    }

}
