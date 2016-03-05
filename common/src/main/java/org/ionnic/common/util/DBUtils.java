package org.ionnic.common.util;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author apple
 *
 */
public class DBUtils {

    /**
     * @param ps
     * @param param
     * @return
     * @throws SQLException
     */
    public static void format(PreparedStatement ps, Object... param) throws SQLException {
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i + 1, param[i]);
            }
        }
    }

    /**
     * @param result
     * @return
     */
    public static List<Map<String, Object>> toList(ResultSet result) {
        Map<String, Object> record = null;
        List<String> columnNameList = new ArrayList<String>();
        List<Map<String, Object>> recordSet = new ArrayList<Map<String, Object>>();
        try {

            int count = result.getMetaData().getColumnCount();

            for (int i = 1; i <= count; i++) {
                String columnName = result.getMetaData().getColumnName(i).toLowerCase();
                columnNameList.add(columnName);
            }
            while (result.next()) {
                record = new HashMap<String, Object>();
                for (String columnName : columnNameList)
                    record.put(columnName, result.getObject(columnName));

                recordSet.add(record);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return recordSet;
    }

    /**
     * @param result
     * @param classType
     * @return
     */
    public static <T> List<T> toList(ResultSet result, Class<T> classType) {
        List<String> columnNameList = new ArrayList<String>();
        List<T> entityList = new ArrayList<T>();

        try {
            Field[] fields = classType.getDeclaredFields();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                String columnName = result.getMetaData().getColumnName(i).toUpperCase();
                columnNameList.add(columnName);
            }
            while (result.next()) {
                T entity = classType.newInstance();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (columnNameList.contains(fieldName.toUpperCase())) {
                        BeanUtils.setProperty(entity, fieldName, result.getObject(fieldName));
                    }
                }
                entityList.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return entityList;
    }

    /**
     * @param result
     * @return
     */
    public static Map<String, Object> toMap(ResultSet result) {
        List<String> columnNameList = new ArrayList<String>();
        Map<String, Object> record = null;
        try {
            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                String columnName = result.getMetaData().getColumnName(i).toLowerCase();
                columnNameList.add(columnName);
            }
            while (result.next()) {
                record = new HashMap<String, Object>();
                for (String columnName : columnNameList) {
                    record.put(columnName, result.getObject(columnName));
                }
                break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return record;
    }

    /**
     * @param result
     * @param classType
     * @return
     */
    public static <T> T toBean(ResultSet result, Class<T> classType) {

        List<T> entityList = toList(result, classType);
        if (entityList == null || entityList.size() == 0) {
            return null;
        }
        return entityList.get(0);
    }
}
