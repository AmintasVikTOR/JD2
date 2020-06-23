package by.dao;

import by.domain.Dealer;

import java.util.List;

public interface DealerDao {
    List<Dealer> findAll();

    List<Dealer> search(String searchParam);

    Dealer findById(Long dealerId);

    Dealer findOne(Long dealerId);

    Dealer save(Dealer dealer);

    Dealer update(Dealer dealer);

    int delete(Dealer dealer);
}
