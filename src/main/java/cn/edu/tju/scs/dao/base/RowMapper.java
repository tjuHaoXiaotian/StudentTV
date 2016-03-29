package cn.edu.tju.scs.dao.base;

/**
 * Created by jack on 2015/11/5.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper
 */
public interface RowMapper
{
    public Object mapRow(ResultSet rs, int index)
            throws SQLException;
}