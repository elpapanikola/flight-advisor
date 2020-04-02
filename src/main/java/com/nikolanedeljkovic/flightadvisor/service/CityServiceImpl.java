package com.nikolanedeljkovic.flightadvisor.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.domain.city.Comment;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CommentRepository;
import com.nikolanedeljkovic.flightadvisor.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	
	@Override
	public City addCity(City city) {
		return cityRepository.save(city);
	}
	
	@Override
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}
	
	@Override
	public List<City> getAllCities(Optional<Integer> numberOfComments) {
		return cityRepository.getAllCitiesWithSpecifiedNumberOfComments(numberOfComments);
	}
	
	@Override
	public Comment postComment(String cityName, Comment comment, String username) {
		return commentRepository.save(Comment.builder()
				.city(cityRepository.findByName(cityName))
				.user(userRepository.findByUsername(username))
				.comment(comment.getComment())
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.build());
	}

	@Override
	public String deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
		return "Deleted";
	}

	@Override
	public Comment updateComment(Long commentId, String comment) {
		Optional<Comment> oldComment = commentRepository.findById(commentId);
		Comment newComment = oldComment.get();
		newComment.setComment(comment);
		newComment.setModifiedAt(LocalDateTime.now());
		return commentRepository.save(newComment);
	}
	

}
