package com.ecole221.exostream.model;

import java.time.LocalDate;
import java.time.Period;

public record Personne(String nom, String matricule, LocalDate dateNaissance) {

}
