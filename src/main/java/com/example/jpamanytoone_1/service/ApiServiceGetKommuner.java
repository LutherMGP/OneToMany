package com.example.jpamanytoone_1.service;

import com.example.jpamanytoone_1.model.Kommune;
import com.example.jpamanytoone_1.model.Region;

import java.util.List;

public interface ApiServiceGetKommuner {
    List<Kommune> getKommuner(); // Benyttes kun til at indhente Kommune data fra Dataforsyningen til lokal database
    List<Kommune> getAllKommuner();
}
