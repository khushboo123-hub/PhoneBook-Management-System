package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dollop.app.entity.GroupEntity;

@Repository
public interface IGroupRepository extends JpaRepository<GroupEntity, Integer>{
}
