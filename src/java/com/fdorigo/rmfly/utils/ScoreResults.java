/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.utils;

import com.fdorigo.rmfly.jpa.entities.Score;
import java.util.List;

/**
 *
 * @author fdorigo
 */
public class ScoreResults {

    private float scoreOverall;
    private float scoreFuselage;
    private float scoreLifts;
    private float scorePitch;
    private float scoreLanding;
    private float scoreCockpit;
    private float scorePower;
    private float scoreFinish;
    private float scoreInnovation;
    private float totalScore;

    public ScoreResults(List<Score> scores) {
        if (scores == null || scores.isEmpty()) {
            return;
        }

        int divider = scores.size();

        for (Score s : scores) {
            scoreOverall += s.getScoreOverall();
            scoreFuselage += s.getScoreFuselage();
            scoreLifts += s.getScoreLifts();
            scorePitch += s.getScorePitch();
            scoreLanding += s.getScoreLanding();
            scoreCockpit += s.getScoreCockpit();
            scorePower += s.getScorePower();
            scoreFinish += s.getScoreFinish();
            scoreInnovation += s.getScoreInnovation();
            totalScore += (s.getScoreOverall() + s.getScoreFuselage() + s.getScoreLifts()
                    + s.getScorePitch() + s.getScoreLanding() + s.getScoreCockpit() 
                    + s.getScorePower() + s.getScoreFinish() + s.getScoreInnovation()) / 9;
        }
        
        scoreOverall /= divider;
        scoreFuselage /= divider;
        scoreLifts /= divider;
        scorePitch /= divider;
        scoreLanding /= divider;
        scoreCockpit /= divider;
        scorePower /= divider;
        scoreFinish /= divider;
        scoreInnovation /= divider;
        totalScore /= divider;
    }

    public Float getScoreOverall() {
        return scoreOverall;
    }

    public Float getScoreFuselage() {
        return scoreFuselage;
    }

    public Float getScoreLifts() {
        return scoreLifts;
    }

    public Float getScorePitch() {
        return scorePitch;
    }

    public Float getScoreLanding() {
        return scoreLanding;
    }

    public Float getScoreCockpit() {
        return scoreCockpit;
    }

    public Float getScorePower() {
        return scorePower;
    }

    public Float getScoreFinish() {
        return scoreFinish;
    }

    public Float getScoreInnovation() {
        return scoreInnovation;
    }

    public Float getTotalScore() {
        return totalScore;
    }
}
