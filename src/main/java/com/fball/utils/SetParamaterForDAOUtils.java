package com.fball.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetParamaterForDAOUtils {

    //<editor-fold defaultstate="collapsed" desc="SET_PARAMATER">
    public static void setParamater(PreparedStatement statement, Object... paramaters) {
        try {
            for (int i = 0; i < paramaters.length; i++) {
                Object paramater = paramaters[i];
                int index = i + 1;
                if (paramater instanceof Long) {
                    statement.setLong(index, (Long) paramater);
                } else if (paramater instanceof String) {
                    statement.setString(index, (String) paramater);
                } else if (paramater instanceof Integer) {
                    statement.setInt(index, (Integer) paramater);
                } else if (paramater instanceof Boolean) {
                    statement.setBoolean(index, (Boolean) paramater);
                } else if (paramater instanceof Double) {
                    statement.setDouble(index, (Double) paramater);
                }
            }
        } catch (SQLException e) {
        }
    }
    //</editor-fold>

}
