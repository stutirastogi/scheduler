package com.assignment.scheduler.repository;

import com.assignment.scheduler.enums.Status;
import com.assignment.scheduler.model.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task t WHERE t.status = :status")
	public List<Task> findAllByStatus(@Param("status") Status status);

	@Query("SELECT t FROM Task t WHERE t.name = :name")
	public Task findByName(@Param("name") String name);
	

}
