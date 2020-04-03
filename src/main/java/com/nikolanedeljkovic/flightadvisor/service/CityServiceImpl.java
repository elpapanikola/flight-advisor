package com.nikolanedeljkovic.flightadvisor.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.domain.city.Comment;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CommentRepository;
import com.nikolanedeljkovic.flightadvisor.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("Posting comment");
		City city = cityRepository.findByName(cityName);
		if (city != null) {
			return commentRepository.save(Comment.builder().city(city).user(userRepository.findByUsername(username))
					.comment(comment.getComment()).createdAt(LocalDateTime.now()).modifiedAt(LocalDateTime.now())
					.build());
		} throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to post comment. Cityname not valid.");
	}

	@Override
	public String deleteComment(Long commentId, String username) {
		if (commentRepository.findById(commentId).get().getUser().getUsername().equals(username)) {
			commentRepository.deleteById(commentId);
			log.info("Comment deleted");
			return "Deleted";
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't delete comment posted by someone else.");
	}

	@Override
	public Comment updateComment(Long commentId, String comment, String username) {
		Optional<Comment> oldComment = commentRepository.findById(commentId);
		if (oldComment.isPresent() && oldComment.get().getUser().getUsername().equals(username)) {
			Comment newComment = oldComment.get();
			newComment.setComment(comment);
			newComment.setModifiedAt(LocalDateTime.now());
			return commentRepository.save(newComment);
		}
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't update comment posted by someone else.");
	}

}
