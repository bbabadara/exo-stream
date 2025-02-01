package com.ecole221.exostream;

import com.ecole221.exostream.model.Personne;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Personne> personnes= List.of(
                new Personne("Fatou","EMP001",LocalDate.of(1998,6,12)),
                new Personne("Demba","EMP002",LocalDate.of(1960,6,12)),
                new Personne("Moussa","EMP003",LocalDate.of(1948,6,12)),
                new Personne("Ndeye","EMP004",LocalDate.of(2000,6,12)),
                new Personne("Absa","EMP005",LocalDate.of(2018,6,12))
        );
//        System.out.println(personnes);
        // personnes majeures
        List<Personne> majeurs = personnes.stream().filter(personne -> Period.between(personne.dateNaissance(),LocalDate.now()).getYears()>=18).toList();

        //personnes retraitees
        List<Personne> retraites = personnes.stream().filter(personne -> Period.between(personne.dateNaissance(),LocalDate.now()).getYears()>60).toList();

       //filtre age ascendant
        List<Personne> ageAsc = personnes.stream()
                .sorted((p1, p2) ->  Period.between(p1.dateNaissance(),LocalDate.now()).getYears() -  Period.between(p2.dateNaissance(),LocalDate.now()).getYears())
                .toList();

        // filtre par odre alphabetique
        List<Personne> alphabetique = personnes.stream()
                .sorted(Comparator.comparing(Personne::nom))
                .toList();
//        Récupère uniquement la liste des noms des employés (en utilisant map).
        List<String> noms= personnes.stream().map(Personne::nom).toList();

//        Crée une liste de messages pour chaque employé :
//        Exemple : "Matricule: EMP001, Nom: Amadou, Age: 22"
        List<String> messages= personnes.stream()
                .map(personne -> "Matricule: "+personne.matricule()+", Nom: "+personne.nom()+", Age: "+Period.between(personne.dateNaissance(),LocalDate.now()).getYears())
                .toList();

//        Calcule l'âge moyen des employés.

//        int ageMoyenne=personnes.stream().reduce((p1, p2) -> Period.between(p1.dateNaissance(),LocalDate.now()).getYears()+Period.between(p2.dateNaissance(),LocalDate.now()).getYears());
//        System.out.println(messages);


    }
}