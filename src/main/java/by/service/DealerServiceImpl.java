package by.service;

import by.dao.DealerDao;
import by.domain.Dealer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    private DealerDao dealerDao;

    public DealerServiceImpl(@Qualifier("dealerRepositoryJdbcTemplate") DealerDao dealerDao) {
        this.dealerDao = dealerDao;
    }

    public Dealer findOne(Long dealerId) {
        return dealerDao.findOne(dealerId);
    }


    @Override
    public Dealer save(Dealer dealer) {
        return dealerDao.save(dealer);
    }

    @Override
    public Dealer update(Dealer dealer) {
        return dealerDao.update(dealer);
    }

    @Override
    public int delete(Dealer dealer) {
        return dealerDao.delete(dealer);
    }

    public List<Dealer> findAll() {
        return dealerDao.findAll();
    }

    @Override
    public List<Dealer> search(String searchParam) {
        return dealerDao.search(searchParam);
    }

    @Override
    public Optional<Dealer> findById(Long dealerId) {
        return dealerDao.findById(dealerId);
    }

}
