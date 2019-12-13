package com.stoldo.fitness_app_android.model;


import androidx.annotation.LayoutRes;

import com.stoldo.fitness_app_android.model.interfaces.ListItem;
import com.stoldo.fitness_app_android.model.interfaces.Subscriber;

import java.lang.reflect.Method;
import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ListViewData<S extends Subscriber, I extends ListItem> {
    private @LayoutRes int itemLayout;
    private Method defaultItemClickMethod;
    private Method editItemClickMethod;
    private List<I> items;
    private S listViewSubscriber;
}
