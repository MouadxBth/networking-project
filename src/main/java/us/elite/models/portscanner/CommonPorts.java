package us.elite.models.portscanner;

import java.util.Arrays;
import java.util.Optional;

public enum CommonPorts {

    FTP_1(new Port(20, "FTP")),
    FTP_2(new Port(21, "FTP")),
    SSH(new Port(22, "Secure Shell")),
    SMTP(new Port(25, "Simple Mail Transfer Protocol")),
    DNS(new Port(53, "Domain Name System")),
    HTTP(new Port(80, "Hypertext Transfer Protocol")),
    NTP(new Port(123, "Network Time Protocol")),
    BGP(new Port(179, "Border Gateway Protocol")),
    HTTPS(new Port(443, "HTTP Secure")),
    ISAKMP(new Port(500, "Internet Security Association and Key Management Protocol ")),
    MSMTP(new Port(587, "Modern secure SMTP")),
    RDP(new Port(3389, "Remote Desktop Protocol"));

    private final Port port;

    CommonPorts(Port port) {
        this.port = port;
    }

    public Port getPort() {
        return port;
    }

    public static Optional<CommonPorts> get(int port) {
        return Arrays.stream(values())
                .filter(commonPort -> commonPort.getPort().port() == port)
                .findAny();
    }
}
