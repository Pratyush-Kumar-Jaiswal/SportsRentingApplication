package com.gl.app.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gl.app.dao.EquipmentDAO;
import com.gl.app.dao.impl.EquipmentDAOImpl;
import com.gl.app.entity.Equipment;
import com.gl.app.exception.EquipmentNotFoundException;
import com.gl.app.service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService{

    EquipmentDAO equipmentDAO=new EquipmentDAOImpl();

    public EquipmentServiceImpl() {
        super();
    }

    public EquipmentServiceImpl(EquipmentDAO equipmentDAO) {
        super();
        this.equipmentDAO = equipmentDAO;
    }



    @Override
    public void addEquipment(Equipment equipment) throws SQLException, ClassNotFoundException {
        // TODO Auto-generated method stub
        equipmentDAO.addEquipment(equipment);

    }


    @Override
    public void updateAvailability(int equipment_id, String  oldDate, String newDate) throws SQLException, EquipmentNotFoundException {
        // TODO Auto-generated method stub
        //write code here
        equipmentDAO.updateAvailability(equipment_id,oldDate,newDate);

    }


    @Override
    public Equipment getEquipmentById(int id) throws SQLException, EquipmentNotFoundException {
        // TODO Auto-generated method stub
        return equipmentDAO.getEquipmentById(id);//Write code here
    }


    @Override
    public void deleteEquipment(int equipment_id) throws SQLException, EquipmentNotFoundException {
        // TODO Auto-generated method stub
        //write code here
        equipmentDAO.deleteEquipment(equipment_id);
    }


    @Override
    public List<Equipment> getAllEquipments() throws SQLException {
        // TODO Auto-generated method stub
        return equipmentDAO.getAllEquipments();//write code here
    }


}
