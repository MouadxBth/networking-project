package us.elite.models.device;

import us.elite.models.CacheStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeviceFinder {

    private final CacheStore<List<Device>> cache = new CacheStore<>(180, TimeUnit.SECONDS);

    public List<Device> findDevices(String ip, int timeout) {
        List<Device> result = cache.get(ip);

        if (result != null && !result.isEmpty())
            return result;
        else
            result = new ArrayList<>();

        for (int index = 0; index < 255; index++) {
            result.add(new Device(ip + "." + index));
        }
        final List<Device> temp = new ArrayList<>();

        result.parallelStream().filter(device -> device.isReachable(timeout))
                        .forEach(temp::add);

        cache.add(ip, temp);
        return cache.get(ip);
    }

}
