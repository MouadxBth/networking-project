package us.elite.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.elite.models.device.Device;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {

    @Bean
    public CacheStore<List<Device>> devicesCache() {
        return new CacheStore<>(180, TimeUnit.SECONDS);
    }

}