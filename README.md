# ParisJanitor - Microservice des biens immobiliers

## 📖Table des matières

1. ✅[Introduction](#introduction)
2. 📦[Prérequis](#prérequis)
3. 🧱[Architecture](#architecture)
3. ⚙️[Installation](#installation)
4. 🔧[Configuration](#configuration)
5. 🚀[Utilisation](#utilisation)
6. 🧪[Tests](#tests)
7. 🧑‍💻[Contribuer](#contribuer)
8. 📄[Licence](#licence)
9. 📦[Deploiement](#deploiement)
10. ⭐[Points importants](#Terminaison API)
11. 🔐[Authentification](#Authentification)
12. 🛠️[Dépannage](#Dépannage)



## Introduction

Ce microservice est une composante autonome conçue selon la clean architecture, elle se concentre sur les 
ressources liées aux propriétés , aux réservations et diverses prestations  .

## Prérequis

- Java 17
- Maven 3.9.9
- Docker
- Spring Boot 3.3.5
- OpenAPI
- Base de données relationnelles: Mongo Atlas
- Spring Security (Basic Auth)
- RabbitMQ

## Architectur

📌 Détails et Responsabilités de chaque couche
├──------------------------------------------------------------------------------------------------------
|Couche	       | Responsabilité
|domain	       | Contient les entités métier et la logique métier pure (aucune dépendance à Spring)
|application   | Définit les Ports (IN/OUT) et implémente les Use Cases
|adapters/in   | Implémente les ports d’entrée (API REST, Event Listeners, etc.)
|adapters/out  | Implémente les ports de sortie (MongoDB, API externes, etc.)
|infrastructure| Contient les configurations, sécurité, gestion des exceptions
├──------------------------------------------------------------------------------------------------------



## Points de terminaison

### Booking

- POST /booking : Créer une nouvelle réservation.
- GET /booking/{id} : Récupérer les détails d'une réservation.
- PUT /booking/{id} : Mettre à jour les informations de réservation.
- DELETE /booking/{id} : Supprimer une réservation.


### Delivery

- POST /delivery : Créer une nouvelle prestation.
- GET /delivery/{id} : Récupérer les détails d'une prestation.
- PUT /delivery/{id} : Mettre à jour une prestation.
- DELETE /delivery/{id} : Supprimer une prestation.


### Property

- POST /property : Créer une nouvelle propriété.
- GET /property/{id} : Récupérer les détails d'une propriété.
- PUT /property/{id} : Mettre à jour une propriété.
- DELETE /property/{id} : Supprimer une propriété.


### Request

- POST /request : Créer une nouvelle demande.
- GET /request/{id} : Récupérer les détails d'une demande.
- PUT /request/{id} : Mettre à jour les informations d'une demande.
- DELETE /request/{id} : Supprimer une demande.


### Depannage

1. Ajouter Mockito comme agent JVM

Mockito is currently self-attaching to enable the inline-mock-maker.

WARNING: Au lancement du Clean install ,Ce warning est lié à Mockito et à son inline-mock-maker, qui permet de mocker des classes finales et statiques. 

#### Solution possible 

2.L'erreur "The forked VM terminated without properly saying goodbye"

Problème lié à Maven Surefire lors de l'exécution des tests, Si les tests consomment trop de mémoire, la JVM peut crasher.

#### Solution possible 

Augmenter la mémoire disponible pour Maven Surefire dans les pugins du ton pom.xml


3. Error processing condition on org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration.propertySourcesPlaceholderConfigurer