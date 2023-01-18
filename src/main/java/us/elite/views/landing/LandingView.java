package us.elite.views.landing;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import us.elite.views.MainLayout;

@PageTitle("Landing Page")
@Route(value = "", layout = MainLayout.class)
public class LandingView extends VerticalLayout {

    public LandingView() {
        setSpacing(false);

        add(new H2("Welcome to our Networking project!"));
        add(new Paragraph("You can select the utility you want to use from the navigation bar on the left side."));

        Button toggleButton = new Button("Dark Theme \uD83C\uDF19");
        toggleButton.addClickListener(event -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
                toggleButton.setText("Dark Theme \uD83C\uDF19");
            }
            else {
                themeList.add(Lumo.DARK);
                toggleButton.setText("Light Theme ☀️");
            }
        });
        add(toggleButton);


        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}