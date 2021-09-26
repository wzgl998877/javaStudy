package com.zt.javastudy.mybatis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jdbc连接数据库
 *
 * @author zhengtao on 2021/9/16
 */
@Slf4j
public class JdbcTest {
    public static List<Map<String,Object>> queryForList(){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Map<String,Object>> resultList = new ArrayList<>();

        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://172.20.2.131:3506/journey?allowMultiQueries=true&Unicode=true&characterEncoding=UTF-8&useSSL=false";
            String user = "loan";
            String password = "JLpaymysql8.0!";
            // 获取数据库连接
            connection = DriverManager.getConnection(url,user,password);

            String sql = "select * from t_user where user_id = ? ";
            // 创建Statement对象（每一个Statement为一次数据库执行请求）
            stmt = connection.prepareStatement(sql);

            // 设置传入参数
            stmt.setString(1, "1027604989");

            // 执行SQL语句
            rs = stmt.executeQuery();

            // 处理查询结果（将查询结果转换成List<Map>格式）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();

            while(rs.next()){
                Map map = new HashMap();
                for(int i = 0;i < num;i++){
                    String columnName = rsmd.getColumnName(i+1);
                    map.put(columnName,rs.getString(columnName));
                }
                resultList.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭结果集
                if (rs != null) {
                    rs.close();
                }
                // 关闭执行
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<Map<String,Object>> list = queryForList();
        log.info("jdbc查询结果为:{}", JSONObject.toJSONString(list));
    }

}
