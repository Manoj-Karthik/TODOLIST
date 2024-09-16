package com.mk.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.todolist.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findAllByUserId(long userid);

	//List<TaskEntity> findAllByUserid(long userid);
	
	//List<TaskEntity> findByUser(UserEntity user);

}
