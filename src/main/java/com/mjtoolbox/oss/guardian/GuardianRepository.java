package com.mjtoolbox.oss.guardian;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuardianRepository extends PagingAndSortingRepository<Guardian, Long> {
    Guardian findByGuardianName(String guardianName);
}
