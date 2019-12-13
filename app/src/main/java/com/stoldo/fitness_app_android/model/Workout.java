package com.stoldo.fitness_app_android.model;

import androidx.annotation.LayoutRes;

import com.stoldo.fitness_app_android.R;
import com.stoldo.fitness_app_android.model.interfaces.ListItem;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Workout implements ListItem {
    private Integer id;
    private String title;
    private String description;
    private List<Exercise> exercises;

    @Override
    public String getExtra() {
        return null;
    }
}