
import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DeliveryExpress
 */

@Data
public class TestClass {

    @DatabaseField(id = true)
    String id;

    @DatabaseField
    String name;
    
    @DatabaseField
    String edad;
    
     @DatabaseField
    int dom;

    public TestClass() {
    }
    
    

    public TestClass(String id, String name, String edad) {
        this.id = id;
        this.name = name;
        this.edad = edad;
    }
    
    

}
