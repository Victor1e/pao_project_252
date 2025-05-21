package Servicii;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericDAO<T> {

    protected Connection connection;

    public GenericDAO() {
        this.connection = Database.DBConnection.getConnection();
    }

    public abstract void create(T obj);
    public abstract T read(int id);
    public abstract void update(T obj);
    public abstract void delete(int id);
}
