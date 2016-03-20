package de.dbo.javafx.spring1.gui;

public interface Configurable {

    void init();

    ScreensConfig getConfig();

    void setConfig(ScreensConfig config);

}