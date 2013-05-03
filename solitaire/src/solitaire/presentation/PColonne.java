package solitaire.presentation;

import java.awt.Color;
import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import solitaire.controle.CColonne;
import solitaire.listener.ComposantTransferable;
import solitaire.listener.TDCDragListener;
import solitaire.listener.TDCDropListener;

/**
 * Présentation d'une colonne
 */
public class PColonne extends JPanel implements IPDragable, IPDropable{
	/**
	 * Controleur de la colonne
	 */
	private CColonne controle;
	
	int decalage = 25;		//Décalage des cartes de la colonne

	/**
	 * Tas des cartes de la colonne
	 */
	private PTasDeCartes cachees, visibles;
	
	/**
	 * Attributs de gestion du drag&drop
	 */
	private DragSource source;
	private EventObject event;

	private Point cursorLocation;		//coordonnées du curseur par rapport au tas de cartes
	private PTasDeCartes tdcDND;		//Tas de cartes concerné par le drag and drop
	private DropTarget target;

	/**
	 * Contructeur de la présentation de la colonne
	 * @param cachees : présentation du tas de cartes cachées de la colonne
	 * @param visibles: présentation du tas de cartes visibles de la colonne
	 * @param c : controleur de la colonne
	 */
	public PColonne(PTasDeCartes cachees, PTasDeCartes visibles, CColonne c) {
		this.cachees = cachees;
		this.visibles = visibles;

		controle = c;
		init();
	}

	/**
	 * Retourne la carte au sommet du tas de cartes cachées et la place sur le tas de cartes alternées
	 * @throws Exception
	 */
	public void retournerCarte() throws Exception {
		int count = cachees.getComponentCount();
		visibles.setLocation(cachees.dx*count, cachees.dy*count);
		repaint();
	}

	/**
	 * Recopie d'un tas de cartes dans le tas de cartes cachées de la colonne
	 * @param presentation : présentation du tas de cartes à mettre dans la reserve
	 * @throws Exception 
	 */
	public void setReserve(PTasDeCartes presentation) throws Exception {
		repaint();
	}

	/**
	 * Getter du controle du sabot
	 * @return le controle du sabot
	 */
	public CColonne getControle() {
		return controle;
	}

	/**
	 * Initialisation de la colonne
	 */
	private void init(){
		setLayout(null);
		setSize(PCarte.largeur, PCarte.hauteur+16*decalage);
		setPreferredSize(getSize());
		setOpaque(false);

		//decalage entre chaques cartes des tas de cartes
		cachees.setDxDy(0, decalage/2);
		visibles.setDxDy(0, decalage);
		
		//Indiquer visuellement qu'il y a un tas de cartes même lorsqu'il n'y a plus de cartes dedans
		cachees.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		//Permet l'affichage correct du tas de carte cachées lorsqu'il n'y a plus de cartes visibles
		visibles.setOpaque(false);

		this.add(visibles);
		this.add(cachees);

		//listeners du drag&drop
		setDragable(visibles);
		setDropable();
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
		
		//Initialisation des paramètres du tas de cartes et actualisation à l'affichage
		tdc.setDxDy(0, decalage);
		tdc.resize();
		
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