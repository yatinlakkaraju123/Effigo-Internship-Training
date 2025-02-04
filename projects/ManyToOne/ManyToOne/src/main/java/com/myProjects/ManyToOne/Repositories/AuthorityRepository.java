package com.myProjects.ManyToOne.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProjects.ManyToOne.Entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    
}
