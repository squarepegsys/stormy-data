package db.migration;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mikeh on 6/9/17.
 */
public class V2__insert_data implements SpringJdbcMigration {

    private static DateTimeFormatter  dateformat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        FileReader dataFile = new FileReader(getClass().getClassLoader().getResource("stormdata_2013.csv").getPath());

        Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.withHeader().parse(dataFile);

        for(CSVRecord record: csvRecords) {
            String stateName = record.get("STATE");
            String stormType = record.get("EVENT_TYPE");

            // Do we have these values already?
            Long stateId = findValueInTable(jdbcTemplate, stateName, "state");
            Long stormTypeId = findValueInTable(jdbcTemplate, stormType, "storm_type");
            // 01/01/2013 00:00:00


            LocalDateTime beginTime = LocalDateTime.parse(record.get("BEGIN_DATE_TIME"),dateformat);
            LocalDateTime endTime = LocalDateTime.parse(record.get("END_DATE_TIME"),dateformat);

            jdbcTemplate.update(
                    "insert into storm_info(begin_timestamp, end_timestamp, comments, state_id, storm_type_id) "+
                            "values (?,?,?,?,?)",
                    beginTime,
                    endTime,
                    record.get("EPISODE_NARRATIVE"),
                    stateId,
                    stormTypeId

            );


        }
    }

    private Long findValueInTable(JdbcTemplate jdbcTemplate, String value, String tableName) {


        long  id = 0;

        try {
            id=jdbcTemplate.queryForObject("select id from " + tableName + " where name=?", Long.class, value);
        }catch (EmptyResultDataAccessException e){
            jdbcTemplate.update("insert into "+tableName+"(name) values(?)", value);

            return findValueInTable(jdbcTemplate,value,tableName);

        }



        return id;
    }
}
