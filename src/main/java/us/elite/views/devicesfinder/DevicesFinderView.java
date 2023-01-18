package us.elite.views.devicesfinder;

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
import us.elite.models.device.DeviceFinder;
import us.elite.views.MainLayout;

import java.util.ArrayList;
import java.util.List;

import static us.elite.views.Utils.getNotification;

@PageTitle("Devices Finder")
@Route(value = "devicesfinder", layout = MainLayout.class)
public class DevicesFinderView extends VerticalLayout {

    private final Grid<Device> grid;
    private static final DeviceFinder deviceFinder = new DeviceFinder();

    public DevicesFinderView() {
        setSpacing(false);

        this.grid = getGrid();

        add(getViewComponents(), grid);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private Component getViewComponents() {
        final TextField ipField = new TextField("Ip:"),
                timeoutField = new TextField("Timeout:");

        ipField.setPlaceholder("Enter the ip");
        ipField.setRequired(true);
        ipField.setClearButtonVisible(true);

        timeoutField.setPlaceholder("Enter the timeout");
        timeoutField.setClearButtonVisible(true);

        final Button submit = new Button("Search");

        submit.addClickListener(event -> {
            grid.setItems(new ArrayList<>());

            int timeout = 200;

            if (!timeoutField.getValue().isBlank())
                timeout = Integer.parseInt(timeoutField.getValue());

            final List<Device> devices = deviceFinder.findDevices(ipField.getValue(), timeout);

            if (devices == null || devices.isEmpty()) {
                getNotification("No device was found", 3, NotificationVariant.LUMO_ERROR);
            return;
            }

            grid.setItems(devices);

        });

        final HorizontalLayout layout = new HorizontalLayout(ipField, submit);
        layout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        return layout;
    }


    private Grid<Device> getGrid() {
        final Grid<Device> grid = new Grid<>(Device.class);
        grid.removeAllColumns();

        grid.addColumn(Device::ip).setHeader("IP");
        grid.addColumn(Device::name).setHeader("Name");

        grid.getColumns().forEach(column -> column.setAutoWidth(true));

        return grid;
    }

}
