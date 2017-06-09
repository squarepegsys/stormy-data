package com.objectpartners.stormydata

import com.objectpartners.stormydata.entity.State
import com.objectpartners.stormydata.entity.Storm
import com.objectpartners.stormydata.entity.StormType
import com.objectpartners.stormydata.repo.StateRepository
import com.objectpartners.stormydata.repo.StormRepository
import com.objectpartners.stormydata.repo.StormTypeRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.LocalDateTime

/*
Thanks nick.spillman and aaron.zirbes
 */

@ContextConfiguration(
        classes = [StormyDataApplication],
        initializers = ConfigFileApplicationContextInitializer
)
@ActiveProfiles('test')
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StormRepositorySpec extends Specification {

    @Autowired
    protected TestRestTemplate restTemplate

    @Autowired
    protected StormRepository stormRepository


    @Autowired
    protected StormTypeRepository stormTypeRepository

    @Autowired
    protected StateRepository stateRepository

    void 'get state with success'() {
        given:
        Storm storm = new Storm(
                id: 1l,
                beginTime: LocalDateTime.now(),
                endTime: LocalDateTime.now(),
                state: new State(id: 1l, name: 'Minnesota'),
        stormType: new StormType(id: 1l, name: 'Name')
        )

        stormTypeRepository.save(storm.stormType)
        stateRepository.save(storm.state)

        stormRepository.save(storm)

        when:

        Map page = restTemplate.getForObject("/api/storms", Map)

        then:
        page.status != 500
        log.warn page.toString()
    }
}
