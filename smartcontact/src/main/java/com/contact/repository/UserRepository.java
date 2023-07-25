package com.contact.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.entity.Contact;
import com.contact.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

public User getUserByEmail(String email);	


@Query("select c from Contact c where c.cid=:cid")
public Contact getContactById(@Param("cid") Long cid);

@Query("from Contact as c where c.user.uid=:uid")
public Page<Contact> getAllContact(@Param("uid") Long uid,Pageable pageable);

@Query("update User u set u.enable=true where u.uid= :id")
@Modifying
public void enableUser(Long id);

@Query("select u from User u where u.verificationcode= :code")
public User getUserByVerifiation(String code);

}
