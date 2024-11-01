
import com.deliveryexpress.sdeu.database.GenericDao;
import com.deliveryexpress.sdeu.database.SqliteDataBaseInteraction;
import com.deliveryexpress.sdeu.database.TableValidator;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DeliveryExpress
 */
public class TestDataBase {

    SqliteDataBaseInteraction databse;

    public static void main(String args[]) {
        try {
            SqliteDataBaseInteraction.init(SqliteDataBaseInteraction.getDATABASE_URL());

            GenericDao<TestClass, String> testClassDao = new GenericDao<>(SqliteDataBaseInteraction.getConnectionSource(),
                    TestClass.class);

            TableUtils.createTableIfNotExists(SqliteDataBaseInteraction.getConnectionSource(), TestClass.class);
            TableValidator.verifyTable(testClassDao, TestClass.class);
            
            TestClass testClass = new TestClass("1","carlos","33");
            testClass.dom = 11;
            testClassDao.create(testClass);
        } catch (SQLException ex) {
            Logger.getLogger(TestDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
