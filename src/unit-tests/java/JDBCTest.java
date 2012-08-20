import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;


@ContextConfiguration
public class JDBCTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldTestNumberOfRowsInConferenceTable() throws Exception {
       String SQL = "select count(*) from Conference";
       int rowCount = jdbcTemplate.queryForInt( SQL );
       assertEquals(1, rowCount);
    }

}