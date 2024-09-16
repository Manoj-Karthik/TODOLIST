package com.mk.todolist.service;

import java.util.List;

import com.mk.todolist.dao.TaskDao;

public interface TaskService {

	public TaskDao saveTask(long userid, TaskDao taskdto);

	public List<TaskDao> get(long userid);
	
	public TaskDao getTask(long userid, long taskid);
	
	public void deleteTask(long userid, long taskid);

}
