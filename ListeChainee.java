/**
 * Represante la liste chainee
 *
 * @param <E> Le type des elements places dans la <code>Liste</code>.
 */
public class ListeChainee<E extends Comparable<E>> {
    private Chainon<E> tete;
    private int taille;

    /**
     * Construit une <code>ListeChainee</code> vide.
     */
    public ListeChainee() {
        this.taille = 0;
        this.tete = null;
    }

    /**
     * Insere le nouveau chainon a la derneire position de la <code>ListeChainee</code>.
     *
     * @param valeur la valeur a inserer.
     */
    public void insererChainon(E valeur) {
        Chainon<E> chainon = new Chainon<>(valeur);
        Chainon<E> courant = this.tete;
        if (this.tete == null) {
            this.tete = chainon;
            this.tete.setSuivant(null);
        } else {
            while (courant.getSuivant() != null) {
                courant = courant.getSuivant();
            }
            courant.setSuivant(chainon);
            chainon.setSuivant(null);
        }
        ++this.taille;
    }

    /**
     * Insere la valeur a la premiere position dans la <code>ListeChainee</code>.
     *
     * @param valeur la valeur a inserer.
     */
    public void insererDebut(E valeur) {
        this.tete = new Chainon<>(valeur, this.tete);
        ++this.taille;
    }

    /**
     * Inserer le chainon a la bonne place dans la <code>ListeChainee</code> .
     *
     * @param valeur la valeur a inserer.
     */
    public void inserer(E valeur) {
        if (this.tete == null)
            insererDebut(valeur);
        else {
            Chainon<E> chainon = new Chainon<>(valeur);
            Chainon<E> courant = this.tete;
            Chainon<E> precedant = this.tete;
            boolean estPremier = true;
            while (courant != null && valeur.compareTo(courant.getValeur()) > 0) {
                estPremier = false;
                precedant = courant;
                courant = courant.getSuivant();
            }
            chainon.setSuivant(courant);
            if (estPremier) {
                this.tete = chainon;
            } else
                precedant.setSuivant(chainon);
            ++this.taille;
        }
    }

    /**
     * Retourne la premiere moitié de la <code>ListeChainee</code>.
     *
     * @return la nouvelle liste inferieure chainee.
     */
    public ListeChainee<E> getInferieur() {
        ListeChainee<E> inferieur = new ListeChainee<>();
        Chainon<E> courant = this.tete;
        for (int i = 1; i <= Math.ceil(this.taille / 2.0); i++) {
            inferieur.insererDebut(courant.getValeur());
            courant = courant.getSuivant();
        }
        return inferieur;
    }

    /**
     * gRetourne la deuxieme moitié superieur de la <code>ListeChainee</code>.
     *
     * @return la nouvelle liste superiereure  chainee.
     */
    public ListeChainee<E> getSuperieure() {
        ListeChainee<E> superieur = new ListeChainee<>();
        Chainon<E> courant = this.tete;
        for (int i = 1; i <= Math.ceil(this.taille / 2.0); i++) {
            courant = courant.getSuivant();
        }
        for (int i = 1; i <= this.taille / 2; i++) {
            superieur.insererChainon(courant.getValeur());
            courant = courant.getSuivant();
        }
        return superieur;
    }

    /**
     * Supprime la premiere occurence dans la <code>ListeChainee</code>.
     *
     * @param valeur la valeur a supprimer.
     */
    public void supprimer(E valeur) {
        Chainon<E> courant = this.tete;
        Chainon<E> precedant = courant;

        boolean estTete = true;
        while (courant != null && courant.getValeur().compareTo(valeur) != 0) {
            precedant = courant;
            courant = courant.getSuivant();
            estTete = false;
        }
        if (courant != null && courant.getValeur().compareTo(valeur) == 0) {
            --this.taille;
            if (!estTete) precedant.setSuivant(courant.getSuivant());
            else this.tete = courant.getSuivant();
        }
    }

    /**
     * Retourne la premiere reference au premier element  de la<code>ListeChainee</code>.
     *
     * @return la reference
     */
    public Chainon<E> getTete() {
        return tete;
    }

    /**
     * Retourne la taille de la <code>ListeChainee</code>.
     *
     * @return la taille en entier
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Retourne le dernier element de la <code>ListeChainee</code>.
     *
     * @return le derneir element.
     */
    public Chainon<E> getFin() {
        Chainon<E> courant = this.tete;
        Chainon<E> precedant = new Chainon<E>();
        while (courant != null) {
            precedant = courant;
            courant = courant.getSuivant();
        }
        return precedant;
    }

    /**
     * Verifie si la valeur est dans la <code>ListeChainee</code>.
     *
     * @param valeur la valeur a cherccher.
     * @return {@code true} si la valeur existe dans la liste chainee.
     */
    public boolean existe(E valeur) {
        Chainon<E> current = this.tete;
        while (current != null && current.getValeur().compareTo(valeur) != 0)
            current = current.getSuivant();
        return current != null;
    }

    /**
     * Inverse une <code>ListeChainee</code>.
     */
    public void inverser() {
        ListeChainee<E> inverse = new ListeChainee<>();
        Chainon<E> courant = this.tete;
        while (courant != null) {
            inverse.insererDebut(courant.getValeur());
            courant = courant.getSuivant();
        }
        this.tete = inverse.getTete();
    }

    /**
     * Insere une valeur dans la <code>ListeChainee</code> inversee
     *
     * @param valeur la valeur a inserer
     */
    public void insererInverse(E valeur) {
        if (this.tete == null)
            insererDebut(valeur);
        else {
            Chainon<E> chainon = new Chainon<>(valeur);
            Chainon<E> courant = this.tete;
            Chainon<E> precedant = this.tete;
            boolean estPremier = true;
            while (courant != null && valeur.compareTo(courant.getValeur()) < 0) {
                estPremier = false;
                precedant = courant;
                courant = courant.getSuivant();
            }
            chainon.setSuivant(courant);
            if (estPremier) {
                this.tete = chainon;
            } else
                precedant.setSuivant(chainon);
            ++this.taille;
        }
    }
}