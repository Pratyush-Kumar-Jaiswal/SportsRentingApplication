package com.gl.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.gl.app.dao.EquipmentDAO;
import com.gl.app.entity.Equipment;
import com.gl.app.exception.EquipmentNotFoundException;
import com.gl.app.util.SportsUtil;

public class EquipmentDAOImpl implements EquipmentDAO {

    public EquipmentDAOImpl(){

    }
    Connection connection;
    SportsUtil sportsUtil=new SportsUtil();


    @Override
    public void addEquipment(Equipment equipment) throws SQLException, ClassNotFoundException {
        //Write code here to add the equipment to the database
        connection= SportsUtil.getConnection();
        String query="insert into equipment(equipmentid,type,brand,model,rentamount,availabledate) values(?,?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1,sportsUtil.generateUniqueId("equipmentid","equipment",1));
        statement.setString(2,equipment.getType());
        statement.setString(3,equipment.getBrand());
        statement.setString(4,equipment.getModel());
        statement.setDouble(5,equipment.getRentAmount());
        statement.setDate(6, java.sql.Date.valueOf(equipment.getDateAvailability()));
        statement.execute();
        System.out.println("Equipment added");
    }


    @Override
    public void updateAvailability(int equipment_id, String oldDate, String newDate) throws SQLException, EquipmentNotFoundException {
        //Write code here to update the availability of the equipment in the database
        connection =SportsUtil.getConnection();
        String query="update equipment set availabledate=? where equipmentid=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setDate(1, Date.valueOf(newDate));
        statement.setInt(2,equipment_id);
        int res=statement.executeUpdate();
        if(res==0){
            throw new EquipmentNotFoundException("Equipment does not exixits");
        }
        System.out.println("Equipment Updated");
    }

    @Override
    public Equipment getEquipmentById(int id) throws SQLException, EquipmentNotFoundException {

        //Write code here to get the equipment by id from the database
        connection=SportsUtil.getConnection();
        String query="select * from equipment where equipmentid=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet set=statement.executeQuery();
        if(!set.next()){
            throw new EquipmentNotFoundException("Equipment doesnot exists");
        }
        return new Equipment(set.getInt("equipmentid"),set.getString("type"),set.getString("brand"),set.getString("model"),set.getDouble("rentamount"),String.valueOf(set.getDate("availabledate")));
    }


    @Override
    public void deleteEquipment(int equipment_id) throws SQLException, EquipmentNotFoundException {
        // TODO Auto-generated method stub
        //Write code here to delete the equipment from the database
        connection=SportsUtil.getConnection();
        String query="delete from equipment where equipmentid=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1,equipment_id);
        int res=statement.executeUpdate();
        if(res==0){
            throw new EquipmentNotFoundException("Equipment does not exists");
        }
        System.out.println("Equipment deleted");
    }


    @Override
    public List<Equipment> getAllEquipments() throws SQLException {
        // TODO Auto-generated method stub
        List<Equipment> list=new ArrayList<>();
        //Write code here to get all the equipments from the database
        connection=SportsUtil.getConnection();
        String query="select * from equipment";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet set=statement.executeQuery();
        while (set.next()){
            list.add(new Equipment(set.getInt("equipmentid"),
                    set.getString("type"),
                    set.getString("brand"),
                    set.getString("model"),
                    set.getDouble("rentamount"),
                    String.valueOf(set.getDate("availabledate"))));
        }
        return list;
    }



}

