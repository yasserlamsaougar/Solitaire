package solitaire.controle;

import solitaire.application.Tas;
import solitaire.presentation.PTasDeCartes;

public interface ICTasDeCartes extends Tas{
	/**
	 * Getter de la presentation
	 * @return p : retourne la presentation du tas de cartes
	 */
	public PTasDeCartes getPresentation();
}
