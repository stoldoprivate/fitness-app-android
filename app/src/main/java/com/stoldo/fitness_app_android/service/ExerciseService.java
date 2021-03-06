package com.stoldo.fitness_app_android.service;


import com.stoldo.fitness_app_android.model.abstracts.AbstractSyncService;
import com.stoldo.fitness_app_android.model.annotaions.Singleton;
import com.stoldo.fitness_app_android.model.data.entity.ExerciseEntity;
import com.stoldo.fitness_app_android.repository.ExerciseRepository;
import com.stoldo.fitness_app_android.util.OtherUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Singleton
public class ExerciseService extends AbstractSyncService {
    private ExerciseRepository exerciseRepository = (ExerciseRepository) OtherUtil.getSingletonInstance(ExerciseRepository.class);

    private ExerciseService() {}

    public List<ExerciseEntity> getExercisesByWorkoutId(Integer workoutId) throws SQLException {
        return exerciseRepository.getExercisesByWorkoutId(workoutId);
    }

    public List<ExerciseEntity> getAllExercises() throws SQLException {
        return exerciseRepository.findAll();
    }

    public ExerciseEntity saveExercise(ExerciseEntity exercise) throws SQLException {
        return exerciseRepository.save(exercise);
    }

    public List<ExerciseEntity> saveExercises(List<ExerciseEntity> exercises) throws SQLException {
        exerciseRepository.flushTable(); // without this, deleted items stay in the db
        List<ExerciseEntity> savedExercises = new ArrayList<>();

        for (ExerciseEntity exercise : exercises) {
            savedExercises.add(saveExercise(exercise));
        }

        return savedExercises;
    }
}
