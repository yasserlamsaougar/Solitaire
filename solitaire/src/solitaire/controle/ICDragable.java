package solitaire.controle;

public interface ICDragable {
	/**
	 * Controle du d�but du drag&drop
	 * On d�place toujours des tasde cartes afin de conserver le m�me sch�ma pour tous les drag&drop
	 * @param c
	 */
	public void debutDND(CCarte c);
	
	/**
	 * Controle de fin du drag and drop
	 * @param success : bool�en indiquant le succ�s ou non du drag&drop
	 * @param tdc : le tas de carte concern� par le drag&drop
	 */
	public void finDND(boolean success, ICTasDeCartes tdc);
}
