/*
 * Copyright 2023 dncomponents
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.start.client.home;

import com.dncomponents.UiField;
import com.dncomponents.client.components.button.Button;
import com.dncomponents.client.components.checkbox.CheckBoxSelectionGroup;
import com.dncomponents.client.components.checkbox.RadioSelectionGroup;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.entities.ItemId;
import com.dncomponents.client.components.textarea.TextArea;
import com.dncomponents.client.dom.handlers.ClickHandler;
import com.dncomponents.client.dom.handlers.MouseEnterHandler;
import com.dncomponents.client.dom.handlers.MouseLeaveHandler;
import com.dncomponents.client.views.IsElement;
import com.start.client.helper.Fruit;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;

public class HomeViewImpl implements IsElement {
    private static HomeViewImpl instance;

    @UiField
    HTMLElement root;
    @UiField
    public Button btnPrimary;
    @UiField
    public Button btnSecondary;
    @UiField
    public HTMLDivElement divElement;
    @UiField
    RadioSelectionGroup<ItemId> radioGroup;
    @UiField
    HTMLElement checkBoxPanel;
    @UiField
    public TextArea eventsTa;

    {
        HtmlBinder.get(HomeViewImpl.class, this).bind();
    }

    public HomeViewImpl() {
        init();
    }

    private void init() {
        btnPrimary.addClickHandler(evt -> eventsTa.setValue("button clicked"));
        btnPrimary.addHandler((MouseEnterHandler) evt -> eventsTa.setValue("mouse enter"));
        btnPrimary.addHandler((MouseLeaveHandler) evt -> eventsTa.setValue("mouse leave"));
        //div
        ((ClickHandler) mouseEvent -> eventsTa.setValue("div clicked")).addTo(divElement);
        //radio
        radioGroup.addSelectionHandler(evt ->
                eventsTa.setValue("Selection: " + evt.getSelectedItem().getUserObject().getId()));
        //checkbox
        CheckBoxSelectionGroup<Fruit> checkBoxGroup = new CheckBoxSelectionGroup<>();
        checkBoxGroup.addEntityItems(Fruit.getFruits(4));
        checkBoxGroup.getItems().forEach(e -> checkBoxPanel.appendChild(e.asElement()));
        checkBoxGroup.getEntitySelectionModel().addSelectionHandler(evt -> {
            eventsTa.setValue("");
            evt.getSelectedItem().forEach(p -> eventsTa.append(p + "\n"));
        });
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    public static HomeViewImpl getInstance() {
        if (instance == null) instance = new HomeViewImpl();
        return instance;
    }
}
