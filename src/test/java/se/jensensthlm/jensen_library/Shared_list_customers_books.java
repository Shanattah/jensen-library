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
    public void id_customerIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("shared_list_customers_books", "id_customer");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
    @Test
    public void id_bookIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("shared_list_customers_books", "id_book");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
    @Test
    public void foreignKeyExists() throws SQLException {
        ForeignKeysList foreignKeysList = info.getForeignKeys().filterByChildTable("shared_list_customers_books");
        Assertions.assertTrue(foreignKeysList.count() > 0);
    }

    @Test
    public void foreignKeyExistsBetweenShared_list_customers_books_Books() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("shared_list_customers_books")
                .filterByParentTable("books");

        Assertions.assertNotNull(foreignKeysList);
    }
    @Test
    public void foreignKeyExistsBetweenShared_list_customers_books_Customers() throws SQLException {
        ForeignKeysList foreignKeysList = info
                .getForeignKeys()
                .filterByChildTable("shared_list_customers_books")
                .filterByParentTable("customers");

        Assertions.assertNotNull(foreignKeysList);
    }
    @Test
    public void id_bookReferencesBooks() throws SQLException {
        ForeignKey fkIdBook = info
                .getForeignKeys()
                .findByChildColumn(new Column("shared_list_customers_books", "id_book"));

        Assertions.assertNotNull(fkIdBook);
        Assertions.assertTrue(fkIdBook
                .getParentColumn()
                .equals(new Column("books", "id"))
        );
    }
    @Test
    public void id_customersReferencesCustomers() throws SQLException {
        ForeignKey fkIdTodoLists = info
                .getForeignKeys()
                .findByChildColumn(new Column("shared_list_customers_books", "id_customer"));

        Assertions.assertNotNull(fkIdTodoLists);
        Assertions.assertTrue(fkIdTodoLists
                .getParentColumn()
                .equals(new Column("customers", "id"))
        );
    }
    @Test
    public void loanDaysIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("shared_list_customers_books", "loan_days");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("int", column.getType());
    }
    @Test
    public void loanDateIsInt() throws SQLException {
        ColumnType column = info.getTypes().findByColumn("shared_list_customers_books", "loan_date");
        Assertions.assertNotNull(column);
        Assertions.assertEquals("date", column.getType());
    }
}