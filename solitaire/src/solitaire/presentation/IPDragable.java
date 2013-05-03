package solitaire.presentation;

import java.awt.Point;
import java.util.EventObject;

import solitaire.controle.ICDragable;


/**
 * Interface de gestion d'un drag
 */
public interface IPDragable{
	/**
	 * Rend possible un drag sur le tas de carte
	 * @param tdcDragable : le tas de cartes dragable
	 */
	public void setDragable(PTasDeCartes tdcDragable);
	
	/**
	 * Getter du controle
	 * @return le controle
	 */
	public abstract ICDragable getControle();
	
	/**
	 * Début du drag and drop
	 * @param tdc : tas de cartes à déplacer
	 */
	public void debutDNDOK(PTasDeCartes tdc);

	/**
	 * Annulation du début du drag and drop
	 * @param tdc : tas de cartes à déplacer
	 */
	public void debutDNDKO(PTasDeCartes tdc);

	/**
	 * Affichage du déplacement lors du drag&drop
	 */
	public void deplacementDND(Point p);

	/**
	 * Traitement de fin d'un drag&drop réussi
	 * @param tdc : tas de cartes concerné par le drag&drop
	 */
	public void finDNDOK(PTasDeCartes tdc);

	/**
	 * Traitement de fin d'un drag&drop raté
	 * @param tdc : tas de cartes concerné par le drag&drop
	 */
	public void finDNDKO(PTasDeCartes tdc);
	
	/**
	 * Modification de l'évenement du drag
	 * @param dge : l'événement
	 */
	public void setDragEvent(EventObject dge);
	
	/**
	 * Modification des coordonnées du curseur par rapport au tas de cartes
	 * @param cursorLocation : coordonnées du point
	 */
	public void setCursorLocation(Point cursorLocation);
}