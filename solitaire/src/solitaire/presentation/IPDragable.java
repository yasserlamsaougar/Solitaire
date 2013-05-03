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
	 * D�but du drag and drop
	 * @param tdc : tas de cartes � d�placer
	 */
	public void debutDNDOK(PTasDeCartes tdc);

	/**
	 * Annulation du d�but du drag and drop
	 * @param tdc : tas de cartes � d�placer
	 */
	public void debutDNDKO(PTasDeCartes tdc);

	/**
	 * Affichage du d�placement lors du drag&drop
	 */
	public void deplacementDND(Point p);

	/**
	 * Traitement de fin d'un drag&drop r�ussi
	 * @param tdc : tas de cartes concern� par le drag&drop
	 */
	public void finDNDOK(PTasDeCartes tdc);

	/**
	 * Traitement de fin d'un drag&drop rat�
	 * @param tdc : tas de cartes concern� par le drag&drop
	 */
	public void finDNDKO(PTasDeCartes tdc);
	
	/**
	 * Modification de l'�venement du drag
	 * @param dge : l'�v�nement
	 */
	public void setDragEvent(EventObject dge);
	
	/**
	 * Modification des coordonn�es du curseur par rapport au tas de cartes
	 * @param cursorLocation : coordonn�es du point
	 */
	public void setCursorLocation(Point cursorLocation);
}