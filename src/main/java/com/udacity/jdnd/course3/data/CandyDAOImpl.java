package com.udacity.jdnd.course3.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CandyDAOImpl implements CandyDAO{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CANDY_ID = "candyId";
    private static final String DELIVERY_ID = "deliveryId";

    private static final String SELECT_ALL_CANDY =
            "SELECT * FROM candy";
    private static final String INERT_DELIVERY =
            "INSERT INTO Delivery(candy_id, delivery_id)" + "VALUES (:" + CANDY_ID + ", :" + DELIVERY_ID + ")";
    private static final String FIND_CANDY_BY_DELIVERY =
            "SELECT C.* FROM candy_delivery AS cd " +
            "JOIN candy AS c on c.id = cd.candy_id" +
            "WHERE cd.delivery_id = :" + DELIVERY_ID;
    private static  final RowMapper<CandyData> candyDataRowMapper =
            new BeanPropertyRowMapper<>(CandyData.class);


    @Override
    public List<CandyData> list() {
        return namedParameterJdbcTemplate.query(SELECT_ALL_CANDY, candyDataRowMapper);
    }

    @Override
    public void addToDelivery(Long candyId, Long deliveryId) {
        namedParameterJdbcTemplate.update(INERT_DELIVERY, new MapSqlParameterSource()
                                            .addValue(CANDY_ID, candyId)
                                            .addValue(DELIVERY_ID, deliveryId));
    }

    @Override
    public List<CandyData> findByDelivery(Long deliveryId) {
        return  namedParameterJdbcTemplate.query(FIND_CANDY_BY_DELIVERY,
                        new MapSqlParameterSource(DELIVERY_ID, deliveryId),
                            candyDataRowMapper);
    }
}
