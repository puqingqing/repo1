package cn.itheima.test;

import cn.itcast.domain.Customer;
import cn.itcast.util.CreateEntityManage;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class JPADemo {

    //添加数据
    @Test
    public void testSave(){
        /**
         * 创建实体管理类工厂，借助Persistence的静态方法获取
         * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJPA");
        //创建
        EntityManager em = factory.createEntityManager();

        //创建事务对象
        EntityTransaction tx= em.getTransaction();

        //创建实力对象
        Customer customer=new Customer();
        customer.setCustName("传智");

        /*添加数据*/
        em.persist(customer);


        //开启事务
        tx.begin();

        //提交事务
        tx.commit();
        em.close();
        factory.close();
    }

    //修改数据
    @Test
    public void testUpdate(){

        EntityManager em = CreateEntityManage.getEntityManager();

        //创建事务对象
        EntityTransaction tx= em.getTransaction();

        //开启事务
        tx.begin();

       //先从数据库中进行查询
        Customer customer = em.find(Customer.class, 1L);
        customer.setCustAddress("贵州");
        //合并数据
        em.merge(customer);
        tx.commit();
    }


    // 删除数据
    @Test
    public void TestRemove(){
        //获取实体类
        EntityManager em = CreateEntityManage.getEntityManager();
        //获取事务对象
        EntityTransaction tx = em.getTransaction();

        //开启事务
        tx.begin();
        Customer customer = em.find(Customer.class, 2l);
        em.remove(customer);

        //提交事务
        tx.commit();
    }


    //统计个数
    @Test
    public void testCount(){
        EntityManager em = CreateEntityManage.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql="select count(custId) from cn.itcast.domain.Customer";
        Query query = em.createQuery(jpql);
        Object num = query.getSingleResult();
        System.out.println("数据库中共有"+num);
        tx.commit();
    }

    //查询所有
    @Test
    public void testFindAll(){
        EntityManager em = CreateEntityManage.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql="from cn.itcast.domain.Customer";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
       for (Object obj:list){
        System.out.println(obj);
      }
        tx.commit();
    }


    //查询id
    @Test
    public void testFinById(){

        EntityManager em = CreateEntityManage.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /*find是立即查询
        *getReference:
        * */
       /* Customer customer = em.find(Customer.class, 1l);*/
        Customer cu = em.getReference(Customer.class, 1l);
        System.out.println(cu);
        tx.commit();
    }


    @Test
    public void testPage(){

        EntityManager em = CreateEntityManage.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String jpql="from Customer where custName like ?";
        Query query = em.createQuery(jpql);
       query.setParameter(1,"传智%");
        List list = query.getResultList();
        for(Object obj:list){
            System.out.println(obj);
        }
        tx.commit();

    }
}
