import com.lagou.edu.AutodeliverApplication8090;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {AutodeliverApplication8090.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AutodeliverApplicationTest {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void test() {
        List<ServiceInstance> instances = discoveryClient.getInstances("LAGOU-RESUME-SERVICE");
        for (ServiceInstance instance : instances) {

            System.out.println(instance);

            Map<String, String> metadata = instance.getMetadata();
            //获取元数据
            String cluster = metadata.get("cluster");
            System.out.println(cluster);
        }



    }

}
