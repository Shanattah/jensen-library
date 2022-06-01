package se.jensensthlm.jensen_library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensensthlm.testing_helpers.Column;
import se.jensensthlm.testing_helpers.ColumnType;
import se.jensensthlm.testing_helpers.ColumnsList;

import java.sql.SQLException;

public class Customers extends TableTestBase {

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("users");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("users", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void userNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("users", "username");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void userNameIsAtMost64CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("users", "username");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(64, column.getMaxLength());
    }
    @Test
    public void nameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("users", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void nameIsAtMost50CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("users", "name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(50, column.getMaxLength());
    }
    @Test
    public void emailIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("users", "email");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void emailIsAtMost50CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("users", "email");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(50, column.getMaxLength());
    }
}
