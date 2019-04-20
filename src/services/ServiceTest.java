package services;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @org.junit.jupiter.api.Test
    void companyReader() {
        try {
            Service.CompanyReader(new File("../dataset/Companies.txt"));
            assertEquals (4, Service.companies.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void companyWriter() {
    }

    @org.junit.jupiter.api.Test
    void vehicleWriter() {
    }

    @org.junit.jupiter.api.Test
    void carReader() {
    }
}