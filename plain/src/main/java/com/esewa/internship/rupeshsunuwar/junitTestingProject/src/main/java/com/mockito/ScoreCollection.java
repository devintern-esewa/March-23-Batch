package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.mockito;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {

    private List<Scoreable> scores=new ArrayList<>();

    public void add(Scoreable scoreable){
        scores.add(scoreable);
    }

    public int arithmeticMean(){
        int total= scores.stream().mapToInt(Scoreable::getScore).sum();
        return total/scores.size();
    }
}
