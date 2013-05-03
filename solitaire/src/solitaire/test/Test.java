/*
 * Created on 31 janv. 2005
 *
 */
package solitaire.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import solitaire.application.Usine;
import solitaire.controle.CCarte;
import solitaire.controle.CColonne;
import solitaire.controle.CSabot;
import solitaire.controle.CSolitaire;
import solitaire.controle.CTasDeCartes;
import solitaire.presentation.PCarte;
import solitaire.usine.CUsine;

/**
 * @author engel test des différents objets visuel
 */
public class Test extends JFrame {
	public static final Usine u = new CUsine();

	public static void main(String[] args) {
		testSolitaire();
		/*
		Test jt = new Test();

		//testPCarte(jt, "Test PCarte");
		//testCCarte(jt, "Test CCarte");
		//testTasDeCartes(jt, "Test TasDeCartes");
		//testColonne(jt, "Test colonne");
		//testSabot(jt, "Test sabot");
		

		// taille de la fenêtre
		jt.pack();

		// centrer la fenêtre dans l'écran
		Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
		Point origine = new Point((dimEcran.width - jt.getWidth())/2,
				(dimEcran.height - jt.getHeight())/2);
		jt.setLocation(origine.x, origine.y);
		jt.setVisible(true);*/
	}

	/**
	 * constructeur ; initialise le panneau interne
	 */
	public Test() {

		// fermeture
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// panneau du plan de jeu
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(163, 163, 215)); // mauve pâle
	}
	/**
	 * test des Cartes
	 * 
	 * @param fenetre où afficher les cartes
	 * @param titre de la fenêtre ci-dessus
	 */
	public static void testPCarte(Test fenetre, String titre) {
		fenetre.setTitle(titre);

		// une carte visible
		PCarte pc = new PCarte ("QH", null);
		pc.setFaceVisible(true);
		pc.setLocation(20, 20);
		fenetre.getContentPane().add(pc) ;

		// une carte cachée
		pc = new PCarte("1D", null);
		pc.setFaceVisible(false);
		pc.setLocation(40 + PCarte.largeur, 20);
		fenetre.getContentPane().add(pc) ;

		// taille du conteneur
		Dimension d = new Dimension(2 * PCarte.largeur + 60, PCarte.hauteur + 40);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}

	public static void testCCarte(Test fenetre, String titre) {
		fenetre.setTitle(titre);

		// une carte visible
		CCarte pc = (CCarte) u.newCarte(12,2);
		PCarte pp = pc.getPresentation();
		pc.setFaceVisible(true);
		pp.setLocation(20, 20);
		fenetre.getContentPane().add(pp) ;

		// une carte cachée
		pc = (CCarte) u.newCarte(10, 3);
		pp = pc.getPresentation();
		pc.setFaceVisible(false);
		pp.setLocation(40 + PCarte.largeur, 20);
		fenetre.getContentPane().add(pp) ;

		// taille du conteneur
		Dimension d = new Dimension(2 * PCarte.largeur + 60, PCarte.hauteur + 40);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}

	public static void testTasDeCartes(Test fenetre, String titre) {
		fenetre.setTitle(titre);

		CTasDeCartes tas = (CTasDeCartes) u.newTasDeCartes("tas", u);

		// une carte visible
		CCarte pc = (CCarte) u.newCarte(12,2);
		pc.setFaceVisible(true);
		tas.empiler(pc) ;

		// une carte cachée
		pc = (CCarte) u.newCarte(10, 3);
		pc.setFaceVisible(false);
		tas.empiler(pc) ;

		// une carte visible
		pc = (CCarte) u.newCarte(8,4);
		pc.setFaceVisible(true);
		tas.empiler(pc) ;

		// taille du conteneur
		fenetre.add(tas.getPresentation());
		Dimension d = new Dimension(200,400);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}

	private static void testColonne(Test fenetre, String titre) {
		fenetre.setTitle(titre);

		CColonne colonne = (CColonne) u.newColonne("Col", u);
		CTasDeCartes tas = (CTasDeCartes) u.newTasDeCartes("tas", u);

		// une carte visible
		CCarte pc = (CCarte) u.newCarte(12,2);
		tas.empiler(pc) ;

		pc = (CCarte) u.newCarte(10, 3);
		tas.empiler(pc);

		pc = (CCarte) u.newCarte(9,4);
		tas.empiler(pc) ;
		
		pc = (CCarte) u.newCarte(8,3);
		tas.empiler(pc) ;
		
		pc = (CCarte) u.newCarte(7,2);
		tas.empiler(pc) ;
		
		pc = (CCarte) u.newCarte(6,1);
		tas.empiler(pc) ;

		colonne.setReserve(tas);
		colonne.retournerCarte();		
		
		// taille du conteneur
		fenetre.add(colonne.getPresentation());
		Dimension d = new Dimension(800,400);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}
	private static void testSabot(Test fenetre, String titre) {
		fenetre.setTitle(titre);

		CSabot sabot = (CSabot) u.newSabot("Col", u);
		CTasDeCartes tas = (CTasDeCartes) u.newTasDeCartes("tas", u);

		// une carte visible
		CCarte pc = (CCarte) u.newCarte(12,2);
		tas.empiler(pc) ;

		pc = (CCarte) u.newCarte(10, 3);
		tas.empiler(pc);

		pc = (CCarte) u.newCarte(9,4);
		tas.empiler(pc) ;
		
		pc = (CCarte) u.newCarte(8,3);
		tas.empiler(pc) ;
		
		pc = (CCarte) u.newCarte(7,2);
		tas.empiler(pc) ;
		
		pc = (CCarte) u.newCarte(6,1);
		tas.empiler(pc) ;

		sabot.setReserve(tas);
		try {
			sabot.retournerCarte();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// taille du conteneur
		fenetre.add(sabot.getPresentation());
		Dimension d = new Dimension(800,400);
		fenetre.getContentPane().setSize(d);
		fenetre.getContentPane().setPreferredSize(d);
	}
	
	private static void testSolitaire() {
		CSolitaire solitaire = (CSolitaire) u.newSolitaire("",u);
	}
}
