package application.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "KHN auctions API controller")
@Controller
public class AuctionsController {

    @GetMapping(value = "*/auctions")
    public String auctionsCustomer() {
        return "auctions";
    }
}
