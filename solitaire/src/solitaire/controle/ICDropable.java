package solitaire.controle;

public interface ICDropable{
	/**
	 * Gestion du drop d'une carte dans un tas de cartes
	 * @param tdc : le tas de cartes à transférer
	 */
	public void drop(ICTasDeCartes tdc);

	/**
	 * Gestion de l'entrée d'un composant dans le champ d'action d'une cible d'un drag
	 * @param tdc : tas de carte potentiellement dropable
	 */
	public void dragEnter(ICTasDeCartes tdc);

	/**
	 * Gestion de la sortie d'un composant dans le champ d'action d'une cible d'un drag
	 */
	public void dragExit();
}
