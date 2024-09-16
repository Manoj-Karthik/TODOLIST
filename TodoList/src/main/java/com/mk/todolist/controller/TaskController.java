package com.mk.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.todolist.dao.TaskDao;
import com.mk.todolist.service.TaskService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	private TaskService taskservice;

	// post
	@PostMapping("/{userid}/task")
	public ResponseEntity<TaskDao> save(@PathVariable(name = "userid") long userid, @RequestBody TaskDao taskdao) {
		// TaskDto task = taskervice.saveTask(userid, taskdto);

		/*
		 * if (task != null) { return new ResponseEntity<>("User Created : " + task,
		 * HttpStatus.CREATED); } return new ResponseEntity<>("User not found",
		 * HttpStatus.BAD_REQUEST);
		 */
		return new ResponseEntity<>(taskservice.saveTask(userid, taskdao), HttpStatus.CREATED);

	}

	@GetMapping("/{userid}/get")
	public ResponseEntity<List<TaskDao>> getAll(@PathVariable(name = "userid") long userid) {
		return new ResponseEntity<>(taskservice.get(userid), HttpStatus.OK);
	}

	@GetMapping("/{userid}/getTask/{taskid}")
	public ResponseEntity<TaskDao> getOne(@PathVariable(name = "userid") long userid,
											@PathVariable(name = "taskid") long taskid) {
		return new ResponseEntity<>(taskservice.getTask(userid, taskid),HttpStatus.OK);
	}
	@DeleteMapping("/{userid}/deleteTask/{taskid}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "userid") long userid,
											@PathVariable(name = "taskid") long taskid) {
		taskservice.deleteTask(userid, taskid);
		return new ResponseEntity<>("Task Deleted SuccessFully",HttpStatus.OK);
	}
	
	
	
}