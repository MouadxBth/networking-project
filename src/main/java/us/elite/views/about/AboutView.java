package us.elite.views.about;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import us.elite.views.MainLayout;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        add(new H2("This project was created by:"),
                new HorizontalLayout(
                        createProfileComponent("Mouad Bouthaich", "mouad.bouthaich@outlook.com"),
                        createProfileComponent("Younes Moustaquim", "younes.moustaquim@outlook.com")));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    public Component createProfileComponent(String name, String email) {
        final VerticalLayout layout = new VerticalLayout();

        Avatar avatar = new Avatar(name);

        Label nameField = new Label(name);

        nameField.getStyle().set("font-weight", "bold");

        Anchor anchor = new Anchor("mailto:" + email, email);

        layout.add(avatar, nameField, anchor);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        layout.getStyle().set("text-align", "center")
                .set("box-shadow", "0 2px 8px -2px var(--lumo-shade-50pct)")
                .set("border-radius", "25px");

        return layout;
    }
}
