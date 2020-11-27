package com.smart.california.service;

import com.smart.california.domain.TourPackage;
import com.smart.california.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tour Package Service
 * <p>
 * Created by Mary Ellen Bowman
 */
@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    /**
     * Create a Tour Package
     *
     * @param code code of the package
     * @param name name of the package
     * @return new or existing tour package
     */
    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    /**
     * Lookup All Tour packages
     *
     * @return
     */
    public Iterable<TourPackage> lookup() {
        return tourPackageRepository.findAll();
    }

    public long total() {
        return tourPackageRepository.count();
    }
}

