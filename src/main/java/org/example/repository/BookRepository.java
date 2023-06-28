package org.example.repository;

import org.example.dto.BookDto;
import org.example.dto.CategoryDto;
import org.example.entity.BookEntity;
import org.example.mapper.BooksOnHand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<BookDto> getAllBook(){
        Session session= sessionFactory.openSession();
        Query query=session.createQuery("select new org.example.dto.BookDto(b.id, b.title, " +
                "b.author,b.category.categoryName,b.publishDate) from BookEntity as b where visible=true");
        List<BookDto> list=query.getResultList();
        session.close();
        return list;
    }

    public List<BookDto> searchByData(String data, Integer id) {
        Session session=sessionFactory.openSession();
       Query query= session.createQuery("select new org.example.dto.BookDto(b.id, b.title,b.author," +
               "b.category.categoryName,b.publishDate) from BookEntity as b where " +
               "b.id=:id or b.title like :data or b.author like: data or b.category.categoryName like:data and b.visible=true");
       query.setParameter("data","%"+data+"%");
       query.setParameter("id",id);
       List<BookDto> bookDtoList=query.getResultList();
       session.close();
       return bookDtoList;
            }
    public List<BookDto> searchByCategory(String category) {
        Session session=sessionFactory.openSession();
        Query query= session.createQuery("select new org.example.dto.BookDto(b.id, b.title,b.author," +
                "b.category.categoryName,b.publishDate) from BookEntity as b where " +
                "b.id=:id or b.title like :data or b.author like: data or b.category.categoryName like:data");
        query.setParameter("data","%"+category+"%");
        List<BookDto> bookDtoList=query.getResultList();
        session.close();
        return bookDtoList;
    }

    public BookEntity getBookById(Integer bookId) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("From BookEntity where id=:id");
        query.setParameter("id",bookId);
        List<BookEntity> list=query.getResultList();
        session.close();
        return list.isEmpty()?null:list.get(0);
    }


    public void addBook(BookEntity bookEntity) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(bookEntity);
        transaction.commit();
        session.close();
    }

    public int removeBook(Integer id) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("update BookEntity set visible= false where id=?1 and visible=true");
        query.setParameter(1,id);
        int n=query.executeUpdate();
        transaction.commit();
        session.close();
        return n;
    }
}
