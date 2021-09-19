package me.xmrvizzy.skyblocker.chat;

import me.xmrvizzy.skyblocker.chat.filters.*;
import me.xmrvizzy.skyblocker.skyblock.dwarven.Fetchur;
import me.xmrvizzy.skyblocker.skyblock.dwarven.Puzzler;

import java.util.regex.Matcher;

public class ChatParser {
    private final ChatListener[] listeners = new ChatListener[]{
            new AoteFilter(),
            new ImplosionFilter(),
            new MoltenWaveFilter(),
            new HealFilter(),
            new AbilityFilter(),
            new AdFilter(),
            new Fetchur(),
            new Puzzler(),
    };

    public boolean shouldFilter(String message) {
        for (ChatListener listener : listeners) {
            if (listener.isEnabled()) {
                Matcher m = listener.getPattern().matcher(message);
                if (m.matches()) {
                    String[] groups = new String[m.groupCount() + 1];
                    for (int i = 0; i < groups.length; i++)
                        groups[i] = m.group(i);
                    return listener.onMessage(groups);
                }
            }
        }
        return false;
    }
}