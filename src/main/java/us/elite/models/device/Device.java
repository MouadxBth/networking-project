package us.elite.models.device;

import java.net.InetAddress;

public class Device {

    private final String ip;
    private String name = "";

    public Device(String ip) {
        this.ip = ip;
        this.name = ip;
    }

    public boolean isReachable(int timeout) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isReachable(timeout)) {
                name = address.getHostName();
                System.out.println(name);
                return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    public String ip() {
        return ip;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "Device{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
