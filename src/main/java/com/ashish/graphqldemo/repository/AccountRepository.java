package com.ashish.graphqldemo.repository;

import com.ashish.graphqldemo.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByUserId(Long userId);
}
