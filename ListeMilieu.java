/**
 * Represente une liste chaînée dont les éléments seront maintenus triés
 *
 * @param <E> Le type des elements places dans la <code>Liste</code>.
 */
public class ListeMilieu<E extends Comparable<E>> implements Comparable<ListeMilieu<E>> {

    private ListeChainee<E> superieure;
    private ListeChainee<E> inferieure;


    /**
     * Construie une <code>ListeMilieu</code>
     */
    public ListeMilieu() {
        this.superieure = new ListeChainee<>();
        this.inferieure = new ListeChainee<>();
    }

    /**
     * crée une nouvelle <code>ListeMileu</code>
     *
     * @return
     */
    public ListeMilieu<E> diviser() {
        var result = new ListeMilieu<E>();

        result.setInferieure(this.getSuperieure().getInferieur());
        result.setSuperieure(this.getSuperieure().getSuperieure());

        this.inferieure.inverser();
        this.superieure = this.getInferieure().getSuperieure();
        this.inferieure = this.getInferieure().getInferieur();
        return result;
    }


    /**
     * Ajoute une valeur dans la bonne place du <code>ListeMilieu</code>
     *
     * @param valeur
     */
    public void inserer(E valeur) {
        if (taille() == 0 || !trouver(valeur)) {
            if (this.inferieure.getTaille() == 0) {
                this.inferieure.insererDebut(valeur);
            } else {
                this.inferieure.insererInverse(valeur);
            }
            verifierInvariant();

        }
    }

    /**
     * Vérifie les invariants
     */
    private void verifierInvariant() {
        E temp;
        E milieu;
        if (!invariant() && this.superieure.getTaille() < this.inferieure.getTaille()) {
            this.superieure.inserer(this.inferieure.getTete().getValeur());
            this.inferieure.supprimer(this.inferieure.getTete().getValeur());
        } else if (this.superieure.getTaille() > this.inferieure.getTaille()) {
            this.inferieure.insererDebut(this.superieure.getTete().getValeur());
            this.superieure.supprimer(this.superieure.getTete().getValeur());
        }
        if (this.superieure.getTaille() > 0) {
             temp = this.superieure.getTete().getValeur();
             milieu = milieu();
            if (temp.compareTo(milieu) < 0) {
                this.superieure.supprimer(temp);
                this.inferieure.supprimer(milieu);
                this.superieure.inserer(milieu);
                this.inferieure.insererInverse(temp);
            }
        }
    }

    /**
     * Vérifie  la taille de la liste <code>inferieure</code> par rapport a la liste <code>superieure</code>
     *
     * @return {@code true} La taille de la liste inférieure est égale à la taille de la liste supérieure ou contient une valeur de
     * plus.
     */
    private boolean invariant() {
        return this.inferieure.getTaille() - 1 == this.superieure.getTaille()
                || this.inferieure.getTaille() == this.superieure.getTaille();
    }

    /**
     * Retourne la premiere valeur de la liste <code>inferieure</code>
     *
     * @return la premiere valeur de la liste inferieure
     */
    public E milieu() {
        E valeur = null;
        if (this.inferieure.getTaille() > 0) {
            valeur = this.inferieure.getTete().getValeur();

        }
        return valeur;
    }

    /**
     * Retourne la derniere valeur de la liste <code>inferieure</code>
     *
     * @return la derniere valeur de la liste inferieure
     */
    public E minima() {
        E valeur;
        if (this.inferieure.getTaille() != 0)
            valeur = inferieure.getFin().getValeur();
        else
            valeur = superieure.getTete().getValeur();
        return valeur;
    }

    /**
     * Retourne la derniere valeur de la liste <code>superieure</code> sinon la premiere de la liste <code>inferieure</code>
     *
     * @return la derniere valeur de la liste superieure sinon la premiere de la liste inferieure
     */
    public E maxima() {
        E valeur;
        if (this.superieure.getTaille() != 0)
            valeur = superieure.getFin().getValeur();
        else
            valeur = inferieure.getTete().getValeur();
        return valeur;
    }

    /**
     * Supprime la premiere occurence de la <code>ListeMilieu</code>
     *
     * @param valeur la valeur a supprimer
     */
    public void supprimer(E valeur) {
        if (this.taille() != 0) {
            if (valeur.compareTo(milieu()) > 0) {
                this.superieure.supprimer(valeur);
            } else {
                this.inferieure.supprimer(valeur);
            }
            verifierInvariant();
        }
    }

    /**
     * Cherche une valeur dans la <code>ListeMilieu</code>
     *
     * @param valeur la valeur a chercher
     * @return {@code true} la valeur a été trouvé
     */
    public boolean trouver(E valeur) {
        boolean trouver = false;
        if (valeur.compareTo(milieu()) <= 0) {
            if (inferieure.existe(valeur))
                trouver = true;
        } else {
            if (superieure.existe(valeur))
                trouver = true;
        }
        return trouver;
    }


    /**
     * Retourne la nombre de valeur dans la <code>ListeMilieu</code>
     *
     * @return la taille de la ListeMilieu
     */
    public int taille() {
        return this.inferieure.getTaille() + this.superieure.getTaille();
    }

    /**
     * Retourne la liste <code>superieure</code>
     *
     * @return la liste chainne superieure
     */
    public ListeChainee<E> getSuperieure() {
        return superieure;
    }

    /**
     * Modifie la liste <code>superieure</code>
     *
     * @param superieure la nouvelle liste chainne superieure
     */
    public void setSuperieure(ListeChainee<E> superieure) {
        this.superieure = superieure;
    }

    /**
     * Retourne la liste <code>Inferieure</code>
     *
     * @return la liste chainne inferieure
     */
    public ListeChainee<E> getInferieure() {
        return inferieure;
    }

    /**
     * Modifie la liste <code>inferieure</code>
     *
     * @param inferieure la nouvelle liste chainne inferieure
     */
    public void setInferieure(ListeChainee<E> inferieure) {
        this.inferieure = inferieure;
    }


    @Override
    public int compareTo(ListeMilieu<E> o) {
        ListeMilieu<E> listToCompare = (ListeMilieu<E>) o;
        return minima().compareTo(listToCompare.maxima());
    }


}