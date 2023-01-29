package com.techgen.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "actors", cascade = CascadeType.REMOVE)
	private Set<Movie> movies = new HashSet<>();

	public Actor(String name) {
		super();
		this.name = name;
	}

	public void addMovie(Movie movie) {
		this.movies.add(movie);
		movie.getActors().add(this);
	}

	public void removeMovie(Movie movie) {
		this.movies.remove(movie);
		movie.getActors().remove(this);
	}

}
