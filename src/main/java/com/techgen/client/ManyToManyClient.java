package com.techgen.client;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.techgen.entity.Actor;
import com.techgen.entity.Movie;
import com.techgen.util.HibernateUtil;

public class ManyToManyClient {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();

			session = sessionFactory.openSession();

			Transaction transaction = session.getTransaction();

			transaction.begin();

			// persistMovieActor(session);
			// persistActor(session);
			// persistMovie(session);
			// addActorToMovie(session);
			// addMovieToActor(session);
			// deleteMovie(session);
			deleteActor(session);

			transaction.commit();

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	private static void persistActor(Session session) {
		Actor actor1 = new Actor("Aamir Khan");
		session.persist(actor1);
	}

	private static void persistMovie(Session session) {
		Movie movie1 = new Movie("Ishq");
		session.persist(movie1);
	}

	private static void persistMovieActor(Session session) {
		Actor actor1 = new Actor("Shahrukh Khan");
		Actor actor2 = new Actor("Salman Khan");
		Actor actor3 = new Actor("Ajay Devgan");
		Actor actor4 = new Actor("Akshay Kumar");

		Movie movie1 = new Movie("Karan Arjun");
		Movie movie2 = new Movie("London Dreams");
		Movie movie3 = new Movie("Khakee");
		Movie movie4 = new Movie("Dil Toh Pagal Hai");
		Movie movie5 = new Movie("Mujse Shadi Karogi");
		Movie movie6 = new Movie("Bazigar");

		movie1.getActors().add(actor1);
		movie1.getActors().add(actor2);

		movie2.getActors().add(actor2);
		movie2.getActors().add(actor3);

		movie3.getActors().add(actor3);
		movie3.getActors().add(actor4);

		movie4.getActors().add(actor4);
		movie4.getActors().add(actor1);

		movie5.getActors().add(actor2);
		movie5.getActors().add(actor4);

		movie6.getActors().add(actor1);

		session.persist(movie1);
		session.persist(movie2);
		session.persist(movie3);
		session.persist(movie4);
		session.persist(movie5);
		session.persist(movie6);
	}

	private static void addMovieToActor(Session session) {
		Movie movie = getMovie(session, 7L);
		Actor actor1 = getActor(session, 5l);
		Actor actor2 = getActor(session, 1l);

		movie.getActors().add(actor1);
		movie.getActors().add(actor2);
	}

	private static void addActorToMovie(Session session) {
		Movie movie = getMovie(session, 8L);
		Actor actor1 = getActor(session, 6l);
		Actor actor2 = getActor(session, 3l);

		actor1.addMovie(movie);
		actor2.addMovie(movie);
	}

	private static void deleteMovie(Session session) {
		Movie movie = getMovie(session, 5L);
		session.remove(movie);
	}

	private static void deleteActor(Session session) {
		Actor actor = getActor(session, 2L);
		session.remove(actor);
	}

	private static Movie getMovie(Session session, Long id) {
		Movie movie = session.get(Movie.class, id);
		return movie;
	}

	private static Actor getActor(Session session, Long id) {
		Actor actor = session.get(Actor.class, id);
		return actor;
	}

}
