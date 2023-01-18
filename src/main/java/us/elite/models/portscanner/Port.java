package us.elite.models.portscanner;

public record Port(int port, String description) {

    @Override
    public String toString() {
        return "Port{" +
                "port=" + port +
                ", description='" + description + '\'' +
                '}';
    }
}
