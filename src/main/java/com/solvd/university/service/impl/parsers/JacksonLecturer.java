package com.solvd.university.service.impl.parsers;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;

public interface JacksonLecturer {
    Lecturer readLecturerFromJackson();

    LecturerContact readLecturerContactFromJackson();
}
