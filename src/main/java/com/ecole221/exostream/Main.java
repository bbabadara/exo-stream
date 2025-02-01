package com.ecole221.exostream;
import com.ecole221.exostream.model.Personne;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.ArrayList;





//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int calculerAge(LocalDate dateNaissance) {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
    public static void main(String[] args) {

        List<Personne> personnes= List.of(
                new Personne("Fatou","EMP001",LocalDate.of(1998,6,12)),
                new Personne("Demba","EMP002",LocalDate.of(1960,7,1)),
                new Personne("Moussa","EMP003",LocalDate.of(1948,4,2)),
                new Personne("Ndeye","EMP004",LocalDate.of(2000,11,30)),
                new Personne("Absa","EMP005",LocalDate.of(2018,2,15)),
                new Personne("Baba","EMP006",LocalDate.of(1985,5,18)),
                new Personne("Alima","EMP006",LocalDate.of(1989,8,14))
        );
//        System.out.println(personnes);
        // personnes majeures
        List<Personne> majeurs = personnes.stream().filter(personne -> calculerAge(personne.dateNaissance())>=18).toList();

        //personnes retraitees
        List<Personne> retraites = personnes.stream().filter(personne -> calculerAge(personne.dateNaissance())>60).toList();

       //filtre age ascendant
        List<Personne> ageAsc = personnes.stream()
                .sorted((p1, p2) ->  calculerAge(p1.dateNaissance()) - calculerAge(p2.dateNaissance()))
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

        double ageMoyen = personnes.stream()
                .mapToInt(personne -> Period.between(personne.dateNaissance(), LocalDate.now()).getYears())
                .average()
                .orElse(0);


//        Trouve l'employé le plus âgé.
//        List<Personne> plusAge = new ArrayList<>(personnes.stream()
//                .sorted(Comparator.comparingInt(p -> Period.between(p.dateNaissance(), LocalDate.now()).getYears()))
//                .toList());
//        Collections.reverse(plusAge);

        Optional<Personne> plusAge = personnes.stream()
                .max(Comparator.comparingInt(p -> Period.between(p.dateNaissance(), LocalDate.now()).getYears()));


//        Trouve l'employé le plus jeune
        Optional<Personne> plusjeune = personnes.stream()
                .min(Comparator.comparingInt(p -> Period.between(p.dateNaissance(), LocalDate.now()).getYears()));

//        Regroupe les employés en deux catégories :
//          1. Moins de 40 ans.
        List<Personne> moins40 = personnes.stream().filter(personne -> Period.between(personne.dateNaissance(),LocalDate.now()).getYears()<40).toList();


//          2. 40 ans et plus.
        List<Personne> plus40 = personnes.stream().filter(personne -> Period.between(personne.dateNaissance(),LocalDate.now()).getYears()>=40).toList();

//• Vérifie s'il existe au moins un employé ayant moins de 18 ans.
        boolean verif= personnes.stream().anyMatch(personne -> Period.between(personne.dateNaissance(),LocalDate.now()).getYears()>=18);


//        • Vérifie si tous les employés ont un matricule non nul.
        boolean verif2= personnes.stream().anyMatch(personne -> personne.matricule()=="");


//        Pour chaque employé, calcule la date exacte à laquelle il atteindra 60 ans, en ajoutant 60 années à sa dateNaissance.
        List<String> date60ans= personnes.stream()
                .map(personne -> "Matricule: "+personne.matricule()+", Nom: "+personne.nom()+", Age: "+Period.between(personne.dateNaissance(),LocalDate.now()).getYears()+", date des 60ans: "+personne.dateNaissance().plusYears(60))
                .toList();


//        calcule de l'age reel

        List<String> ageRelle= personnes.stream()
                .map(personne -> "Matricule: "+personne.matricule()+", Nom: "+personne.nom()+" Date de naissance: "+personne.dateNaissance()+" Age: "+Period.between(personne.dateNaissance(),LocalDate.now()).getYears()+" ans "+Period.between(personne.dateNaissance(),LocalDate.now()).getMonths()+" mois "+ Period.between(personne.dateNaissance(),LocalDate.now()).getDays()+" jours")
                .toList();

//        Récupère tous les employés :
            //• Nés avant l’année 1990.
        List<Personne> avant1990 = personnes.stream().filter(personne -> personne.dateNaissance().getYear()<1990).toList();

        //• Nés après l’année 2000.
        List<Personne> apres2000 = personnes.stream().filter(personne -> personne.dateNaissance().getYear()>2000).toList();

        //• Nés durant les années 1980 (entre 1980 et 1989 inclus).
        List<Personne> entre80 = personnes.stream().filter(personne -> personne.dateNaissance().getYear()>=1980 && personne.dateNaissance().getYear()<=1989 ).toList();

        System.out.println("Personnes majeures : " + majeurs);
        System.out.println("Personnes retraitées : " + retraites);
        System.out.println("Âge moyen : " + ageMoyen);
        System.out.println("Employé le plus âgé : " + plusAge.orElse(null));
        System.out.println("Employé le plus jeune : " + plusjeune.orElse(null));
        System.out.println("Personnes nées avant 1990 : " + avant1990);
        System.out.println("Personnes nées après 2000 : " + apres2000);
        System.out.println("Personnes nées dans les années 1980 : " + entre80);




    }
}