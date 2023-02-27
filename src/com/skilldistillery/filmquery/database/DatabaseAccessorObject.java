package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String user = "student";
	private static final String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;

//		String sql = "SELECT * FROM film WHERE id = ?";
		String sql = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			// Here is our mapping of query columns to our object fields:
			film = new Film(); // Create the object
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDecription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));

		}

		return film;

	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		// ...
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
			actor.setFilms(findFilmsByActorId(actorId));
		}
		actorResult.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.* FROM actor JOIN film_actor ON actor.id = film_actor.actor_id "
					+ "JOIN film ON film_actor.film_id = film.id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int filmsId = rs.getInt("id");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				Actor actor = new Actor(filmsId, fName, lName, null);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;

	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id \" + \" WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				Integer length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, length, repCost, rating, features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Film> findFilmByKeyWord(String keyWord) throws SQLException {
		List<Film> films = new ArrayList<>();
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			stmt.setString(2, "%" + keyWord + "%");

			stmt.setString(1, keyWord);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Here is our mapping of query columns to our object fields:
				Film film = new Film(); // Create the object
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDecription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setLanguageName(rs.getString("language.name"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				actors = findActorsByFilmId(film.getId());
				film.setActors(actors);
				films.add(film);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

}
