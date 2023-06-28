package org.example.repository;

import org.example.entity.TakenBookEntity;
import org.example.enums.TakenBookStatus;
import org.example.mapper.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TakenBookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public TakenBookMapper getBookInfo(Integer bookId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select tb.id, tb.createdDate, tb.status, b.id, tb.studentId.id " +
                "from TakenBookEntity as tb  " +
                "inner join tb.bookId as b where b.id=:bookId" +
                " and tb.createdDate <now() " +
                "order by tb.createdDate desc " +
                "limit 1");
        query.setParameter("bookId", bookId);
        List<Object[]> objects = query.getResultList();
        if(objects==null){
            session.close();
            return null;
        }
        List<TakenBookMapper> takenBookMapperList = new LinkedList<>();
        for (Object[] obj : objects) {
            TakenBookMapper takenBookMapper = new TakenBookMapper();
            for (int i = 0; i < obj.length; i++) {
                takenBookMapper.setTakenBookId(obj[0].toString());
                takenBookMapper.setCreate_date(LocalDateTime.parse(obj[1].toString()));
                takenBookMapper.setTakenBookStatus(TakenBookStatus.valueOf(obj[2].toString()));
                takenBookMapper.setBookId(Integer.valueOf(obj[3].toString()));
                takenBookMapper.setStudentId(Integer.valueOf(obj[4].toString()));
            }
            takenBookMapperList.add(takenBookMapper);
            session.close();

        }
        return takenBookMapperList.isEmpty()?null:takenBookMapperList.get(0);
    }
    public void addTakenBookInfo(TakenBookEntity takenBookEntity) {
        Session session= sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(takenBookEntity);
        transaction.commit();
        session.close();
    }

    public void returnBook(String takenBookId, TakenBookStatus takenBookStatus) {
        Session session=sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
       Query query= session.createQuery("update TakenBookEntity set status=:status, returnDate=:date where id=:id");
       query.setParameter("status",takenBookStatus.toString());
       query.setParameter("date", LocalDate.now());
       query.setParameter("id",takenBookId);
       query.executeUpdate();
       transaction.commit();
       session.close();
    }

    public List<BooksOnHand> getAllBooksOnHandById(Integer id) {
        List<BooksOnHand> booksOnHandList= new LinkedList<>();
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("select tb.id, b.title, b.author," +
                " b.category.categoryName, tb.createdDate, tb.returnDate from" +
                " TakenBookEntity as tb inner  join tb.bookId as b " +
                "where tb.status='TOOK' and  tb.studentId=:id");
        query.setParameter("id",id);
        List<Object[]> listObj=query.getResultList();
        if(listObj==null){
            session.close();
            return null;
        }
        System.out.println(listObj.size());
        for (Object[] obj: listObj) {
            BooksOnHand booksOnHand= new BooksOnHand();
            for (int i = 0; i <listObj.size(); i++) {
                booksOnHand.setId(obj[0].toString());
                booksOnHand.setTitle(obj[1].toString());
                booksOnHand.setAuthor(obj[2].toString());
                booksOnHand.setCategory(obj[3].toString());
                LocalDateTime localDateTime=LocalDateTime.parse(obj[4].toString());
                booksOnHand.setTakenDate(LocalDate.of(localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth()));
                booksOnHand.setReturnDate(LocalDate.parse(obj[5].toString()));
                booksOnHandList.add(booksOnHand);
              break;
            }

        }
        session.close();
        return booksOnHandList;
    }

    public List<BooksOnHand> getAllTakenBookList(Integer id) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("select new org.example.mapper.BooksOnHand(tb.id, b.title, b.author," +
                " b.category.categoryName, tb.createdDate, tb.returnDate) from" +
                " TakenBookEntity as tb inner  join tb.bookId as b " +
                "where tb.status='RETURNED' and  tb.studentId=:id");
        query.setParameter("id",id);
        List<BooksOnHand> list=query.getResultList();
        session.close();
        return list;
    }

    public List<AllBooksOnHandMapper> getAllBooksOnHand() {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("select new org.example.mapper.AllBooksOnHandMapper(b.id," +
                " b.title, b.author, b.category.categoryName, tb.createdDate, tb.returnDate," +
                " s.name, s.surname, s.phone )from TakenBookEntity as tb inner join tb.bookId as b" +
                " inner join tb.studentId as s where tb.status='TOOK'");
        List<AllBooksOnHandMapper> list=query.getResultList();
        session.close();
        return list;
    }

    public List<BookHistoryMapper> getBookHistoryById(Integer id) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("select new org.example.mapper.BookHistoryMapper" +
                "(tb.createdDate, tb.returnDate, s.name, s.surname, s.phone, tb.note) from TakenBookEntity as" +
                " tb inner join tb.studentId as s where tb.bookId.id=?1");
        query.setParameter(1,id);
        List<BookHistoryMapper> list=query.getResultList();
        session.close();
        return list;
    }

    public List<BestBookMapper> getBestBooks() {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("select b.id, b.title, b.author," +
                " b.category.categoryName, count(tb.id) as c from " +
                "TakenBookEntity as tb inner join tb.bookId as b where tb.status='RETURNED'" +
                " group by b.id, b.title, b.author, b.category.id " +
                "order by c desc  " +
                "limit 10");
        List<BestBookMapper> list=new LinkedList<>();
        List<Object[]> objList=query.getResultList();
        for (Object[] obj: objList){
            BestBookMapper bestBookMapper= new BestBookMapper();
            for (int i = 0; i <objList.size() ; i++) {
                bestBookMapper.setId(Integer.valueOf(obj[0].toString()));
                bestBookMapper.setTitle(obj[1].toString());
                bestBookMapper.setAuthor(obj[2].toString());
                bestBookMapper.setCategoryName(obj[3].toString());
                bestBookMapper.setTakenCount(Integer.valueOf(obj[4].toString()));
                list.add(bestBookMapper);
                break;
            }
        }
        session.close();
        return list;
    }
}

//    select count(*), tb.book_id  from taken_book as tb
//    group by tb.book_id
//}
