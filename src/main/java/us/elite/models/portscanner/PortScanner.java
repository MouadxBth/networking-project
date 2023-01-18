package us.elite.models.portscanner;

import us.elite.models.device.Device;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortScanner {

    private final ExecutorService executorService;

    public PortScanner() {
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public boolean scan(Device device, int port, int timeout) {
        if (device == null)
            return false;
        if (port < 0 || port > 65535 || timeout < 0)
            return false;
        try {
            Socket socket = new Socket();

            socket.connect(new InetSocketAddress(device.ip(), port), timeout);

            socket.close();
            return true;
        } catch (Exception ignored) {}
        return false;
    }

    public List<Port> scanAll(Device device, int start, int max, int timeout) {
        if ((start < 0 || start > 65535) || (max < 0 || max > 65535))
            return new ArrayList<>();
        final List<Port> result = new ArrayList<>();
        // print ->
        // System.out.println("RUNNING PORT SCAN ON: " + device.name() + " WITH IP: " + device.host());
        Arrays.stream(CommonPorts.values())
                .forEach(commonPorts -> {
                    if (scan(device, commonPorts.getPort().port(), timeout))
                        result.add(commonPorts.getPort());
                });

        while (start < max) {
            if (CommonPorts.get(start).isPresent())
                continue;
            if (scan(device, start, timeout))
                result.add(new Port(start, "Open Port"));
            start++;
        }
        return result;
    }
}
