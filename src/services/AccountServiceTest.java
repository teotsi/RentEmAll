package services;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void addApplication() {
        try {
            Service.CompanyReader("Companies.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}