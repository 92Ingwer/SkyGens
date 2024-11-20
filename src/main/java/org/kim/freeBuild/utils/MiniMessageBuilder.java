package org.kim.freeBuild.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniMessageBuilder {
    private Component component;
    public MiniMessageBuilder(String input) {
        this.component = MiniMessage.miniMessage().deserialize(input);
    }
    public Component get() {
        return component;
    }
}
