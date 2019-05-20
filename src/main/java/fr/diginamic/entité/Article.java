package fr.diginamic.entité;

public class Article {
	private int id;
	private String designation;
	private String fournisseur;
	private int prix;

	public Article(int id, String designation, String fournisseur, int prix) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.designation = designation;
		this.fournisseur = fournisseur;
		this.prix = prix;

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Setter
	 * 
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the fournisseur
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Setter
	 * 
	 * @param fournisseur
	 *            the fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * @return the prix
	 */
	public int getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}

}
