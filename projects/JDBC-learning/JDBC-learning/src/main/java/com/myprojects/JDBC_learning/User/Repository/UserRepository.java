package com.myprojects.JDBC_learning.User.Repository;

import com.myprojects.JDBC_learning.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String SelectQuery = """
            
            SELECT * FROM users;
            """;

    private static String InsertQuery = """
            insert into users(name,email) values(?,?)
            """;
    private static String UpdateQuery = """
            
            update users SET name =? , email = ? WHERE id = ?
            """;
    public List<User> selectAllUsers()
    {   List<User> users = jdbcTemplate.query(SelectQuery,BeanPropertyRowMapper.newInstance(User.class));
        logger.info("Fetched users: {}", users);  // Log the fetched users

        return users;

    }

    public void addUser(User user)
    {
        jdbcTemplate.update(InsertQuery,user.getName(),user.getEmail());
    }

    public void updateUser(int id,User user)
    {
        jdbcTemplate.update(UpdateQuery,user.getName(),user.getEmail(),id);
    }

    public User selectUserById(int id)
    {
        User user = jdbcTemplate.queryForObject("select * from users where id = ?",new BeanPropertyRowMapper<>(User.class),id);
        return user;
    }

    public void deleteUserById(int id)
    {
        jdbcTemplate.update("delete from users where id = ?",id);
    }
}
