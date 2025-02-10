package com.myprojects.LMS.Repositories;

import com.myprojects.LMS.Entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {
    @Query(value = "select id from books where name=?",nativeQuery = true)
    int findIdByBookname(String name);
}
