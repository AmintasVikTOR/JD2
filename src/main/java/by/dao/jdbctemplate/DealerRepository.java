package by.dao.jdbctemplate;

import by.dao.DealerDao;
import by.domain.Dealer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("dealerRepositoryJdbcTemplate")

public class DealerRepository implements DealerDao {

    public static final String DEALER_ID = "id";
    public static final String DEALER_NAME = "dealer_name";
    public static final String DEALER_ADDRESS = "dealer_address";
    public static final String DEALER_CAPACITY = "dealer_capacity";
    public static final String DEALER_CREATED = "created";
    public static final String DEALER_CHANGED = "changed";
    public static final String DEALER_FOUNDATION = "year_of_foundation";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DealerRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Dealer> findAll() {
        /*Here will be aspect logic Before*/

        final String findAllQuery = "select * from m_auto_dealer order by id desc";
        /*Here will be aspect logic After*/
        return jdbcTemplate.query(findAllQuery, this::dealerRowMapper);
        /*Here will be aspect logic AfterReturning*/
        /*Here will be aspect logic AfterThrowing*/
    }

    private Dealer dealerRowMapper(ResultSet resultSet, int i) throws SQLException {
        Dealer dealer = new Dealer();
        dealer.setId(resultSet.getLong(DEALER_ID));
        dealer.setName(resultSet.getString(DEALER_NAME));
        dealer.setName(resultSet.getString(DEALER_ADDRESS));
        dealer.setId(resultSet.getLong(DEALER_CAPACITY));
        dealer.setCreated(resultSet.getTimestamp(DEALER_CREATED));
        dealer.setChanged(resultSet.getTimestamp(DEALER_CHANGED));
        dealer.setYearOfFoundation(resultSet.getDate(DEALER_FOUNDATION));
        return dealer;
    }

    @Override
    public List<Dealer> search(String searchParam) {
        final String searchQuery = "select * from m_auto_dealer where name = ? order by id desc";
        return jdbcTemplate.query(searchQuery, this::dealerRowMapper, searchParam);
    }

    @Override
    public Optional<Dealer> findById(Long dealerId) {
        return Optional.empty();
    }

    @Override
    public Dealer findOne(Long dealerId) {
        final String findById = "select * from m_auto_dealer where id = ?";

        return jdbcTemplate.queryForObject(findById, this::dealerRowMapper, dealerId);
    }

    @Override
    public Dealer save(Dealer dealer) {
        return null;
    }

    @Override
    public Dealer update(Dealer dealer) {
        return null;
    }

    @Override
    public int delete(Dealer dealer) {
        return 0;
    }
}
