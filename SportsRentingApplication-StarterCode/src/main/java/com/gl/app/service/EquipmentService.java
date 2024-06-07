package com.gl.app.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gl.app.entity.Equipment;
import com.gl.app.exception.EquipmentNotFoundException;

public interface EquipmentService {
    public void addEquipment(Equipment equipment) throws SQLException, ClassNotFoundException;
    public void updateAvailability(int equipment_id, String  oldDate, String newDate) throws SQLException, EquipmentNotFoundException;
    public void deleteEquipment(int equipment_id) throws SQLException, EquipmentNotFoundException;
    public Equipment getEquipmentById(int id) throws SQLException, EquipmentNotFoundException;
    public List<Equipment> getAllEquipments() throws SQLException;
}
