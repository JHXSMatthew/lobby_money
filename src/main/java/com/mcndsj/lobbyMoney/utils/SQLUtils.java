package com.mcndsj.lobbyMoney.utils;

import com.mcndsj.JHXSMatthew.Shared.MoneyManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 * Created by Matthew on 21/06/2016.
 */
public class SQLUtils {

    public static int getPoints(String name){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int returnValue = 0;
        try {
            connection = MoneyManager.getInstance().getConnection();
            if(connection == null || connection.isClosed()){
                return 0;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT points FROM `playerpoints` Where `playername`='"+name+"';");
            if(resultSet.next()){
                returnValue = resultSet.getInt("points");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) {e.printStackTrace();}
            if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
            if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
        }
        return returnValue;
    }


    public static int isPlayerInPointTable(String name){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int returnValue = -1;
        try {
            connection = MoneyManager.getInstance().getConnection();
            if(connection == null || connection.isClosed()){
                returnValue =  -1;
            }else {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT id FROM `playerpoints` Where `playername`='" + name + "';");
                if (resultSet.next()) {
                    returnValue = 1;
                }else{
                    returnValue = 0;
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    public static void setPoints(String player,int points){
        int value = isPlayerInPointTable(player);
        if( value == -1){
            return;
        }

        Connection connection = null;
        Statement statement = null;
        try {
            connection = MoneyManager.getInstance().getConnection();
            if(connection == null || connection.isClosed()){
                return;
            }
            statement = connection.createStatement();
            if(value == 0){
                statement.executeUpdate("INSERT INTO `playerpoints` (`playername`,`points`) VALUES ('"+player+"','"+ points + "');");
            }else{
                statement.executeUpdate("UPDATE `playerpoints` SET `points`='"+ points +"' Where `playername`='"+player+"';");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
