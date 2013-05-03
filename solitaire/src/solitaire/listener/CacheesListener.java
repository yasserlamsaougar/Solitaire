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
	 * @param sabot : sabot concern� par le listener
	 */
	public CacheesListener(PSabot sabot) {
		this.sabot = sabot;
		onCachees = false;
	}
	
	/**
	 * Active la possibilit� de l'�venement
	 */
	public void mousePressed(MouseEvent e) {
		onCachees = true;
	}
	
	/**
	 * D�sactive la possibilit� de l'�venement
	 */
	public void mouseExited(MouseEvent e) {
		onCachees = false;
	}
	
	/**
	 * Appel du controle du sabot pour g�rer l'�venemnt
	 * @pre la possibilit� e '�venement a �t� activ�
	 */
	public void mouseReleased(MouseEvent e) {
		if(onCachees)
			sabot.getControle().click();
	}
}
