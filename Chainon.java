/**
 * Cree Chainon de la liste
 *
 * @param <E> Le type des elements places dans la <code>Liste</code>.
 */
public class Chainon<E extends Comparable<E>> {
    private E valeur;
    private Chainon<E> suivant;

    /**
     * Construit un <code>Chainon</code> avec un seul parametre
     *
     * @param valeur la valeur qui sera stockee dans le chainon
     */
    public Chainon(E valeur) {
        this.valeur = valeur;
    }

    /**
     * Construit un <code>Chainon</code> avec les deux parametres
     *
     * @param valeur  la valeur qui sera stockee dans le chainon
     * @param suivant le chainon qui sera le suivant du chainon cree
     */
    public Chainon(E valeur, Chainon<E> suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }

    /**
     * Construit un <code>Chainon</code> vide.
     */
    public Chainon() {

    }

    /**
     * Retourne la valeur du chainon
     *
     * @return la valeur
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Modifie la valeur du chainon
     *
     * @param valeur la nouvelle valeur
     */
    public void setValeur(E valeur) {
        this.valeur = valeur;
    }

    /**
     * Retourne la chainon suivant
     *
     * @return l'adresse du chainon suivant
     */
    public Chainon<E> getSuivant() {
        return suivant;
    }

    /**
     * Modifie l'adresse du chainon suivant
     *
     * @param suivant la nouvelle adresse du chainon suivant
     */
    public void setSuivant(Chainon<E> suivant) {
        this.suivant = suivant;
    }

}