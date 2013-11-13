package com.github.yingzhuo.mycar.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.yingzhuo.mycar.domain.Badge;

public interface BadgeRepo extends CrudRepository<Badge, String> {

	@Query("from Badge as b order by b.name asc")
	public List<Badge> findAll();

	@Query(value = "select count(*) from TBL_USER_BADGE where user_id = ?1", nativeQuery = true)
	public BigInteger countByUserId(Integer id);

	@Query("from Badge as b where b.name = ?1")
	public Badge findByName(String name);

}
