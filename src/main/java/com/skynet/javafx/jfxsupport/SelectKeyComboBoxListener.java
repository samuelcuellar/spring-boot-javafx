package com.skynet.javafx.jfxsupport;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SelectKeyComboBoxListener implements EventHandler<KeyEvent> {

	private StringBuilder sb = new StringBuilder();

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void handle(KeyEvent event) {
		sb.delete(0, sb.length()); // TODO: save last typed keys
		if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.TAB) {
			return;
		} else if (event.getCode() == KeyCode.BACK_SPACE && sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		} else {
			sb.append(event.getText());
		}

		if (sb.length() == 0) {
			return;
		}

		ComboBox comboBox = ((ComboBox) event.getSource());
		boolean found = false;
		ObservableList items = comboBox.getItems();
		for (int i = 0; i < items.size(); i++) {
			String strValue = comboBox.getConverter().toString(items.get(i));
			if (strValue.toLowerCase().startsWith(sb.toString().toLowerCase())) {
				comboBox.getSelectionModel().select(i);
				ListView lv = (ListView) ((ComboBoxListViewSkin<?>) comboBox.getSkin()).getPopupContent();
                lv.scrollTo(lv.getSelectionModel().getSelectedIndex());
				found = true;
				break;
			}
		}

		if (!found && sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
