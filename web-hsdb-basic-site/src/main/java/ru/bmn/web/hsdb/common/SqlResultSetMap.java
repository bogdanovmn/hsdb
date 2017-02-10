package ru.bmn.web.hsdb.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 */
public class SqlResultSetMap {
    private final ResultSet resultSet;

    public SqlResultSetMap(final ResultSet rs) {
        this.resultSet = rs;
    }

    public Properties toProperties() throws SQLException {
        ResultSetMetaData meta = this.resultSet.getMetaData();
        int columsCnt = meta.getColumnCount();

        Properties result = new Properties();
        for (int i = 1; i <= columsCnt; i++) {
            result.put(meta.getColumnName(i), this.resultSet.getString(i));
        }

        return result;
    }
}
