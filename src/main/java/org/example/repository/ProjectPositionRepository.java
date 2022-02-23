package org.example.repository;

import org.example.entity.ProjectPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectPositionRepository extends JpaRepository<ProjectPosition, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM project_positions " +
                    "WHERE position_end_date < current_date OR position_start_date > current_date")
    List<ProjectPosition> findAllAvailableProjectPositionsNow();

    @Query(nativeQuery = true, value =
            "SELECT * FROM project_positions " +
                    "WHERE position_start_date < current_date and position_end_date < current_date " +
                    "or position_start_date > current_date + ?1 and position_end_date > current_date + ?1")
    List<ProjectPosition> findAllAvailableProjectPositionsNextDays(int days);
}
