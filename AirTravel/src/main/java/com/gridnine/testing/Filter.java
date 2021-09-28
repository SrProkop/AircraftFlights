package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public interface Filter<T> {
    public List<T> beforeTime(List<T> list, LocalDateTime dateTime);
    public List<T> afterTime(List<T> list, LocalDateTime dateTime);
    public List<T> breakTimeMore(List<T> list, long hours);
    public List<T> breakTimeLess(List<T> list, long hours);
    public List<T> arrivalBeforeDeparture(List<T> list);

}
