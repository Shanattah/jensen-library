package se.jensensthlm.jensen_library;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensensthlm.testing_helpers.*;

import java.sql.SQLException;

public class Books extends TableTestBase {

    @Test
    public void tableHasPrimaryKey() throws SQLException {
        ColumnsList columns = info.getPrimaryKeys().filterByTableName("books");
        Assertions.assertEquals(1, columns.count());
    }

    @Test
    public void primaryKeyIsCalledId() throws SQLException {
        Column column = info.getPrimaryKeys().find("books", "id");
        Assertions.assertNotNull(column);
    }

    @Test
    public void titleIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("books", "title");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("varchar", column.getType());
    }

    @Test
    public void titleIsAtMost200CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("books", "title");
        Assertions.assertNotNull(column);
        Assertions.assertTrue(200 <= column.getMaxLength());
    }

    @Test
    public void isbnIsString() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("books", "isbn");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("char", column.getType());
    }
    @Test
    public void isbnIsExactly13CharactersLong() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("books", "isbn");
        Assertions.assertNotNull(column);
        Assertions.assertEquals(13, column.getMaxLength());
    }
    @Test
    public void id_authorIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("books", "id_author");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
    @Test
    public void foreignKeyExistsBetweenBooks_Authors() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("books")
                .filterByParentTable("authors");

        Assertions.assertNotNull(foreignKeysList);
    }
    @Test
    public void id_authorReferencesAuthors() throws SQLException {
        ForeignKey fkIdUser = info
                .getForeignKeys()
                .findByChildColumn(new Column("books", "id_author"));

        Assertions.assertNotNull(fkIdUser);
        Assertions.assertTrue(fkIdUser
                .getParentColumn()
                .equals(new Column("authors", "id"))
        );
    }
}