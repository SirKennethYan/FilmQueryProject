package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	DatabaseAccessorObject dbao = new DatabaseAccessorObject();
//	DatabaseAccessor dba = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = dba.findFilmById(15);
//		System.out.println(film);
//	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		System.out.println("MENU:");
		System.out.println("1: Look up a film by its id.");
		System.out.println("2: Look up a film by a search keyword.");
		System.out.println("3: Exit the application.");

		String menuOpt = input.nextLine();

		if (menuOpt.equals("1")) {
			System.out.println("Enter a Film ID (1 - 1000): ");
			int userInput = input.nextInt();
			Film filmById = dbao.findFilmById(userInput);
			if (filmById != null) {
				System.out.println("\nTitle: " + filmById.getTitle() + "\nRelease Year: " + filmById.getReleaseYear()
						+ "\nRating: " + filmById.getRating() + "\nDescription: " + filmById.getDecription());
				startUserInterface(input);
			} else {
				System.out.println("No film found with ID: " + userInput);
				startUserInterface(input);
			}
		} else if (menuOpt.equals("2")) {
			System.out.println("Enter search Keyword: 'Action', 'Drama', 'Western', etc. ");
			String userInput = input.next();
			List<Film> filmsByKeyword = dbao.findFilmByKeyWord(userInput);
			if (filmsByKeyword != null && !filmsByKeyword.isEmpty()) {
				System.out.println("Films matching keyword: " + userInput);
				for (Film film : filmsByKeyword) {
					System.out.println("Title: " + film.getTitle() + " Release Year: " + film.getReleaseYear()
							+ " Rating: " + film.getRating() + " Description: " + film.getDecription());
				}
			} else {
				System.out.println("No films found matching keyword " + userInput);
			}
		} else if (menuOpt.equals("3")) {
			System.out.println("Goodbye! ");
		} else {
			System.out.println("Invalid input. Please enter a number between 1 - 3.");
			startUserInterface(input);
		}

	}

}
