package solitaire.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import solitaire.presentation.PSabot;

/**
 * Listener d'un click sur un tas de cartes 
 */
public class CacheesListener extends MouseAdapter{
	PSabot sabot;
	boolean onCachees;
	
	/**
	 * Constructeur du listener
	 * @param sabot : sabot concerné par le listener
	 */
	public CacheesListener(PSabot sabot) {
		this.sabot = sabot;
		onCachees = false;
	}
	
	/**
	 * Active la possibilité de l'évenement
	 */
	public void mousePressed(MouseEvent e) {
		onCachees = true;
	}
	
	/**
	 * Désactive la possibilité de l'évenement
	 */
	public void mouseExited(MouseEvent e) {
		onCachees = false;
	}
	
	/**
	 * Appel du controle du sabot pour gérer l'évenemnt
	 * @pre la possibilité e 'évenement a été activé
	 */
	public void mouseReleased(MouseEvent e) {
		if(onCachees)
			sabot.getControle().click();
	}
}
