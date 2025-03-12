# ParisJanitor - Microservice des biens immobiliers

<!--Une brève description de votre projet -->
Ce microservice est une composante autonome conçue selon la clean architecture, elle se concentre uniquement sur les fonctionnalités liées à la gestion des utilisateurs.

## Table des matières

1. [Introduction](#introduction)
2. [Architecture](#architecture)
3. [Prérequis](#prérequis)
4. [Installation](#installation)
5. [Configuration](#configuration)
6. [Utilisation](#utilisation)
7. [Tests](#tests)
8. [Contribuer](#contribuer)
9. [Licence](#licence)
10. [Deploiement](#deploiement)
11. [Points](#Points de Terminaison API)
12. [Authentification](#Authentification)
13. [Exemples](#Exemples)
14. [Depannage](#Dépannage)


## Introduction

<!--Fournir une description plus détaillée de votre projet, en Expliquant les objectifs, les fonctionnalités principales et le contexte du projet.-->

## Prérequis

- Java 17
- Maven 3.9.9
- Spring Boot 3.3.5
- Base de données relationnelles: MySQL Workbench 8.0
- Spring Security (Basic Auth & JWT)

## Architecture 

<!--
com.example.paris-janitor-api
├── application            # 💡 Couche Application (Use Cases et Ports)
│   ├── port              # Définition des interfaces (Ports IN & OUT)
│   │   ├── in            # Ports d'entrée (actions disponibles pour les Adapters IN)
│   │   │   ├── CreateProductPort.java
│   │   │   ├── GetAllProductsPort.java
│   │   ├── out           # Ports de sortie (interactions avec la base, services externes)
│   │   │   ├── LoadProductsPort.java
│   │   │   ├── PersistProductPort.java
│   ├── usecase           # Implémentation des Use Cases (n'intéragit pas avec la BD mais passe tj=oujours par un Port OUT pour recuperer les donnees
│   │   ├── CreateProductUseCase.java
│   │   ├── GetAllProductsUseCase.java
│
├── domain                # 💡 Couche Domaine (Entités et Logique Métier)
│   ├── model             # Modèles métiers (sans dépendance à Spring ou MongoDB)
│   │   ├── Product.java
│   ├── exception         # Gestion des exceptions métier
│   │   ├── InvalidProductException.java
│
├── adapters              # 💡 Couche Adapters (Implémentations des Ports)
│   ├── in                # Implémentations des ports d'entrée (API REST, Kafka, CLI...)
│   │   ├── rest          # Adapter pour une API REST
│   │   │   ├── ProductController.java
│   │   ├── event         # Adapter pour un Event Listener (Kafka, RabbitMQ, etc.)
│   │   │   ├── ProductEventListener.java
│   ├── out               # Implémentations des ports de sortie (Base de données, Services externes...)
│   │   ├── persistence   # Accès à MongoDB via Spring Data
│   │   │   ├── ProductMongoRepository.java
│   │   │   ├── ProductMongoAdapter.java
│   │   ├── external      # Appels vers des services externes (API tierces)
│   │   │   ├── ExternalPricingService.java
│
├── infrastructure        # 💡 Couche Infrastructure (Frameworks, Configurations, Security)
│   ├── config            # Configuration Spring Boot
│   │   ├── MongoConfig.java
│   │   ├── SecurityConfig.java
│   ├── exception         # Gestion des exceptions globales (Controller Advice)
│   │   ├── GlobalExceptionHandler.java
│
├── CleanArchApplication.java  # 🚀 Classe principale Spring Boot
└── resources
├── application.yml        # Configuration (MongoDB, Spring, etc.)


📌 Détails et Responsabilités de chaque couche
├──------------------------------------------------------------------------------------------------------
|Couche	       | Responsabilité
|domain	       | Contient les entités métier et la logique métier pure (aucune dépendance à Spring)
|application   | Définit les Ports (IN/OUT) et implémente les Use Cases
|adapters/in   | Implémente les ports d’entrée (API REST, Event Listeners, etc.)
|adapters/out  | Implémente les ports de sortie (MongoDB, API externes, etc.)
|infrastructure| Contient les configurations, sécurité, gestion des exceptions
├──------------------------------------------------------------------------------------------------------

-->

## Points de terminaison

- POST /users : Créer un nouvel utilisateur.
- GET /users/{id} : Récupérer les détails d'un utilisateur.
- PUT /users/{id} : Mettre à jour les informations d'un utilisateur.
- DELETE /users/{id} : Supprimer un utilisateur.
- POST /login : Authentifier un utilisateur.

