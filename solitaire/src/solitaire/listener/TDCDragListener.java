package solitaire.listener;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.dnd.DropTarget;
import java.io.IOException;

import solitaire.presentation.IPDragable;
import solitaire.presentation.PCarte;
import solitaire.presentation.PTasDeCartes;

/**
 * Listener d'un drag de tas de cartes
 */
public class TDCDragListener implements DragGestureListener, DragSourceListener, DragSourceMotionListener{
	IPDragable dragable;

	/**
	 * Constructeur du listener
	 * @param dragable : tas dragable
	 */
	public TDCDragListener(IPDragable dragable) {
		this.dragable = dragable;
	}

	/**
	 * Démarrage du processus de drag&drop avec appel des méthodes adéquates
	 * @param dge : l'événement reçu
	 */
	public void dragGestureRecognized(DragGestureEvent dge){
		try{
			PCarte p = (PCarte) dge.getComponent().getComponentAt(dge.getDragOrigin());

			//Calcul des coordonnées du curseur par rapport au tas de cartes
			dragable.setCursorLocation(new Point((int)(dge.getDragOrigin().getX()-p.getLocation().getX()), 
					(int)(dge.getDragOrigin().getY()-p.getLocation().getY())));
		
			dragable.setDragEvent(dge);
			dragable.getControle().debutDND(p.getControle());
		}catch(ClassCastException e){}	//S'il n'y a plus de carte dans le tas dragable, on annule le démarrage du drag&drop
	}

	/**
	 * Finalisation du processus de drag&drop avec appel de la méthode adéquate
	 */
	public void dragDropEnd(DragSourceDropEvent dsde) {			
		try {
			DataFlavor data = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
			PTasDeCartes tdc = (PTasDeCartes) dsde.getDragSourceContext().getTransferable().getTransferData(data);

			dragable.getControle().finDND(dsde.getDropSuccess(), tdc.getControle());
		} catch (UnsupportedFlavorException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Traitement du déplacement lors du drag&drop
	 */
	public void dragMouseMoved(DragSourceDragEvent dsde) {
		dragable.deplacementDND(dsde.getLocation());
	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {}

	@Override
	public void dragExit(DragSourceEvent dse) {}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {}
}