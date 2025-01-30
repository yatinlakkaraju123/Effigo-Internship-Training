package com.myprojects.LMS.Repositories;

import com.myprojects.LMS.Entities.Borrow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from borrowing where user_id=?",nativeQuery = true)
    public void deleteAllByUser(int userId);
    @Modifying
    @Transactional
    @Query(value = "delete from borrowing where book_id=?",nativeQuery = true)
    public void deleteAllByBook(int bookId);
}
