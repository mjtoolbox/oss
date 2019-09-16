package com.mjtoolbox.oss.teacher;

import com.mjtoolbox.oss.program.Program;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {
    List<Teacher> findByProgram(Program program);
}
