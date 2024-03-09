package com.camdevhub.jiratoolbox;

import java.util.prefs.Preferences;

public class AppPreferences {
	private static final String PREFS_NODE_NAME = "com.camdevhub.jiratoolbox";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_URL = "url";
    private static final String KEY_REMEMBER = "remember";
    
    private final Preferences prefs;

    public AppPreferences() {
        prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
    }

    public void saveUsername(String username) {
        prefs.put(KEY_USERNAME, username);
    }

    public String loadUsername() {
        return prefs.get(KEY_USERNAME, "");
    }
    
    public void saveUrl(String url) {
        prefs.put(KEY_URL, url);
    }

    public String loadUrl() {
        return prefs.get(KEY_URL, "");
    }
    
    public void saveRemember(String remember) {
        prefs.put(KEY_REMEMBER, remember);
    }

    public boolean loadRemember() {
        return prefs.getBoolean(KEY_REMEMBER, false);
    }
}
