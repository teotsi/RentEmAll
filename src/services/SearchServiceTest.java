package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {

    @Test
    void calculateDistance() {
        double distance = SearchService.calculateDistance(5,0,4,0);
        assertEquals(111, Math.round(distance));
    }
}