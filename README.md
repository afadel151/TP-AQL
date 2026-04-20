# TP-AQL

## Vue d'ensemble
Ce dépôt contient les travaux pratiques pour TP1 (Tests Unitaires avec Couverture) et TP3 (Tests d'Intégration avec Docker et Testcontainers).

---

## TP1 - Tests Unitaires avec Couverture

### Questions et Réponses

**Q : Les différents critères de couverture (ligne, branche, condition) produisent-ils les mêmes tests pour le même exercice ?**

R : Pour l'exercice 1 (Palindrome) et l'exercice 2 (Anagramme), les différents critères de couverture ont produit des tests similaires mais pas identiques :
- Les tests de couverture de lignes se concentrent sur l'exécution de toutes les lignes de code
- Les tests de couverture de branches s'assurent que toutes les branches de décision (if/else) sont prises
- Les tests de couverture de conditions s'assurent que toutes les conditions booléennes sont évaluées à la fois vrai et faux

Bien qu'il y ait chevauchement, chaque critère nécessite des cas de test spécifiques pour atteindre une couverture complète. Par exemple :
- La couverture de lignes peut ne pas distinguer les différentes façons dont une branche peut être prise
- La couverture de branches assure que les chemins vrai et faux sont exécutés
- La couverture de conditions est plus granulaire, testant les conditions individuelles dans les expressions booléennes complexes

### Bugs Trouvés et Corrigés

#### Exercice 1 - Palindrome
**Bug 1 : Index hors limites**
- Le code original du manuel avait `j++` et `i--` dans la boucle while, ce qui ferait déplacer les indices dans la mauvaise direction
- **Correction** : Changé en `i++` et `j--` pour déplacer correctement les indices l'un vers l'autre

#### Exercice 2 - Anagramme
**Bug 1 : Chaînes vides résultent en Index 0 hors limites pour une longueur de 0**
- Le code original ne gérait pas les chaînes vides, causant une IndexOutOfBoundsException
- **Correction** : Ajouté une vérification des chaînes vides avant le traitement

**Bug 2 : Anagrammes résultent en Index hors limites**
- Le code original utilisait `i <= s1.length()` dans la condition de la boucle for, ce qui accéderait à un index au-delà de la longueur de la chaîne
- **Correction** : Changé en `i < s1.length()` pour rester dans les indices valides

#### Exercice 3 - Recherche Binaire
**Bug : Tableaux à un seul élément non gérés correctement**
- Le code original utilisait `low < high` dans la condition de la boucle while, ce qui échouerait pour les tableaux à un seul élément
- **Correction** : Changé en `low <= high` pour gérer correctement les tableaux à un seul élément

#### Exercice 5 - Chiffres Romains
**Bug : ArrayIndexOutOfBoundsException lors de l'accès au tableau de symboles**
- Le code original utilisait `i <= symbols.length` dans la condition de la boucle for, ce qui accéderait au tableau hors limites
- **Correction** : Changé en `i < symbols.length` pour éviter d'accéder au tableau hors limites

#### Exercice 6 - FizzBuzz
**Bug : La valeur d'entrée 1 est traitée incorrectement comme invalide**
- Le code original utilisait `n <= 1` dans la condition, ce qui rejetterait 1 comme invalide
- **Correction** : Changé en `n < 1` puisque 1 est une entrée valide qui devrait retourner "1"

### Statut d'Achèvement
- Exercices Complétés : 6/6 (Palindrome, Anagramme, Recherche Binaire, Équation Quadratique, Chiffres Romains, FizzBuzz)
- Tous les tests de couverture créés : Oui (Couverture de lignes, de branches et de conditions pour chaque exercice)
- Bugs documentés dans TP1/README.md : Oui

---

## TP3 - Tests d'Intégration

### Partie 1 : Tests Unitaires avec Mocks

#### Questions et Réponses

**Q : Pourquoi les implémentations utilisent-elles Spring Boot et JPA au lieu d'interfaces simples ?**

R : L'implémentation actuelle utilise Spring Boot avec des dépôts Spring Data JPA, ce qui fournit une approche plus réaliste et prête pour la production. Cependant, cela diffère de la exigence du manuel pour des interfaces simples et des tests unitaires basés sur des mocks. Les dépôts JPA sont plus complexes mais offrent une meilleure intégration avec les opérations de base de données.

**Q : Y a-t-il des tests unitaires avec mocks comme requis par le manuel ?**

R : Oui, les implémentations requises avec des mocks Mockito pour :
- UserService avec mock UserRepository
- OrderController avec mocks OrderService et OrderDao
- ProductService avec mock ProductApiClient (y compris les scénarios d'échec d'API)

ont été implémentés.

### Partie 2 : Docker et Testcontainers

#### Questions et Réponses

**Q : Quels sont les avantages et inconvénients de l'utilisation de Testcontainers pour les tests d'intégration ?**

**Avantages :**
- **Isolation** : Chaque test s'exécute dans son propre conteneur, assurant aucune interférence entre les tests
- **Environnement réaliste** : Les tests s'exécutent contre des instances de base de données réelles (ex: MySQL) plutôt que des alternatives en mémoire
- **Gestion automatique du cycle de vie** : Les conteneurs sont automatiquement démarrés avant les tests et arrêtés après
- **Reproductibilité** : Même version et configuration de base de données sur différents environnements
- **Portabilité** : Les tests peuvent s'exécuter partout où Docker est disponible

**Inconvénients :**
- **Exécution plus lente** : Le démarrage/arrêt des conteneurs ajoute une surcharge par rapport aux bases de données en mémoire
- **Intensif en ressources** : Nécessite que Docker soit installé et en cours d'exécution
- **Complexité** : Configuration supplémentaire nécessaire pour la configuration des conteneurs
- **Courbe d'apprentissage** : L'équipe doit comprendre les concepts Docker et Testcontainers

**Q : Comparaison entre les tests existants basés sur H2 et l'approche Testcontainers :**

L'implémentation actuelle utilise une base de données en mémoire H2 qui est :
- Plus rapide pour l'exécution des tests
- Plus simple à configurer
- Moins réaliste (H2 peut avoir un comportement différent du MySQL de production)
- Pas adaptée pour tester les fonctionnalités spécifiques à la base de données

Testcontainers avec MySQL serait :
- Plus lent mais plus réaliste
- Mieux pour détecter les problèmes de compatibilité de base de données
- Plus aligné avec l'environnement de production
- Nécessite l'installation de Docker

### Statut d'Achèvement

**Partie 1 :**
- Services implémentés : Oui (UserService, OrderService, ProductService)
- Interfaces simples créées : Oui (UserRepository, OrderDao, ProductApiClient)
- Services simples créés : Oui (SimpleUserService, SimpleOrderService, SimpleProductService)
- Contrôleurs simples créés : Oui (SimpleOrderController)
- Tests unitaires avec mocks : Oui (SimpleUserServiceTest, SimpleOrderControllerTest, SimpleProductServiceTest)
- Tous les scénarios de test requis couverts : Oui

**Partie 2 :**
- Dépendances Testcontainers : Oui
- Implémentation Testcontainers réelle : Oui (conteneur MySQL 8.0 configuré)
- Tous les tests d'intégration mis à jour avec @DynamicPropertySource : Oui
- Dockerfile créé : Oui (build multi-étapes pour l'application TP3)
- Docker Compose créé : Oui (services MySQL + application TP3)
- Projet task-manager : Non cloné (peut être fait séparément)

---

## Module Partagé

Le module partagé contient les modèles, interfaces et exceptions communs utilisés dans TP3 :
- Modèles : User, Product, Order
- Interfaces de dépôt : UserRepository, ProductRepository, OrderRepository (Spring Data JPA)
- DAO : OrderDao
- Exceptions : ApiException, InvalidDataException, ResourceNotFoundException

---
