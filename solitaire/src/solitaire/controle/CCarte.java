package solitaire.controle;

import solitaire.application.Carte;
import solitaire.presentation.PCarte;

/**
 * Controle d'une carte
 */
public class CCarte extends Carte {

	/**
	 * reference vers la pr�sentation de la carte
	 */
	private PCarte presentation;

	/**
	 * Constructeur d'un controleur de carte
	 * @param valeur : valeur de la carte
	 * @param couleur : couleur de la carte
	 */
	public CCarte(int valeur, int couleur) {
		super(valeur, couleur);
		
		presentation = new PCarte(valeurs[getValeur()-1]+couleurs[getCouleur()-1], this);
	}

	/**
	 * change l'etat de la carte (visible ou cach�e)
	 * @param visible : true or false (visible ou cach�e)
	 */
	public void setFaceVisible(boolean visible){
		super.setFaceVisible(visible);
		presentation.setFaceVisible(isFaceVisible());
	}
	
	/**
	 * getter de la pr�sentation de la carte
	 * @return la presentation de la carte
	 */
	public PCarte getPresentation() {
		return presentation;
	}
}
