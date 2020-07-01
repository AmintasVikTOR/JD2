package by.service;

import by.domain.Dealer;

import java.util.List;
import java.util.Optional;

public interface DealerService {

    //In realease AOP
    List<Dealer> findAll();

    List<Dealer> search(String searchParam);

    Optional<Dealer> findById(Long dealerId);

    Dealer findOne(Long dealerId);

    Dealer save(Dealer dealer);

    Dealer update(Dealer dealer);

    int delete(Dealer dealer);
}
