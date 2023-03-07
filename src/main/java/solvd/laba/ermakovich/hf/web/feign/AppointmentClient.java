package solvd.laba.ermakovich.hf.web.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@FeignClient(name = "HOSPITAL-APPOINTMENT")
public interface AppointmentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users")
    Boolean isDoctorExist(@RequestParam("externalId") UUID externalId);

}
