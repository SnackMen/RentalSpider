
import com.ws.config.MySQLDataConfiguration;
import com.ws.dao.HouseDAOImpl;
import com.ws.dao.IHouseDAO;
import com.ws.dto.HouseDTO;
import com.ws.service.HouseServiceImpl;
import com.ws.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by laowang on 17-4-16.
 */
public class Test {



    public static void main(String []args){

//        ApplicationContext ctx = new AnnotationConfigApplicationContext(MySQLDataConfiguration.class);
//
//       HouseDAOImpl houseService = (HouseDAOImpl) ctx.getBean("getHouseDAO");

//        System.out.println(houseService);
//
//        houseService.getId(12444);
//        HouseServiceImpl houseService1 = new HouseServiceImpl();
//        houseService.searchById(124444);
//        IHouseService houseService = new HouseServiceImpl();
//        HouseDTO[] houseDTOS = houseService.searchAll("58");
//        for(int i=0;i<10;i++) {
//            System.out.println(houseDTOS[i].toString());
//        }
    }
}
