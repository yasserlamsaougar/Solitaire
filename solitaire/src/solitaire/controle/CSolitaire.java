package solitaire.controle;

import java.util.ArrayList;
import java.util.List;

import solitaire.application.Solitaire;
import solitaire.application.Usine;
import solitaire.presentation.PColonne;
import solitaire.presentation.PSolitaire;
import solitaire.presentation.PTasDeCartes;
import solitaire.usine.CUsine;

/**
 * Controle d'un solitaire
 */
public class CSolitaire extends Solitaire {
	/**
	 * Usine de l'application
	 */
	public static final Usine u = new CUsine();
	
	/**
	 * presentation du jeu solitaire
	 */
	private PSolitaire presentation;

	/**
	 * Constructeur du controle du Solitaire
	 * @param nom : le nom du controleur
	 * @param u : l'Usine du controleur
	 */
	public CSolitaire(String nom, CUsine u) {
		super(nom, u);
		super.initialiser();
		
		//Création du PSolitaire
		List<PColonne> contCol = new ArrayList<PColonne>();
		for (int i = 0; i < 7; i++)
			contCol.add(((CColonne) pilesAlternees[i]).getPresentation());
		
		List<PTasDeCartes> contTCC = new ArrayList<PTasDeCartes>();
		for (int i = 0; i < 4; i++)
			contTCC.add(((CTasDeCartesColorees) pilesColorees[i]).getPresentation());
		
		CSabot cSabot = (CSabot)sabot;
		presentation = new PSolitaire(this, cSabot.getPresentation(), contCol, contTCC);
	}

	/**
	 * Retourne la présentation du controle
	 * @return la présentation
	 */
	public PSolitaire getPresentation() {
		return presentation;
	}
	
	/**
	 * Lancement du solitaire
	 */
	public static void main(String[] args) {
		Solitaire sol = u.newSolitaire("Solitaire",u);
		sol.run();
	}
}
