/**
 * Écrivez vos nom ici :
 *
 * @HAMZA MAAZAOUI
 * @MAAH08069405
 * @WISAAL EDDASSI
 * @EDDW02619802
 */


/**
 * Represente une structure qui va gérer une suite de liste <code>ListeMilieu</code>
 *
 * @param <E> Le type des elements places dans la <code>Liste</code>.
 */
public class ListeIndex<E extends Comparable<E>> {

    private int nbrListe;
    private ListeChainee<ListeMilieu<E>> list;

    /**
     * Construit une <code>ListeIndex</code>
     */
    public ListeIndex() {
        this.nbrListe = 0;
        this.list = new ListeChainee<>();
    }

    /**
     * Vérifie si la valeur existe dans une des <code>ListeMilieu</code>
     *
     * @param valeur la valeur a chercher
     * @return {@code true} la valeur a été bien trouvé
     */
    public boolean contient(E valeur) {
        var p = this.list.getTete();
        boolean trouver = false;
        while (p != null && !trouver) {
            if (p.getValeur().trouver(valeur))
                trouver = true;
            p = p.getSuivant();
        }
        return trouver;
    }

    /**
     * Retourne la <code>ListeMilieu</code>
     *
     * @param i L'indice de l'index
     * @return La valeur du listeMilieu
     */
    public ListeMilieu<E> get(int i) {
        int j = 0;
        var liste = this.list.getTete();
        while (j < i) {
            ++j;
            liste = liste.getSuivant();
        }
        return liste.getValeur();
    }

    /**
     * Ajoute la valeur a la bonne <code>ListeMilieu</code>
     *
     * @param valeur la valeur a ajouter
     */
    public void inserer(E valeur) {
        boolean inseree = false;
        ListeMilieu<E> listeMilieu;
        ListeMilieu<E> listeMilieuSuivant;
        if (this.nbrListe == 0) {
            ajoutNouvelleListe(valeur);
        } else {
            if (this.nbrListe == 1 || (this.nbrListe > 1 && valeur.compareTo(get(1).minima()) < 0)) {
                get(0).inserer(valeur);
                verifierPremierInvariant(get(0));
            } else if (this.nbrListe != 1 && valeur.compareTo(get(this.nbrListe - 1).minima()) >= 0) {
                get(this.nbrListe - 1).inserer(valeur);
                verifierPremierInvariant(get(this.nbrListe - 1));
            } else {

                for (int i = 1; i < nbrListe && !inseree; i++) {
                    listeMilieu = get(i);
                    listeMilieuSuivant = get(i + 1);
                    if (valeur.compareTo(listeMilieu.minima()) > 0 && valeur.compareTo(listeMilieuSuivant.minima()) <= 0) {
                        listeMilieu.inserer(valeur);
                        verifierPremierInvariant(listeMilieu);
                        inseree = true;
                    }
                }
                if (!inseree) {
                    ajoutNouvelleListe(valeur);
                }
            }
        }
    }

    /**
     * ajoute la valeur dans une nouvelle <code>ListeMilieu</code>
     *
     * @param valeur la valeur a ajouter
     */
    private void ajoutNouvelleListe(E valeur) {
        ListeMilieu<E> nouvelleListe = new ListeMilieu<>();
        nouvelleListe.inserer(valeur);
        this.list.inserer(nouvelleListe);
        ++this.nbrListe;
    }

    /**
     * Verifie le nombre de valeur de chaque <code>ListeMilieu</code> par rapport a la <code>ListeMilieu</code> dans l'index
     *
     * @param eListeMilieu la ListeMilieu dans l'index
     */
    private void verifierPremierInvariant(ListeMilieu<E> eListeMilieu) {
        if (eListeMilieu.taille() > 2 * nbrListe)
            add(eListeMilieu.diviser());
    }

    private void add(ListeMilieu<E> list) {
        this.list.inserer(list);
        ++this.nbrListe;
    }

    /**
     * Retourne le nombre des <code>ListeMilieu</code>
     *
     * @return le nombre des ListeMileu
     */
    public int nbrListe() {
        return this.nbrListe;
    }

    /**
     * Supprime la preimere occuorence de la valeur dans la <code>ListeMilieu</code> trouvé
     *
     * @param valeur la valeur a supprimer
     */
    public void supprimer(E valeur) {
        boolean trouver = false;
        if (this.taille() != 0) {
            if (this.nbrListe >= 2 && valeur.compareTo(get(1).minima()) < 0) {
                get(0).supprimer(valeur);
            } else if (valeur.compareTo(get(this.nbrListe - 1).minima()) >= 0) {
                get(this.nbrListe - 1).supprimer(valeur);
            } else {
                for (int i = 1; i < nbrListe - 1 && !trouver; i++) {
                    if (valeur.compareTo(get(i).minima()) >= 0 && valeur.compareTo(get(i + 1).minima()) < 0) {
                        get(i).supprimer(valeur);
                        trouver = true;
                    }
                }
            }
        }
    }

    /**
     * Retourne la somme de toules les tailles des <code>ListeMileu</code>
     *
     * @return la somme des ListeMilieu
     */
    public int taille() {
        int sum = 0;
       Chainon<ListeMilieu<E>> p = this.list.getTete();
        while (p != null) {
            sum += p.getValeur().taille();
            p = p.getSuivant();
        }
        return sum;
    }

}