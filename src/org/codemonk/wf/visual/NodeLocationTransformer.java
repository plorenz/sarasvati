/*
    This file is part of Sarasvati.

    Sarasvati is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Sarasvati is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public 
    License along with Sarasvati.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2008 Paul Lorenz
*/
/**
 * Created on May 15, 2008
 */
package org.codemonk.wf.visual;

import java.awt.geom.Point2D;

import org.apache.commons.collections15.Transformer;
import org.codemonk.wf.hib.HibNodeRef;

public class NodeLocationTransformer implements Transformer<HibNodeRef, Point2D>
{
  protected GraphTree graphTree;

  public NodeLocationTransformer (GraphTree graphTree)
  {
    this.graphTree = graphTree;
  }

  @Override
  public Point2D transform (HibNodeRef nodeRef)
  {
    GraphTreeNode treeNode = graphTree.getTreeNode( nodeRef );
    Point2D point = new Point2D.Double();
    point.setLocation( treeNode.getDepth() * 100 + 50, treeNode.getIndex() * 100 + 50 );
    return point;
  }
}
