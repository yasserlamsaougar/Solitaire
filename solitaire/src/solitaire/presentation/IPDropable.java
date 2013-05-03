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
	 * Traitement du drop accept�
	 */
	public void dropOK();
	
	/**
	 * Traitement du drop rejet�
	 */
	public void dropKO();
	
	/**
	 * Affichage positif du drop �ventuel sur le composant
	 */
	public void showEmpilable();
	
	/**
	 * Affichage n�gatif du drop �ventuel sur le composant
	 */
	public void showNotEmpilable();
	
	/**
	 * Retour � l'affichage neutre sur le composant
	 */
	public void showNeutre();

	/**
	 * Modification de l'�venement du drop
	 * @param event : l'�v�nement
	 */
	public void setDropEvent(EventObject event);
}