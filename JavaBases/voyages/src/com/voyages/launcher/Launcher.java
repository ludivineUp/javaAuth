package com.voyages.launcher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.voyages.beans.ArrivalDateTripComparator;
import com.voyages.beans.ArrivalPlaceTripComparator;
import com.voyages.beans.DepartureDateTripComparator;
import com.voyages.beans.DeparturePlaceTripComparator;
import com.voyages.beans.Place;
import com.voyages.beans.Trip;
/**
 * 
 * @author ludiviine
 *
 */
public class Launcher {

	private static List<Place> places;
	private static List<Trip> trips;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// choix du menu par l'utilisateur
		int item;

		// collections représentant les données de l'utilisateur
		places = new ArrayList<Place>();
		places.add(new Place(0, "Paris"));
		places.add(new Place(1, "Marseille"));

		trips = new ArrayList<Trip>();
		
		// entrée clavier
		Scanner scanner = new Scanner(System.in);

		// affichage et action tant que l'utilisateur ne souhaite pas quitter
		// l'application (choix 5)
		do {
			// affichage du menu
			menu();
			// saisie du choix
			item = scanner.nextInt();
			System.out.println();
			// action en fonction du choix de l'utilisateur
			switch (item) {
			case 1: // affiche les ieux
				displayPlaces();
				break;
			case 2: // affiche les voyages
				displayTrips();
				break;
			case 3: // ajouter un lieu
				addPlace();
				break;
			case 4: // ajouter un voyage
				addTrip();
				break;
			case 5: // quitter
				break;
			default: // mauvaise saisie clavier : on la fait refaire à
						// l'utilisateur
				System.out.println("Choix incorrect.");
				break;
			}
		} while (item != 5);

		
		System.out.println("Au revoir !");

	}

		private static void addTrip() {
		System.out.println("*** CREER UN NOUVEAU VOYAGE ***");
		displayPlaces();
		Scanner scan = new Scanner(System.in);
		// Saisie du lieu de départ
		System.out.print("Entrer l'id du lieu de départ :");
		int departure = scan.nextInt();
		System.out.println();
		// Saisie du lieu d'arrivée
		System.out.print("Entrer l'id du lieu d'arrivée :");
		int arrival = scan.nextInt();
		System.out.println();
		// Saisie de la date de départ
		System.out.println("Entrer la date de départ");
		Date departureDate = createDate();
		// Saisie de la date d'arrivée
		System.out.println("Entrer la date d'arrivée");
		Date arrivalDate = createDate();
		// ajout du voyages s'il n'existe pas déjà
		Trip trip = new Trip(trips.size(), places.get(departure),
				places.get(arrival), departureDate, arrivalDate);
		if (trips.contains(trip)) {
			System.out.println("Le voyage existe déjà");
		} else {
			trips.add(trip);
		}
		System.out.println("*** *** ***");
	}

	private static Date createDate() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Entrer le jour :");
		int day = scan.nextInt();
		System.out.println();
		System.out.print("Entrer le mois :");
		int month = scan.nextInt();
		System.out.println();
		System.out.print("Entrer l'année :");
		int year = scan.nextInt();
		System.out.println();
		Calendar c = GregorianCalendar.getInstance();
		c.set(year, month - 1, day);
		return c.getTime();
	}

	private static void addPlace() {
		System.out.println("*** CREER UN NOUVEAU LIEU ***");
		System.out.print("Entrer le nom du lieu : ");
		Scanner scan = new Scanner(System.in);
		// id : taille de la lsite avant l'insertion
		Place place = new Place(places.size(), scan.next());
		// ajout avec vérification que le lieu n'existe pas
		if (!places.contains(place)) {
			places.add(place);
		} else {
			System.out.println("Le lieu existe déjà.");
		}
		System.out.println("*** *** ***");
	}

	private static void displayTrips() {
		System.out.println("*** LISTE DES VOYAGES ***");
		sortMenu();
		Scanner scan = new Scanner(System.in);
		int item = scan.nextInt();
		switch (item) {
		case 1:
			Collections.sort(trips, new DeparturePlaceTripComparator());
			break;
		case 2:
			Collections.sort(trips, new ArrivalPlaceTripComparator());
			break;
		case 3:
			Collections.sort(trips, new DepartureDateTripComparator());
			break;
		case 4:
			Collections.sort(trips, new ArrivalDateTripComparator());
			break;

		default:
			System.out.println("pas de trie !");
			break;
		}
		for (Trip trip : trips) {
			System.out.println(trip);
		}
		System.out.println("*** *** ***");
	}

	private static void displayPlaces() {
		System.out.println("*** LISTE DES LIEUX ***");
		Collections.sort(places);
		// changer les id de tous les lieux en focntion de leur nouvel indice
		Iterator<Place> it = places.iterator();
		int i = 0;
		while (it.hasNext()) {
			Place place = (Place) it.next();
			place.setId(i);
			i++;
		}
		for (Place place : places) {
			System.out.println(place);
		}
		System.out.println("*** *** ***");
	}

	public static void sortMenu() {
		System.out.println("Trie par : ");
		System.out.println("1. Par lieu de départ");
		System.out.println("2. Par lieu d'arrivée");
		System.out.println("3. Par date de départ");
		System.out.println("4. Par date d'arrivée");
		System.out.print("Entre votre choix : ");
	}

	public static void menu() {
		System.out.println("1. Liste des lieux");
		System.out.println("2. Liste des voyages");
		System.out.println("3. Ajouter un lieu");
		System.out.println("4. Ajouter un voyage");
		System.out.println("5. Quitter");
		System.out.print("Entre votre choix : ");
	}

}
