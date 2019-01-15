package cn.itcast.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateEntityManage {

    /**
     * 创建实体管理类工厂，借助Persistence的静态方法获取
     * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
     */
    private static   EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJPA");
    }

    //创建实体管理类
    public static EntityManager getEntityManager(){
          return factory.createEntityManager();
    }
}
