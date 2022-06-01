package se.jensensthlm.jensen_library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jensensthlm.testing_helpers.Column;
import se.jensensthlm.testing_helpers.ColumnType;
import se.jensensthlm.testing_helpers.ForeignKey;
import se.jensensthlm.testing_helpers.ForeignKeysList;

import java.sql.SQLException;

public class Shared_list_customers_books extends TableTestBase {


    @Test
    public void id_userIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("shared_todo_lists", "id_user");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
    @Test
    public void id_todo_listIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("shared_todo_lists", "id_todo_list");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("shared_todo_lists");
        Assertions.assertTrue(foreignKeysList.count() > 0);
    }

    @Test
    public void foreignKeyExistsBetweenShared_todo_lists_Users() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("shared_todo_lists")
                .filterByParentTable("users");

        Assertions.assertNotNull(foreignKeysList);
    }
    @Test
    public void foreignKeyExistsBetweenShared_todo_lists_Todo_lists() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("shared_todo_lists")
                .filterByParentTable("todo_lists");

        Assertions.assertNotNull(foreignKeysList);
    }
    @Test
    public void id_userReferencesUsers() throws SQLException {
        ForeignKey fkIdUser = info
                .getForeignKeys()
                .findByChildColumn(new Column("shared_todo_lists", "id_user"));

        Assertions.assertNotNull(fkIdUser);
        Assertions.assertTrue(fkIdUser
                .getParentColumn()
                .equals(new Column("users", "id"))
        );
    }
    @Test
    public void id_todo_listReferencesTodo_lists() throws SQLException {
        ForeignKey fkIdTodoLists = info
                .getForeignKeys()
                .findByChildColumn(new Column("shared_todo_lists", "id_todo_list"));

        Assertions.assertNotNull(fkIdTodoLists);
        Assertions.assertTrue(fkIdTodoLists
                .getParentColumn()
                .equals(new Column("todo_lists", "id"))
        );
    }
}