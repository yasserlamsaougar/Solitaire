package solitaire.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import solitaire.controle.CSolitaire;

/**
 * Présentation d'un solitaire
 */
public class PSolitaire extends JFrame{

	/**
	 * Presentation du solitaire
	 */
	private CSolitaire controle;
	private JPanel sabot;
	private JPanel cont_colonnes;
	private JPanel cont_TasCColorees;

	/**
	 * Constructeur du Solitaire
	 */
	public PSolitaire(CSolitaire controle, PSabot sabot, List<PColonne> contCol, List<PTasDeCartes> contTCC) {
		this.controle = controle;
		this.sabot = sabot;
		
		JPanel contPCol = new JPanel();
		for(PColonne c : contCol)
			contPCol.add(c);
		
		JPanel contPTCC = new JPanel();
		for(PTasDeCartes c : contTCC)
			contPTCC.add(c);
		
		this.cont_colonnes = contPCol;
		this.cont_TasCColorees = contPTCC;
		
		initComponents();
		initFrame();
	}

	/**
	 * initialise la fenetre de solitaire
	 */
	private void initFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Solitaire");
		setResizable(false);
		getContentPane().setBackground(new Color(0, 100, 0));
		
		//Ajout des différents éléments du solitaire
		getContentPane().add(sabot, BorderLayout.NORTH);
		getContentPane().add(cont_colonnes, BorderLayout.CENTER);
		getContentPane().add(cont_TasCColorees, BorderLayout.EAST);
		pack();

		//Affichage de la fenetre au centre de l'écran
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) (tailleEcran.getWidth()-getWidth())/2,(int) (tailleEcran.getHeight()-getHeight())/2);
		
		setVisible(true);
	}

	/**
	 * initialise les composants de solitaire
	 */
	private void initComponents()
	{
		//initialisation du conteneur des colonnes
		cont_colonnes.setLayout(new FlowLayout(FlowLayout.LEFT));
		cont_colonnes.setSize(new Dimension(PCarte.largeur*7+45, 0));
		cont_colonnes.setPreferredSize(cont_colonnes.getSize());
		cont_colonnes.setOpaque(false);

		//initialisation du conteneur des tas de cartes colorees<
		cont_TasCColorees.setLayout(new GridLayout(4,1));
		cont_TasCColorees.setSize(new Dimension(PCarte.largeur+10, PCarte.hauteur*4+50));
		cont_TasCColorees.setPreferredSize(cont_TasCColorees.getSize());
		cont_TasCColorees.setOpaque(false);
	}

	/**
	 * Getter du controle du solitaire
	 * @return le controle du solitaire
	 */
	public CSolitaire getControle() {
		return controle;
	}
}
