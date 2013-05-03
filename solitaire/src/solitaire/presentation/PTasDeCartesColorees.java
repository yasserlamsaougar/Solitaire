package solitaire.presentation;

import java.awt.Color;
import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import solitaire.controle.CTasDeCartesColorees;
import solitaire.controle.ICTasDeCartes;
import solitaire.listener.ComposantTransferable;
import solitaire.listener.TDCDragListener;
import solitaire.listener.TDCDropListener;


public class PTasDeCartesColorees extends PTasDeCartes implements IPDragable, IPDropable{
	/**
	 * Image de fond lorsque le tas est vide
	 */
	private JLabel imageFond;
	
	/**
	 * Attributs de gestion du drag&drop
	 */
	private DragSource source;
	private EventObject event;

	private Point cursorLocation;		//coordonnées du curseur par rapport au tas de cartes
	private PTasDeCartes tdcDND;		//Tas de cartes concerné par le drag and drop
	private DropTarget target;
	
	/**
	 * Constructeur de la presentation  du tas de cartes
	 * Bénéficie de l'héritage de PTasDeCartes et ajoute la fonctionnalité de drag&drop
	 * @param controle : Controleur du tas de cartes
	 * @param couleur 
	 */
	public PTasDeCartesColorees(ICTasDeCartes controle, int couleur) {
		super(controle);

		init(couleur);
	}
	
	/**
	 * Getter du controleur du tas de cartes
	 * @return le controleur du tas de carte
	 */
	public CTasDeCartesColorees getControle() {
	    return (CTasDeCartesColorees) super.getControle();
	}

	/**
	 * Initialisation de la JPanel 
	 */
	private void init(int couleur){		
		//Initialisation de l'image de fond des tas de cartes colorées
		imageFond = new JLabel(new ImageIcon(ClassLoader.getSystemResource("cartesCSHD/0" + getCouleur(couleur) + ".gif")));
		add(imageFond);
		
		imageFond.setSize(PCarte.largeur, PCarte.hauteur);
		imageFond.setBackground(Color.yellow);
		
		
		//listeners du drag&drop
		setDragable(this);
		setDropable();
	}
	
	/**
	 * Renvoie la lettre associé à la couleur du tas pour l'image de fond
	 * @param couleur : couleur du tas
	 * @return : la lettre représentant la couleur en anglais
	 */
	public char getCouleur(int couleur){
		switch(couleur){
			case 1 : 
				return 'D';
			case 2 : 
				return 'S';
			case 3 : 
				return 'H';
			default :
				return 'C';
		}
	}

	//////////////////////////////////méthodes de gestion du drag&drop//////////////////////////////////////////
	/**
	 * Rend possible un drag sur le composant
	 * @param tdcDragable : le tas de cartes dragable
	 */
	public void setDragable(PTasDeCartes tdcDragable) {
		source = new DragSource();
		TDCDragListener dragTDC = new TDCDragListener(this);
		
		source.createDefaultDragGestureRecognizer(tdcDragable, DnDConstants.ACTION_MOVE, dragTDC);
		source.addDragSourceListener(dragTDC);
		source.addDragSourceMotionListener(dragTDC);
	}

	/**
	 * Début du drag and drop
	 * @param tdc : tas de cartes à déplacer
	 */
	public void debutDNDOK(PTasDeCartes tdc){
		source.startDrag((DragGestureEvent) event, DragSource.DefaultMoveDrop, new ComposantTransferable(tdc), source.getDragSourceListeners()[0]);

		//Initialisation du déplacement
		tdcDND = tdc;
		getRootPane().add(tdcDND, 0);
	}

	/**
	 * Annulation du début du drag and drop
	 * @param tdc : tas de cartes à déplacer
	 */
	public void debutDNDKO(PTasDeCartes tdc) {
	}

	/**
	 * Affichage du déplacement lors du drag&drop
	 */
	public void deplacementDND(Point p) {
		tdcDND.setLocation((int) (p.getX()-getRootPane().getLocationOnScreen().getX()-cursorLocation.getX()),
				(int) (p.getY()-getRootPane().getLocationOnScreen().getY()-cursorLocation.getY()));
	}

	/**
	 * Traitement de fin d'un drag&drop réussi
	 * @param tdc : tas de cartes concerné par le drag&drop
	 */
	public void finDNDOK(PTasDeCartes tdc) {
		getRootPane().remove(tdc);
		getRootPane().repaint();
	}

	/**
	 * Traitement de fin d'un drag&drop raté
	 * @param tdc : tas de cartes concerné par le drag&drop
	 */
	public void finDNDKO(PTasDeCartes tdc) {
		getRootPane().remove(tdc);
		getRootPane().repaint();
	}

	/**
	 * Modification de l'évenement du drag
	 * @param dge : l'événement
	 */
	public void setDragEvent(EventObject dge) {
		this.event = dge;
	}

	/**
	 * Modification des coordonnées du curseur par rapport au tas de cartes
	 * @param cursorLocation : coordonnées du point
	 */
	public void setCursorLocation(Point cursorLocation) {
		this.cursorLocation = cursorLocation;
	}

	/**
	 * Rend possible un drop sur le composant
	 */
	public void setDropable() {
		TDCDropListener dropTDC = new TDCDropListener(this);		
		target = new DropTarget(this, DnDConstants.ACTION_MOVE, dropTDC);
	}

	/**
	 * Traitement du drop accepté
	 */
	public void dropOK(){
		((DropTargetDropEvent) event).acceptDrop(DnDConstants.ACTION_MOVE);
		target.getDropTargetContext().dropComplete(true);
		
		setOpaque(false);
	}

	/**
	 * Traitement du drop rejeté
	 */
	public void dropKO(){
		((DropTargetDropEvent) event).rejectDrop();
		
		setOpaque(false);
	}
	
	/**
	 * Affichage positif du drop éventuel sur le composant
	 */
	public void showEmpilable(){
		setBackground(Color.GREEN);
		setOpaque(true);
		getRootPane().repaint();
	}
	
	/**
	 * Affichage négatif du drop éventuel sur le composant
	 */
	public void showNotEmpilable(){
		setBackground(Color.RED);
		setOpaque(true);
		getRootPane().repaint();
	}
	
	/**
	 * Retour à l'affichage neutre sur le composant
	 */
	public void showNeutre(){
		setOpaque(false);
		getRootPane().repaint();
	}

	/**
	 * Modification de l'évenement du drop
	 * @param event : l'événement
	 */
	public void setDropEvent(EventObject event) {
		this.event = event;
	}
}