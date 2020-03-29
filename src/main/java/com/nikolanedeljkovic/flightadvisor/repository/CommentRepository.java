package com.nikolanedeljkovic.flightadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolanedeljkovic.flightadvisor.domain.city.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
