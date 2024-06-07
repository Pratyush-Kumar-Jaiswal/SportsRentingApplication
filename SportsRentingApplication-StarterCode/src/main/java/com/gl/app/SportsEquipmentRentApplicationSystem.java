package com.gl.app;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gl.app.dao.EquipmentDAO;
import com.gl.app.dao.impl.EquipmentDAOImpl;
import com.gl.app.entity.Equipment;
import com.gl.app.exception.EquipmentNotFoundException;
import com.gl.app.service.EquipmentService;
import com.gl.app.service.impl.EquipmentServiceImpl;
import com.gl.app.util.SportsUtil;

import java.text.ParseException;
import java.util.function.Consumer;


public class SportsEquipmentRentApplicationSystem {
    public static void main(String[] args) throws ParseException, SQLException, ClassNotFoundException, EquipmentNotFoundException {

        EquipmentDAO equipmentDAO=new EquipmentDAOImpl();
        EquipmentService equipmentService = new EquipmentServiceImpl(equipmentDAO);
        SportsUtil sportsUtil=new SportsUtil();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("1. Add Equipment");
            System.out.println("2. Update Equipment Availability");
            System.out.println("3. Delete Equipment");
            System.out.println("4. Get EquipmentBy Id");
            System.out.println("5. List All Equipments");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            String dateStr = null;
            switch (choice) {

                case 1:
                    //Write code to add equipment
                    dateStr= String.valueOf(LocalDate.now());
//                    System.out.println(dateStr);
                    System.out.println("Enter the Equipment details");
                    System.out.println("Enter the type");
                    String type= scanner.next();
                    System.out.println("Enter the brand");
                    String brand=scanner.next();
                    System.out.println("Enter the model");
                    String model=scanner.next();
                    System.out.println("Enter the rent amount");
                    Double rent=scanner.nextDouble();
                    Equipment equipment=new Equipment(type,brand,model,rent, dateStr);
                    equipmentService.addEquipment(equipment);
                    break;


                case 2:
                    //Write code to update equipment availability
                    System.out.println("Enter Equipment id");
                    int id=scanner.nextInt();
                    System.out.println("Enter the old date(YYYY-MM-DD)");
                    String oldDate=scanner.next();
                    System.out.println("Enter the new date(YYYY-MM-DD)");
                    String newDate=scanner.next();
                    equipmentService.updateAvailability(id,oldDate,newDate);
                    break;

                case 3:
                    //Write code to delete equipment
                    System.out.println("Enter equipment id to be deleted");
                    int delid=scanner.nextInt();
                    equipmentService.deleteEquipment(delid);
                    break;
                case 4:
                    //Write code to get equipment by id
                    System.out.println("Enter the Equipment id to be fetched");
                    int idf=scanner.nextInt();
                    Equipment e=equipmentService.getEquipmentById(idf);
//                    System.out.println(e);
                    Arrays.stream(e.getClass().getDeclaredFields()).forEach(field -> {
                        field.setAccessible(true);
                        try{
                            System.out.println(field.getName()+" : "+field.get(e));
                        }
                        catch (IllegalAccessException err){
                            err.printStackTrace();
                        }
                    });
                    break;
                case 5:
                    //Write code to list all equipments
                    List<Equipment> list=equipmentService.getAllEquipments();
//                    System.out.println(list);
                    Consumer<Equipment> printEquipment=equip1-> System.out.println(equip1);
                    list.forEach(printEquipment);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
