package com.jobsity.game.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerGameImplTest {

    private PlayerGameImpl game;

    @Before
    public void setUp() {
        game = new PlayerGameImpl("John");
    }

    private void roll(List<Integer> rolls) {
        rolls.forEach(pinsDown -> game.roll(pinsDown));
    }

    @Test
    public void shouldScoreNoPointsGame() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0));
        roll(rolls);
        assertEquals(0, game.score());
    }

    @Test
    public void shouldScoreGameOfOnes() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1));
        roll(rolls);
        assertEquals(20, game.score());
    }

    @Test
    public void shouldScoreOneSpareFollowedByFivePoints() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(5,5, 5,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0));
        roll(rolls);
        assertEquals(20, game.score());
    }

    @Test
    public void shouldScoreThreeSparesFollowedByFivePoints() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(5,5, 5,5, 5,5, 5,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0));
        roll(rolls);
        assertEquals(50, game.score());
    }

    @Test
    public void shouldScoreOnlySparesExceptLastFrame() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(1,9, 2,8, 3,7, 4,6, 5,5, 6,4, 7,3, 8,2, 9,1, 5,4));
        roll(rolls);
        assertEquals(148, game.score());
    }

    @Test
    public void shouldScoreOnlyStrikesExceptTwoLastFrames() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 5,4, 2,7));
        roll(rolls);
        assertEquals(242, game.score());
    }

    @Test
    public void shouldScoreOnlySparesWithOneAdditionalRoll() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(1,9, 2,8, 3,7, 4,6, 5,5, 6,4, 7,3, 8,2, 9,1, 5,5, 5));
        roll(rolls);
        assertEquals(154, game.score());
    }

    @Test
    public void shouldScoreOnlyStrikesWithTwoAdditionalRolls() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
        roll(rolls);
        assertEquals(300, game.score());
    }

    @Test
    public void shouldScoreSparesAndStrikesWithOneAdditionalRoll() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(10, 5,5, 10, 5,5, 10, 5,5, 10, 5,5, 10, 5,5, 10));
        roll(rolls);
        assertEquals(200, game.score());
    }

    @Test
    public void shouldScoreSparesAndStrikesWithTwoAdditionalRolls() {
        List<Integer> rolls = new ArrayList<>(Arrays.asList(5,5, 10, 5,5, 10, 5,5, 10, 5,5, 10, 5,5, 10, 5,5));
        roll(rolls);
        assertEquals(200, game.score());
    }
}