package com.skynet.javafx.views;

import org.springframework.context.annotation.Scope;
import com.skynet.javafx.jfxsupport.AbstractFxmlView;
import com.skynet.javafx.jfxsupport.FXMLView;

@FXMLView("/fxml/customer.fxml")
@Scope("prototype")
public class CustomerView extends AbstractFxmlView {

}
