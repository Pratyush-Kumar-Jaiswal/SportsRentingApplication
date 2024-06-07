package com.gl;

import com.gl.app.dao.EquipmentDAO;
import com.gl.app.dao.impl.EquipmentDAOImpl;
import com.gl.app.entity.Equipment;
import com.gl.app.exception.EquipmentNotFoundException;
import com.gl.app.service.impl.EquipmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class EquipmentServiceTest {
    private Equipment expectedEquipment;

    @InjectMocks
    private EquipmentServiceImpl equipmentServiceImpl;

    @Mock
    EquipmentDAO equipmentDAO=new EquipmentDAOImpl();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        equipmentServiceImpl=new EquipmentServiceImpl(equipmentDAO);
        expectedEquipment=new Equipment(2,"ball","kukubura","44r4",50.0,"2020-04-26");
    }

    @Test
    public void testGetEquipmentById() throws SQLException, ClassNotFoundException, EquipmentNotFoundException {
        when(equipmentDAO.getEquipmentById(2)).thenReturn(expectedEquipment);
        Equipment actualEquipment=equipmentServiceImpl.getEquipmentById(2);
        Assertions.assertEquals(expectedEquipment,actualEquipment);
    }
}
