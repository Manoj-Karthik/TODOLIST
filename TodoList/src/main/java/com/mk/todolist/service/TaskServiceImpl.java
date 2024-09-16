package com.mk.todolist.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.todolist.dao.TaskDao;
import com.mk.todolist.exception.APIException;
import com.mk.todolist.exception.TaskNotFound;
import com.mk.todolist.exception.UserNotFound;
import com.mk.todolist.model.Task;
import com.mk.todolist.model.User;
import com.mk.todolist.repository.TaskRepository;
import com.mk.todolist.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskrepository;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public TaskDao saveTask(long userid, TaskDao taskdao) {
		/*
		 * Optional<UserEntity> user = userrepository.findById(userid);
		 * if(user.isPresent()) { User user = user.get(); Task task =
		 * modelmapper.map(taskdao, TaskEntity.class); task.setUser(user);
		 * Task entity = taskrepository.save(task); return modelmapper.map(entity,
		 * TaskDto.class); } return null;
		 */

		User user = userrepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("User ID %d Not Found", userid))); // search userid is
																										// present or
																										// not

		Task task = modelmapper.map(taskdao, Task.class); // if present taskdto to taskentity.class
		task.setUser(user); // set user in task entity
		Task entity = taskrepository.save(task);// save the task
		return modelmapper.map(entity, TaskDao.class); // return taskdto by converting entity to dto
	}

	@Override
	public List<TaskDao> get(long userid) {
		userrepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User %d Not Found", userid)));
		List<Task> tasks = taskrepository.findAllByUserId(userid);
		/*Optional<UserEntity> id = userrepository.findById(userid);
		if(id.isPresent()) {
			
			UserEntity entity = id.get();
			List<TaskEntity> userList = taskrepository.findByUser(entity);
			System.out.println(userList);
			return userList.stream().map(task -> modelmapper.map(userList, TaskDto.class)).collect(Collectors.toList());
			
		}
		//taskrepository.findByUser(null)
		return null;
		*/
		//System.out.println(tasks);
		return tasks.stream().map(task-> modelmapper.map(task, TaskDao.class)).collect(Collectors.toList());
	}
	@Override
	public TaskDao getTask(long userid, long taskid) {
		User user=userrepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User %d Not Found", userid)));
		Task task=taskrepository.findById(taskid).orElseThrow(
				()-> new TaskNotFound(String.format("Task Not %d Found", taskid)));
		if(user.getId() != task.getUser().getId())
		{
			throw new APIException(String.format("API %d EXCEPITION ", userid));
		}
		
		return modelmapper.map(task, TaskDao.class);
	}
	@Override
	public void deleteTask(long userid, long taskid) {
		
		User user = userrepository.findById(userid).orElseThrow(
				()->  new UserNotFound(String.format("User %d Not Found", userid)));
		
		Task entity = taskrepository.findById(taskid).orElseThrow(
				()-> new TaskNotFound(String.format("Task Not %d Found", taskid)));
		
		if(user.getId() != entity.getUser().getId())
		{
			throw new APIException(String.format("API %d Not Found", userid));
		}
		
		taskrepository.deleteById(taskid);
		
	}

}
