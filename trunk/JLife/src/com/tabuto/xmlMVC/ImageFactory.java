/**
* @author Francesco di Dio
* Date: 27/dic/2010 16.19.47
* Titolo: ImageFactory.java
* Versione: 0.1.13.2 Rev.a:
*/


/*
 * Copyright (c) 2010 Francesco di Dio.
 * tabuto83@gmail.com 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

 /*
  * Inserisci qui breve descrizione in italiano della classe
  */

package com.tabuto.xmlMVC;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageFactory {
	
	// singleton
	private static ImageFactory uniqueInstance = new ImageFactory();
	// private constructor
	private ImageFactory() {};
	// return uniqueInstance
	public static ImageFactory getInstance() {
		return uniqueInstance;
	}
	
	public static final String ICONS_FILE = "16x16/";
	public static final String ACTION_ICONS_FILE = ICONS_FILE + "actions/";
	
	public static final String OPEN_FILE_ICON = ICONS_FILE + "filesystems/folder_open.png";
	public static final String SAVE_ICON = ACTION_ICONS_FILE + "filesave.png";
	public static final String PRINT_ICON = ACTION_ICONS_FILE + "fileprint.png";
	public static final String LOAD_DEFAULTS_ICON = ACTION_ICONS_FILE + "bookmarks_list_add.png";
	public static final String NEW_FILE_ICON = ACTION_ICONS_FILE + "filenew.png";
	public static final String SAVE_FILE_AS_ICON = ACTION_ICONS_FILE + "filesaveas.png";
	public static final String EDIT_ICON = ICONS_FILE + "apps/package_editors.png";
	public static final String ADD_ICON = ACTION_ICONS_FILE + "edit_add.png";
	public static final String DELETE_ICON = ACTION_ICONS_FILE + "cancel.png";
	public static final String MOVE_UP_ICON = ACTION_ICONS_FILE + "up.png";
	public static final String MOVE_DOWN_ICON = ACTION_ICONS_FILE + "down.png";
	public static final String DEMOTE_ICON = ACTION_ICONS_FILE + "demote.png";
	public static final String PROMOTE_ICON = ACTION_ICONS_FILE + "promote.png";
	public static final String DUPLICATE_ICON = ACTION_ICONS_FILE + "editcopy.png";
	public static final String IMPORT_ICON = ACTION_ICONS_FILE + "compfile.png";
	public static final String BIG_SOURCE_ICON = ACTION_ICONS_FILE + "source.png";
	public static final String COLLAPSED_ICON = ACTION_ICONS_FILE + "1rightarrow.png";
	public static final String NOT_COLLAPSED_ICON = ACTION_ICONS_FILE + "1downarrow.png";
	public static final String TWO_LEFT_ARROW = ACTION_ICONS_FILE + "2leftarrow.png";
	public static final String N0 = ACTION_ICONS_FILE + "no.png";
	
	public Icon getIcon(String iconPathName) {
		try {
			return new ImageIcon(ImageFactory.class.getResource(iconPathName));
		} catch (NullPointerException ex) {
			System.out.println("Could not find Icon at " + iconPathName);
			return null;
		}
	}

}
