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
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("customers");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("customers", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void firstNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void firstNameIsAtMost60CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "first_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(60, column.getMaxLength());
    }
    @Test
    public void lastNameIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void lastNameIsAtMost60CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "last_name");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(60, column.getMaxLength());
    }
    @Test
    public void emailIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "email");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void emailIsAtMost60CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "email");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(60, column.getMaxLength());
    }
    @Test
    public void phoneNumberIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "phone_number");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void phoneNumberIsAtMost24CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("customers", "phone_number");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(24, column.getMaxLength());
    }
}
