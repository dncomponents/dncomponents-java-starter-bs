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

package com.start.client.cell;

import com.dncomponents.UiField;
import com.dncomponents.client.components.ListData;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.entities.ItemId;
import com.dncomponents.client.components.textarea.TextArea;
import com.dncomponents.client.views.IsElement;
import com.start.client.helper.Person;
import com.start.client.helper.TestingHelper;
import elemental2.dom.HTMLElement;

public class ListAppView implements IsElement {
    @UiField
    HTMLElement root;
    @UiField
    ListData<ItemId, String> list;
    @UiField
    ListData<Person, String> listPeople;
    @UiField
    TextArea logTa;
    @UiField
    TextArea logTa2;

    public ListAppView() {
        HtmlBinder.create(ListAppView.class, this).bind();
        init();
    }


    private void init() {
        list.getSelectionModel().addSelectionHandler(evt ->
                evt.getSelectedItem().forEach(p ->
                        logTa.append(p.getId() + "\n")));
        listPeople.getRowCellConfig().setFieldSetter((person, s) -> person.setName(s));
        listPeople.getRowCellConfig().setFieldGetter(Person::getName);
        listPeople.getRowCellConfig()
                .setCellRenderer(r -> r.valuePanel.innerHTML = "<b>" + r.value + "</b>");
        listPeople.getSelectionModel().addSelectionHandler(evt ->
                evt.getSelectedItem().forEach(p ->
                        logTa2.append(p.getName() + " " + p.getAge() + "\n")
                ));

        listPeople.setRowsData(TestingHelper.getPeople(300));
        listPeople.drawData();
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    private static ListAppView instance;

    public static ListAppView getInstance() {
        if (instance == null)
            instance = new ListAppView();
        return instance;
    }

}
