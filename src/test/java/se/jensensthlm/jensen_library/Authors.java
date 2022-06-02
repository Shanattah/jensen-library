package se.jensensthlm.jensen_library;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensensthlm.testing_helpers.*;

import java.sql.SQLException;

public class Authors extends TableTestBase {

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("authors");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("authors", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void firstNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("authors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void lastNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("authors", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void firstNameIsAtMost60CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("authors", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(60, column.getMaxLength());
    }

    @Test
    public void lastNameIsAtMost60CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("authors", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(60, column.getMaxLength());
    }

}