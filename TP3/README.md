# TP3

## Partie 1 : Tests Unitaires avec Mocks - COMPLÉTÉ

### Exercice 1 : UserService avec UserRepository
- Créé l'interface simple `UserRepository` avec la méthode `findUserById(long id)`
- Créé la classe `SimpleUserService` avec la méthode `getUserById(long id)`
- Créé `SimpleUserServiceTest` avec des mocks Mockito
- Les tests vérifient que `findUserById` est appelé avec le bon argument
- Scénarios de test : utilisateur trouvé, utilisateur non trouvé, dépôt lance une exception

### Exercice 2 : OrderController avec OrderService et OrderDao
- Mis à jour l'interface `OrderDao` pour retourner `Order` au lieu de `void`
- Créé la classe `SimpleOrderService` avec la méthode `createOrder(Order order)`
- Créé la classe `SimpleOrderController` avec la méthode `createOrder(Order order)`
- Créé `SimpleOrderControllerTest` avec des mocks Mockito
- Les tests vérifient que `OrderService.createOrder` est appelé avec le bon argument
- Scénarios de test : création de commande réussie, service lance une exception

### Exercice 3 : ProductService avec ProductApiClient
- Créé l'interface `ProductApiClient` avec la méthode `getProduct(String productId)`
- Créé la classe `SimpleProductService` avec la méthode `getProduct(String productId)`
- Créé `SimpleProductServiceTest` avec des mocks Mockito
- Les tests vérifient que `ProductApiClient.getProduct` est appelé avec le bon argument
- Scénarios de test comme requis par le manuel :
  - Récupération réussie
  - Format de données incompatible (lance InvalidDataException)
  - Échec de l'appel API (lance ApiException)
  - Produit non trouvé

### Note
Les implémentations existantes Spring Boot/JPA restent inchangées. Les interfaces et services simples créés ci-dessus sont spécifiquement pour les tests unitaires avec mocks comme requis par le manuel.

## Partie 2 : Docker et Testcontainers - COMPLÉTÉ (Exercice 1)

### Exercice 1 : Implémentation Testcontainers
- Implémenté `AbstractTestContainer` avec la configuration du conteneur MySQL 8.0
- Ajouté `@DynamicPropertySource` à toutes les classes de tests d'intégration :
  - UserServiceIntegrationTest
  - OrderServiceIntegrationTest
  - ProductServiceIntegrationTest
  - UserRepositoryTest
  - OrderRepositoryTest
  - ProductRepositoryTest
- Les tests utilisent maintenant un conteneur MySQL réel au lieu de la base de données en mémoire H2
- Le conteneur démarre automatiquement avant les tests et s'arrête après
- La configuration inclut :
  - Nom de la base de données : testdb
  - Nom d'utilisateur : testuser
  - Mot de passe : testpass

### Configuration Docker
- Créé `Dockerfile` pour l'application TP3 (build multi-étapes)
  - Étape de build : Maven avec Eclipse Temurin 17
  - Étape d'exécution : Eclipse Temurin 17 JRE Alpine
- Créé `docker-compose.yml` à la racine du projet avec :
  - Service MySQL 8.0 avec volume persistant
  - Service d'application TP3
  - Configuration réseau pour la communication entre services
  - Variables d'environnement pour la connexion à la base de données

### Exercice 2 : Projet Task-Manager - NON COMMENCÉ
Cet exercice nécessite le clonage et l'analyse d'un projet externe, ce qui peut être fait séparément.