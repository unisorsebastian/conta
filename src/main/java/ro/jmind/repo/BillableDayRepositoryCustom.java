package ro.jmind.repo;

import ro.jmind.model.BillableDay;

public interface BillableDayRepositoryCustom<T extends BillableDay> {
    T save(T t);

    Iterable<T> findAll();
}
