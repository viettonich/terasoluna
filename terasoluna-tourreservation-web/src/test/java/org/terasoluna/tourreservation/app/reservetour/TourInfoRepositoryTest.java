package org.terasoluna.tourreservation.app.reservetour;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.terasoluna.tourreservation.domain.model.TourInfo;
import org.terasoluna.tourreservation.domain.repository.tourinfo.TourInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PostgresDataSourceConfiguration.class, MyBatisConfiguration.class })
public class TourInfoRepositoryTest {

    @Autowired
    private TourInfoRepository tourInfoRepository;

    @Test
    public void test1() {
        TourInfo tourInfo = tourInfoRepository.findOneWithDetails("0000000001");
        System.out.println(tourInfo);
    }
}
