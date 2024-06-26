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

package com.start.client;

import com.dncomponents.UiField;
import com.dncomponents.client.components.AbstractCellComponent;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.entities.ItemId;
import com.dncomponents.client.components.sidemenu.SideMenu;
import com.dncomponents.client.dom.History;
import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.appview.AcceptsOneElement;
import com.dncomponents.client.views.appview.PlaceManager;
import com.start.client.cell.CellPlace;
import com.start.client.greeting.GreetingPlace;
import com.start.client.home.HomePlace;
import elemental2.dom.HTMLElement;
import elemental2.dom.Node;

public class MainApp implements AcceptsOneElement {

    @UiField
    HTMLElement contentWrapper;
    @UiField
    public SideMenu<ItemId> side;

    PlaceManager placeManager = new PlaceManager(this);
    HtmlBinder binder = HtmlBinder.create(MainApp.class, this);

    public MainApp() {
        binder.bind();
        init();
        side.setPlaceManager(placeManager);
        side.expandAll(false);
    }

    public void init() {
        placeManager.register(HomePlace.HomePlaceRegister.instance);
        placeManager.register(GreetingPlace.GreetingPlaceRegister.instance);
        placeManager.register(CellPlace.CellPlaceRegister.instance);
        placeManager.setHomePlace(HomePlace.class);
        History.fireCurrentHistoryState();
    }

    @Override
    public void setElement(IsElement element) {
        contentWrapper.innerHTML = "";
        contentWrapper.appendChild(element.asElement());
        AbstractCellComponent.resetScrollOnPage(contentWrapper);
    }

    public Node asElement() {
        return binder.asNode();
    }
}
