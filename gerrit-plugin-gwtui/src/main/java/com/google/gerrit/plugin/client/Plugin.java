// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.plugin.client;

import com.google.gerrit.client.GerritUiExtensionPoint;
import com.google.gerrit.client.info.AccountInfo;
import com.google.gerrit.client.info.AccountPreferencesInfo;
import com.google.gerrit.client.info.ServerInfo;
import com.google.gerrit.plugin.client.extension.Panel;
import com.google.gerrit.plugin.client.screen.Screen;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wrapper around the plugin instance exposed by Gerrit.
 *
 * Listeners for events generated by the main UI must be registered
 * through this instance.
 */
public final class Plugin extends JavaScriptObject {
  private static final Plugin self = install(
      GWT.getModuleBaseURL() + GWT.getModuleName() + ".nocache.js");

  /** Obtain the plugin instance wrapper. */
  public static Plugin get() {
    return self;
  }

  /** Installed name of the plugin. */
  public final String getName() {
    return getPluginName();
  }

  /** Installed name of the plugin. */
  public final native String getPluginName()
  /*-{ return this.getPluginName() }-*/;

  /** Navigate the UI to the screen identified by the token. */
  public final native void go(String token)
  /*-{ return this.go(token) }-*/;

  /** Refresh the current UI. */
  public final native void refresh()
  /*-{ return this.refresh() }-*/;

  /** Refresh Gerrit's menu bar. */
  public final native void refreshMenuBar()
  /*-{ return this.refreshMenuBar() }-*/;

  /** @return the preferences of the currently signed in user, the default preferences if not signed in */
  public final native AccountPreferencesInfo getUserPreferences()
  /*-{ return this.getUserPreferences() }-*/;

  /** Refresh the user preferences of the current user. */
  public final native void refreshUserPreferences()
  /*-{ return this.refreshUserPreferences() }-*/;

  /** @return the server info */
  public final native ServerInfo getServerInfo()
  /*-{ return this.getServerInfo() }-*/;

  /** @return the current user */
  public final native AccountInfo getCurrentUser()
  /*-{ return this.getCurrentUser() }-*/;

  /** Check if user is signed in. */
  public final native boolean isSignedIn()
  /*-{ return this.isSignedIn() }-*/;

  /** Show message in Gerrit's ErrorDialog. */
  public final native void showError(String message)
  /*-{ return this.showError(message) }-*/;

  /**
   * Register a screen displayed at {@code /#/x/plugin/token}.
   *
   * @param token literal anchor token appearing after the plugin name. For
   *        regular expression matching use {@code screenRegex()} .
   * @param entry callback function invoked to create the screen widgets.
   */
  public final void screen(String token, Screen.EntryPoint entry) {
    screen(token, wrap(entry));
  }

  private final native void screen(String t, JavaScriptObject e)
  /*-{ this.screen(t, e) }-*/;

  /**
   * Register a screen displayed at {@code /#/x/plugin/regex}.
   *
   * @param regex JavaScript {@code RegExp} expression to match the anchor token
   *        after the plugin name. Matching groups are exposed through the
   *        {@code Screen} object passed into the {@code Screen.EntryPoint}.
   * @param entry callback function invoked to create the screen widgets.
   */
  public final void screenRegex(String regex, Screen.EntryPoint entry) {
    screenRegex(regex, wrap(entry));
  }

  private final native void screenRegex(String p, JavaScriptObject e)
  /*-{ this.screen(new $wnd.RegExp(p), e) }-*/;

  /**
   * Register a settings screen displayed at {@code /#/settings/x/plugin/token}.
   *
   * @param token literal anchor token appearing after the plugin name.
   * @param entry callback function invoked to create the settings screen widgets.
   */
  public final void settingsScreen(String token, String menu, Screen.EntryPoint entry) {
    settingsScreen(token, menu, wrap(entry));
  }

  private final native void settingsScreen(String t, String m, JavaScriptObject e)
  /*-{ this.settingsScreen(t, m, e) }-*/;

  /**
   * Register a panel for a UI extension point.
   *
   * @param extensionPoint the UI extension point for which the panel should be
   *        registered.
   * @param entry callback function invoked to create the panel widgets.
   */
  public final void panel(GerritUiExtensionPoint extensionPoint, Panel.EntryPoint entry) {
    panel(extensionPoint.name(), wrap(entry));
  }

  private final native void panel(String i, JavaScriptObject e)
  /*-{ this.panel(i, e) }-*/;

  protected Plugin() {
  }

  native void _initialized() /*-{ this._success = true }-*/;
  native void _loaded() /*-{ this._loadedGwt() }-*/;
  private static final native Plugin install(String u)
  /*-{ return $wnd.Gerrit.installGwt(u) }-*/;

  private static final native JavaScriptObject wrap(Screen.EntryPoint b) /*-{
    return $entry(function(c){
      b.@com.google.gerrit.plugin.client.screen.Screen.EntryPoint::onLoad(Lcom/google/gerrit/plugin/client/screen/Screen;)(
        @com.google.gerrit.plugin.client.screen.Screen::new(Lcom/google/gerrit/plugin/client/screen/Screen$Context;)(c));
    });
  }-*/;

  private static final native JavaScriptObject wrap(Panel.EntryPoint b) /*-{
    return $entry(function(c){
      b.@com.google.gerrit.plugin.client.extension.Panel.EntryPoint::onLoad(Lcom/google/gerrit/plugin/client/extension/Panel;)(
        @com.google.gerrit.plugin.client.extension.Panel::new(Lcom/google/gerrit/plugin/client/extension/Panel$Context;)(c));
    });
  }-*/;
}
