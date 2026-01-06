# TP 7 — JAX\-RS \(Jersey\) avec Spring Boot

Application Spring Boot intégrant JAX\-RS \(Jersey\) pour exposer une API REST autour d'une entité `Compte`. Le démarrage insère des données d'exemple via `CommandLineRunner`.

## Caractéristiques

- Java \(Spring Boot\)
- JAX\-RS \(Jersey\)
- Spring Data JPA
- Maven
- Démarrage avec insertion de 3 comptes \(types `EPARGNE` et `COURANT`\)

## Structure du projet

TP 7  JAXRS  Jersey/
- `pom.xml`
- `src/main/java/com/example/demo/JaxrsApplication.java`
- `src/main/java/com/example/demo/config/MyConfig.java`
- `src/main/java/com/example/demo/controllers/CompteRestJaxRSAPI.java`
- `src/main/java/com/example/demo/entities/Compte.java`
- `src/main/java/com/example/demo/entities/TypeCompte.java`
- `src/main/java/com/example/demo/repositories/CompteRepository.java`
- `src/main/resources/application.properties`
- `src/test/java/com/example/demo/JaxrsApplicationTests.java`
- `mvnw`, `mvnw.cmd`

## Prérequis

- Java 17 ou supérieur
- Maven \(ou le Maven Wrapper fourni\)
- Git \(optionnel pour cloner\)

Vérifier:
- `java -version`
- `mvn -v`

## Installation et exécution

Cloner ou extraire le projet, puis:

- Windows \(PowerShell\):
  - `.\mvnw.cmd clean package`
  - `.\mvnw.cmd spring-boot:run`

- Linux/macOS:
  - `./mvnw clean package`
  - `./mvnw spring-boot:run`

Par défaut, l'application démarre sur `http://localhost:8082`

Exécution du JAR:
- `java -jar target/*.jar`

## Configuration

Fichier: `src/main/resources/application.properties`

- Port HTTP: `server.port=8080` \(si nécessaire\)
- Configuration JPA et datasource: définir selon votre SGBD \(ou mémoire\)
- La configuration Jersey et le préfixe de chemin de l'API se trouvent dans `src/main/java/com/example/demo/config/MyConfig.java` \(par exemple via `@ApplicationPath` ou `ResourceConfig`\)

## Données d'initialisation

Au démarrage, `JaxrsApplication` insère 3 comptes avec:
- `solde`: valeur aléatoire
- `dateCreation`: date du jour
- `type`: `EPARGNE` ou `COURANT`

Ces données sont persistées via `CompteRepository`.

## Modèle de données

Entité `Compte` \(champs typiques\):
- `id`: identifiant
- `solde`: nombre
- `dateCreation`: date
- `type`: enum `TypeCompte` \(valeurs `EPARGNE`, `COURANT`\)

Exemple JSON:
{
  "id": 1,
  "solde": 3500.75,
  "dateCreation": "2025-01-01T12:00:00Z",
  "type": "EPARGNE"
}

Le format de date peut varier selon la configuration Jackson.

## API REST

Contrôleur: `src/main/java/com/example/demo/controllers/CompteRestJaxRSAPI.java`

Chemin de base:
- Le préfixe dépend de `config/MyConfig.java` \(ex. racine `/` ou `/api`\). Dans les exemples ci\-dessous, remplacez `{BASE}` par l'URL réelle, p.ex. `http://localhost:8080` ou `http://localhost:8080/api`.

Endpoints usuels \(à adapter selon les annotations `@Path` exactes\):
- GET `{BASE}/comptes` — liste des comptes
- GET `{BASE}/comptes/{id}` — détail d'un compte
- POST `{BASE}/comptes` — créer un compte
- PUT `{BASE}/comptes/{id}` — mettre à jour un compte
- DELETE `{BASE}/comptes/{id}` — supprimer un compte

Exemples \(Windows PowerShell\):

- Lister:
  - `curl.exe -X GET "{BASE}/comptes"`

- Détail:
  - `curl.exe -X GET "{BASE}/comptes/1"`

- Créer:
  - `curl.exe -X POST "{BASE}/comptes" -H "Content-Type: application/json" -d "{\"solde\": 4200.0, \"dateCreation\": \"2025-01-01T12:00:00Z\", \"type\": \"COURANT\"}"`

- Mettre à jour:
  - `curl.exe -X PUT "{BASE}/comptes/1" -H "Content-Type: application/json" -d "{\"solde\": 9999.0, \"dateCreation\": \"2025-01-01T12:00:00Z\", \"type\": \"EPARGNE\"}"`

- Supprimer:
  - `curl.exe -X DELETE "{BASE}/comptes/1"`

## Tests

- `.\mvnw.cmd test` \(Windows\)
- `./mvnw test` \(Linux/macOS\)

Fichiers de test: `src/test/java/com/example/demo/JaxrsApplicationTests.java`

## Développement avec IntelliJ IDEA

- Ouvrir le dossier du projet
- Laisser IntelliJ importer le projet Maven
- Run configuration: `JaxrsApplication`
- Debug possible via le bouton Debug d'IntelliJ

## Dépannage

- Port déjà utilisé: changer `server.port` dans `application.properties`
- Problèmes de datasource: vérifier les propriétés JPA \(URL, driver, user, password\)
- Erreurs Jersey/JAX\-RS: confirmer la bonne inscription des ressources dans `MyConfig.java` et les annotations `@Path`/`@GET`/`@POST`, etc.

# Captures d'écran
## Base de donnees H2

<img width="1041" height="520" alt="Screenshot 2025-11-04 102601" src="https://github.com/user-attachments/assets/cd9cf9f7-b654-4711-9238-d216b0a19f16" />

## Test Postman format JSON

<img width="770" height="828" alt="Screenshot 2025-11-04 103730" src="https://github.com/user-attachments/assets/c31d3a16-0b41-49c9-94dc-fce1822c15ce" />

## Test Postman format XML

<img width="838" height="969" alt="Screenshot 2025-11-04 104934" src="https://github.com/user-attachments/assets/73c95f71-7024-4c6c-82ff-ef8f10cec6d3" />
