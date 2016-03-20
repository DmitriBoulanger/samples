package de.dbo.samples.javafx.spring2.ui;

import javafx.scene.Parent;

/**
 * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
 * а view - представление, нам предстоит использовать в 
 * точке входа {@link Application}.
 */
public class View {
    
    private Parent view;
    
    private Object controller;

    public View(Parent view, Object controller) {
        this.view = view;
        this.controller = controller;
    }

    public Parent getView() {
        return view;
    }

    public void setView(Parent view) {
        this.view = view;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}

