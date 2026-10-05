package com.nibm.mothercare.repository;

import com.nibm.mothercare.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
    List<Reminder> findByMotherIdOrderByReminderDateAscReminderTimeAsc(Integer motherId);
}
