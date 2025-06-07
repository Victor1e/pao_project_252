package Servicii;

import java.sql.Connection;

public abstract class GenDB<T> {

    protected Connection connection;

    public GenDB() {
        this.connection = Database.DBConnection.getConnection();
    }
    /*
    public GenDB(Connection connection) {
        this.connection = Database.DBConnection.getConnection();
    }*/

    public abstract void create(T obj);
    public abstract T read(int id);
    public abstract void update(T obj);
    public abstract void delete(int id);
}
