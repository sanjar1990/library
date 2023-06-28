package org.example.repository;

import org.example.entity.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public CategoryEntity getCategoryByName(String categoryName) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from CategoryEntity as c where c.categoryName=:cName");
        query.setParameter("cName",categoryName);
        List<CategoryEntity> categoryList= query.getResultList();
        session.close();
         return categoryList.isEmpty()?null:categoryList.get(0);
    }

    public List<CategoryEntity> getAllCategory() {
        Session session=sessionFactory.openSession();
        List<CategoryEntity> list=session.createQuery("from  CategoryEntity where visible=true").getResultList();
        session.close();
        return list;
    }

    public void addCategory(CategoryEntity category) {
        Session session= sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
    }

    public int removeCategory(Integer id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("update CategoryEntity set visible=false where id=:id and visible=true");
        query.setParameter("id",id);
        int n=query.executeUpdate();
        transaction.commit();
        session.close();
        return n;
    }

    public void updateCategory(CategoryEntity isExists) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("update CategoryEntity set visible=true where id=:id and visible=false");
        query.setParameter("id",isExists.getId());
        int n=query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
