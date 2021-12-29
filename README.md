# TP3: TDA et chainage

Ce TP est pour construire un type de données. Ce type de données est un contenant
homogène trié à taille variable. Ce contenant va utiliser des chainages pour obtenir une taille variable.
Afin d’accélérer la recherche dans le contenant, nous allons inclure un index qui permet de retrouver les
éléments plus rapidement. Cette structure va avoir des méthodes (services) pour insérer, supprimer et
consulter les éléments. Les techniques utilisées pour l’index vont nous permettre d’obtenir des temps
 𝑂(√𝑛𝑛), ce qui est meilleur que 𝑂(𝑛𝑛) mais moins performant que 𝑂(log 𝑛𝑛).
