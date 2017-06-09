package db.migration;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Created by mikeh on 6/9/17.
 */
public class V2__insert_data implements SpringJdbcMigration {

    private static DateTimeFormatter  dateformat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss z");
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


            String czTimezone =discoverTimezone(record.get("CZ_TIMEZONE"));
            String beginDateString = record.get("BEGIN_DATE_TIME")+" "+ czTimezone;
            String endDateStr = record.get("END_DATE_TIME")+" "+ czTimezone;

            LocalDateTime beginTime = LocalDateTime.parse(beginDateString,dateformat);
            LocalDateTime endTime = LocalDateTime.parse(endDateStr,dateformat);

            jdbcTemplate.update(
                    "insert into storm_info(begin_timestamp, end_timestamp, comments, event_id, episode_id, state_id, storm_type_id) "+
                            "values (?,?,?,?,?, ?, ?)",
                    beginTime,
                    endTime,
                    record.get("EPISODE_NARRATIVE"),
                    Long.parseLong(record.get("EVENT_ID")),
                    Long.parseLong(record.get("EPISODE_ID")),
                    stateId,
                    stormTypeId

            );


        }
    }

    private String discoverTimezone(String czTimezone) {

        String[] tzParts = czTimezone.split(Pattern.quote("-"));

        if (tzParts.length>1) {
            return tzParts[0];
        }

        if (czTimezone.equals("GST10")) {
            return "UTC+10:00";
        }

        return czTimezone;
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
