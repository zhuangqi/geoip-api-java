package cn.wdz.geolite2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.wdz.geolite2.service.GeoipService;
import cn.wdz.geolite2.service.GeoipService.IpAddressInfo;

@RestController
public class GeoipController {
    
    @Autowired
    private GeoipService geoipService;


    @GetMapping(value = "/qryip")
    public ResponseEntity<IpAddressInfo> search(@RequestParam String ip) {
        return ResponseEntity.ok().body(geoipService.search(ip));
    }

}
