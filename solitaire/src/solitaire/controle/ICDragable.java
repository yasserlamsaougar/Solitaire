package solitaire.controle;

public interface ICDragable {
	/**
	 * Controle du début du drag&drop
	 * On déplace toujours des tasde cartes afin de conserver le même schéma pour tous les drag&drop
	 * @param c
	 */
	public void debutDND(CCarte c);
	
	/**
	 * Controle de fin du drag and drop
	 * @param success : booléen indiquant le succès ou non du drag&drop
	 * @param tdc : le tas de carte concerné par le drag&drop
	 */
	public void finDND(boolean success, ICTasDeCartes tdc);
}
