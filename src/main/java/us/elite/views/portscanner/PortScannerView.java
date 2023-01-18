package us.elite.views.portscanner;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import us.elite.models.device.Device;
import us.elite.models.portscanner.Port;
import us.elite.models.portscanner.PortScanner;
import us.elite.views.MainLayout;

import java.util.ArrayList;

import static us.elite.views.Utils.getNotification;

@PageTitle("Port Scanner")
@Route(value = "portscanner", layout = MainLayout.class)
public class PortScannerView extends VerticalLayout {

    private final Grid<Port> grid;
    private final PortScanner portScanner;

    public PortScannerView() {
        setSpacing(false);

        this.grid = getGrid();
        this.portScanner = new PortScanner();

        add(getViewComponents(), grid);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private Component getViewComponents() {
        final TextField searchField = new TextField("Ip:"),
                portStartField = new TextField("Port start:"),
                portEndField = new TextField("Port end:"),
                timeoutField = new TextField("Timeout:");

        searchField.setPlaceholder("Enter the ip");
        searchField.setRequired(true);
        searchField.setClearButtonVisible(true);

        portStartField.setPlaceholder("Enter the start of ports");
        portStartField.setClearButtonVisible(true);

        portEndField.setPlaceholder("Enter the end of ports");
        portEndField.setClearButtonVisible(true);

        timeoutField.setPlaceholder("Enter the timeout");
        timeoutField.setClearButtonVisible(true);

        final Button submit = new Button("Search");

        submit.addClickListener(event -> {
            grid.setItems(new ArrayList<>());

            final Device device = new Device(searchField.getValue());
            int timeout = 200;
            int portStart = 0;
            int portEnd = 5;

            if (!timeoutField.getValue().isBlank())
                timeout = Integer.parseInt(timeoutField.getValue());

            if (!device.isReachable(timeout)) {
                getNotification("Device was not found", 3, NotificationVariant.LUMO_ERROR);
                return;
            }

            if (!portStartField.getValue().isBlank())
                portStart = Integer.parseInt(portStartField.getValue());
            if (!portEndField.getValue().isBlank())
                portEnd = Integer.parseInt(portEndField.getValue());


            grid.setItems(portScanner.scanAll(device, portStart, portEnd, timeout));

        });

        final HorizontalLayout layout = new HorizontalLayout(searchField, portStartField, portEndField, submit);
        layout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        return layout;
    }


    private Grid<Port> getGrid() {
        final Grid<Port> grid = new Grid<>(Port.class);
        grid.removeAllColumns();

        grid.addColumn(Port::port).setHeader("Port");
        grid.addColumn(Port::description).setHeader("Description");

        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        return grid;
    }


}
