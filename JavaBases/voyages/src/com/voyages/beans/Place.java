package com.voyages.beans;

import java.io.Serializable;
/**
 * Représentation des lieux pour les voyages
 * @author ludivine
 *
 */
public class Place implements Comparable<Place>, Serializable{
	/**
	 * Long
	 * serial id par défault
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * int
	 * Identifiant unique correspondant à la clé primaire de la base
	 */
	private int id;
	/**
	 * String
	 * Nom du lieu
	 */
	private String name;
	/**
	 * Constructeur par défaut sans aucune initialisation
	 */
	public Place(){}
	/**
	 * Constructeur avec le nom
	 * @param name String le nom du lieu
	 */
	public Place(String name){
		this.name = name;
	}
	/**
	 * Constructeur complet
	 * @param id int l'identifiant unique
	 * @param name String le nom du lieu
	 */
	public Place(int id, String name){
		this.id = id;
		this.name = name;
	}
	/**
	 * Getter de l'identifiant
	 * @return int id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter de l'id
	 * @param id int
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter du nom
	 * @see java.util.String
	 * @return name String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter du nom
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "" + id + " : " + (name != null ? "name=" + name : "");
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/**
	 * Surcharge du compareTo en fonction du nom du lieu
	 */
	@Override
	public int compareTo(Place arg0) {
		// TODO Auto-generated method stub
		return name.compareTo(arg0.name);
	}
	
}
