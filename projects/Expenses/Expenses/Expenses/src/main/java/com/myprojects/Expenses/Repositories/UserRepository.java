package com.myprojects.Expenses.Repositories;

import com.myprojects.Expenses.Entities.Expenses;
import com.myprojects.Expenses.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query(value = "select * from expenses where user_id=? and category_id=?",nativeQuery = true)
    List<Expenses> findExpensesByCategoryAndUser(long uid,long cid);
    @Query(value = "select * from expenses where date>=? and date<?",nativeQuery = true)
    List<Expenses> findExpensesByDate(Date from,Date to);


}
