package io.github.supplygo.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TreeUtils {

    public static <T, K> List<T> buildTree(List<T> list,
                                           Function<T, K> idGetter,
                                           Function<T, K> parentIdGetter,
                                           BiConsumer<T, T> addChild) {
        List<T> newList = Optional.ofNullable(list).orElse(Lists.newArrayList());
        Map<K, T> idMap = newList.stream().collect(Collectors.toMap(idGetter, Function.identity()));
        List<T> roots = Lists.newArrayList();
        for (T item : newList) {
            K parentId = parentIdGetter.apply(item);
            T parent = idMap.get(parentId);
            if (parent != null) {
                addChild.accept(parent, item);
            } else {
                roots.add(item);
            }
        }
        return roots;
    }

}
