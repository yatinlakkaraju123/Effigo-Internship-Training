package com.myprojects.LMS.Repositories;

import com.myprojects.LMS.Entities.RequestBooks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestBooksRepository extends JpaRepository<RequestBooks,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from request_books where user_id=?",nativeQuery = true)
    public void deleteAllByUser(int userId);
    @Modifying
    @Transactional
    @Query(value = "delete from request_books where book_id=?",nativeQuery = true)
    public void deleteAllByBook(int bookId);

}
