drop database if exists BDD_ppe; 
create database BDD_ppe; 
use BDD_ppe;

CREATE TABLE Client(
idClient INT AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(50) NOT NULL,
prenom VARCHAR(50) NOT NULL,
adresse VARCHAR(200) NOT NULL,
tel VARCHAR(11) UNIQUE NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Reservation(
    refR INT AUTO_INCREMENT PRIMARY KEY,
    dateR DATE NOT NULL,
    dateHeureDebut DATETIME NOT NULL,   
    dateHeureFin DATETIME NOT NULL,
    nbPersonne INT NOT NULL,
    idClient INT NOT NULL,
    FOREIGN KEY (idClient) REFERENCES Client(idClient)

);

CREATE TABLE ContratLoc(
    refC INT AUTO_INCREMENT PRIMARY KEY,
    dureeC INT NOT NULL,
    dateDebutC DATE NOT NULL,
    dateFinC DATE NOT NULL,
    nbPersonneC INT NOT NULL
    idClient INT NOT NULL,
    FOREIGN KEY (idclient) REFERENCES Client(idClient),
    idHabitation INT NOT NULL,
    FOREIGN KEY (idHabitation) REFERENCES Habitation(idHabitation)
);

CREATE TABLE TypeEquipement(
    idTypeEquipement INT AUTO_INCREMENT PRIMARY KEY,
    nomType VARCHAR(1OO) NOT NULL,
    description VARCHAR(500) NOT NULL
);

CREATE TABLE Equipement(
    idEquipement INT AUTO_INCREMENT PRIMARY KEY,
    nomEquipement VARCHAR(100) NOT NULL,
    qteEquipement INT NOT NULL,
    idTypeEquipement INT NOT NULL,
    FOREIGN KEY (idTypeEquipement) REFERENCES TypeEquipement(idTypeEquipement)
);

CREATE TABLE Habitation(
    idHabitation INT AUTO_INCREMENT PRIMARY KEY,
    adresse VARCHAR(200) NOT NULL,
    taille INT NOT NULL,
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProprietaire) REFERENCES Proprietaire(idProprietaire),
    codeR VARCHAR(100) NOT NULL,
    FOREIGN KEY (codeR) REFERENCES Region(codeR)

);

CREATE TABLE Region(
    codeR VARCHAR(100) PRIMARY KEY,
    departement INT NOT NULL,
    villeR VARCHAR(50) NOT NULL
);

CREATE TABLE Station (
    codeS INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    nomS VARCHAR(50) NOT NULL,
    etoiles INT NOT NULL,
    codeR VARCHAR(100)NOT NULL, 
    FOREIGN KEY (codeR) REFERENCES Region(codeR)
);

CREATE TABLE Activite(
    codeA INT AUTO_INCREMENT PRIMARY KEY,
    nomActivite VARCHAR(50) NOT NULL,
    tarifA DECIMAL(6,2) NOT NULL
);

CREATE TABLE Proprietaire (
    idProprietaire INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    adresse VARCHAR(200) NOT NULL,  
    tel VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE ContratMandatLocatif(
    idContrat INT AUTO_INCREMENT PRIMARY KEY,
    etatContrat VARCHAR(50) NOT NULL,
    dateSignature DATE NOT NULL
    idProprietaire INT NOT NULL,
    FOREIGN KEY (idProprietaire) REFERENCES Proprietaire(idProprietaire),
    idHabitation INT NOT NULL,
    FOREIGN KEY (idHabitation) REFERENCES Habitation(idHabitation)

);

CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mdp VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO User (nom, prenom, email, mdp, role) 
VALUES ('abc', 'efg', 'a@gmail.com', '123', 'admin');

