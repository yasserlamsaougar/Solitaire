package solitaire.presentation;

import java.util.EventObject;

import solitaire.controle.ICDropable;


/**
 * Interface de gestion d'un drop
 */
public interface IPDropable{
	/**
	 * Rend possible un drop sur le composant
	 */
	public void setDropable();
	
	/**
	 * Getter du controle
	 * @return le controle
	 */
	public abstract ICDropable getControle();
	
	/**
	 * Traitement du drop accepté
	 */
	public void dropOK();
	
	/**
	 * Traitement du drop rejeté
	 */
	public void dropKO();
	
	/**
	 * Affichage positif du drop éventuel sur le composant
	 */
	public void showEmpilable();
	
	/**
	 * Affichage négatif du drop éventuel sur le composant
	 */
	public void showNotEmpilable();
	
	/**
	 * Retour à l'affichage neutre sur le composant
	 */
	public void showNeutre();

	/**
	 * Modification de l'évenement du drop
	 * @param event : l'événement
	 */
	public void setDropEvent(EventObject event);
}