/*
 * OpenFaces - JSF Component Library 2.0
 * Copyright (C) 2007-2010, TeamDev Ltd.
 * licensing@openfaces.org
 * Unless agreed in writing the contents of this file are subject to
 * the GNU Lesser General Public License Version 2.1 (the "LGPL" License).
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * Please visit http://openfaces.org/licensing/ for more details.
 */
package org.openfaces.component.table;

import javax.faces.component.UIComponent;
import java.util.List;

/**
 * @author Dmitry Pikhulya
 */
public class StaticTreeStructure extends TreeStructure {
    public static final String COMPONENT_TYPE = "org.openfaces.StaticTreeStructure";
    public static final String COMPONENT_FAMILY = "org.openfaces.StaticTreeStructure";

    private UIComponent currentParent;
    private List<UIComponent> currentLevelNodes;
    private int currentNodeIndex;
    private TreeNode currentNode;
    private int level;

    public StaticTreeStructure() {
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
    public void goToTopLevel() {
        setCurrentLevelByParent(this);
        setNodeIndex(0);
        level = 0;
    }

    private void setCurrentLevelByParent(UIComponent currentLevelParent) {
        currentParent = currentLevelParent;
        currentLevelNodes = currentParent.getChildren();
    }

    public void goToChildLevel() {
        setCurrentLevelByParent(currentNode);
        setNodeIndex(0);
        level++;
    }

    public void goToParentLevel() {
        if (level == 0)
            throw new IllegalArgumentException("Can't go to parent level from top-level nodes");
        currentNode = (TreeNode) currentParent;
        setCurrentLevelByParent(currentNode.getParent());
        currentNodeIndex = currentLevelNodes.indexOf(currentNode);
        level--;
    }

    public int getNodeCount() {
        return currentParent.getChildCount();
    }

    public void setNodeIndex(int nodeIndex) {
        if (currentNodeIndex < 0)
            throw new IllegalArgumentException("nodeIndex shouldn't be less than zero: " + nodeIndex);
        currentNodeIndex = nodeIndex;
        if (currentNodeIndex >= currentLevelNodes.size())
            currentNode = null;
        else {
            Object nodeObj = currentLevelNodes.get(nodeIndex);
            if (!(nodeObj instanceof TreeNode)) {
                if (currentParent == this)
                    throw new IllegalArgumentException("StaticTreeStructure can only contain TreeNode children, but found: " + nodeObj.getClass());
                else
                    throw new IllegalArgumentException("TreeNode can only contain TreeNode children, but found: " + nodeObj.getClass());
            }

            currentNode = (TreeNode) nodeObj;
        }
    }

    public int getNodeIndex() {
        return currentNodeIndex;
    }

    public boolean isNodeAvailable() {
        return currentNode != null;
    }

    public Object getNodeData() {
        if (!isNodeAvailable())
            throw new IllegalArgumentException("No node is available at the current index: " + currentNodeIndex);
        Object result = currentNode.getValue();
        return result;
    }

    public Object getNodeKey() {
        if (!isNodeAvailable())
            throw new IllegalArgumentException("No node is available at the current index: " + currentNodeIndex);
        Object result = currentNode.getNodeKey();
        return result;
    }

    public boolean getNodeHasChildren() {
        if (!isNodeAvailable())
            throw new IllegalArgumentException("No node is available at the current index: " + currentNodeIndex);
        return currentNode.getChildren().size() > 0;
    }

    public int getLevel() {
        return level;
    }


}
