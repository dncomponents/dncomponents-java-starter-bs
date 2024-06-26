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
import com.dncomponents.client.components.Tree;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.textarea.TextArea;
import com.dncomponents.client.views.IsElement;
import com.start.client.helper.Fruit;
import elemental2.dom.HTMLElement;

public class TreeAppView implements IsElement {
    @UiField
    HTMLElement root;
    @UiField
    Tree<Object> tree;
    @UiField
    TextArea logTa;

    public TreeAppView() {
        HtmlBinder.create(TreeAppView.class, this).bind();
        init();
    }

    private void init() {
        tree.setRoot(Fruit.getFruitsTree());
        tree.drawData();
        tree.getSelectionModel().addSelectionHandler(evt ->
                evt.getSelectedItem().forEach(p ->
                        logTa.append(p.getUserObject() + "\n")));
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    private static TreeAppView instance;

    public static TreeAppView getInstance() {
        if (instance == null)
            instance = new TreeAppView();
        return instance;
    }

}
