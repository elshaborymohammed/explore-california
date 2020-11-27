package com.smart.california;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.california.domain.Difficulty;
import com.smart.california.domain.Region;
import com.smart.california.service.TourPackageService;
import com.smart.california.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class ExploreCaliforniaApplication implements CommandLineRunner {

    @Value("${california.file}")
    private String importFile;

    @Autowired
    private TourPackageService tourPackageService;
    @Autowired
    private TourService tourService;

    public static void main(String[] args) {
        SpringApplication.run(ExploreCaliforniaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createTourAllPackages();
        createTours(importFile);
    }

    /**
     * Initialize all the known tour packages
     */
    private void createTourAllPackages() {
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }

    /**
     * Create tour entities from an external file
     */
    private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(importedTour ->
                tourService.createTour(importedTour.getTitle(),
                        importedTour.getDescription(),
                        importedTour.getBlurb(),
                        importedTour.getPrice(),
                        importedTour.getLength(),
                        importedTour.getBullets(),
                        importedTour.getKeywords(),
                        importedTour.getPackageType(),
                        importedTour.getDifficulty(),
                        importedTour.getRegion()));
    }

    /**
     * Helper class to import ExploreCalifornia.json
     */
    private static class TourFromFile {
        //fields
        private String packageType, title, description, blurb, price, length,
                bullets, keywords, difficulty, region;

        //reader
        static List<TourFromFile> read(String fileToImport) throws IOException {
            System.out.println("path file : " + new File(fileToImport).getAbsolutePath());
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {
                    });
        }

        protected TourFromFile() {
        }

        String getPackageType() {
            return packageType;
        }

        String getTitle() {
            return title;
        }

        String getDescription() {
            return description;
        }

        String getBlurb() {
            return blurb;
        }

        Integer getPrice() {
            return Integer.parseInt(price);
        }

        String getLength() {
            return length;
        }

        String getBullets() {
            return bullets;
        }

        String getKeywords() {
            return keywords;
        }

        Difficulty getDifficulty() {
            return Difficulty.valueOf(difficulty);
        }

        Region getRegion() {
            return Region.findByLabel(region);
        }
    }
}