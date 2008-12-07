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

package com.googlecode.sarasvati.mem;

import com.googlecode.sarasvati.CustomNode;
import com.googlecode.sarasvati.Engine;
import com.googlecode.sarasvati.GuardResponse;
import com.googlecode.sarasvati.NodeToken;
import com.googlecode.sarasvati.NodeWrapper;

public class MemCustomNode extends MemNode implements NodeWrapper
{
  protected CustomNode customNode;

  public MemCustomNode (CustomNode customNode)
  {
    this.customNode = customNode;
  }

  public CustomNode getCustomNode ()
  {
    return customNode;
  }

  @Override
  public void execute (Engine engine, NodeToken token)
  {
    customNode.execute( engine, token );
  }

  @Override
  public GuardResponse defaultGuard (Engine engine, NodeToken token)
  {
    return super.guard( engine, token );
  }

  @Override
  public <T> T getDefaultAdaptor (Class<T> clazz)
  {
    return super.getAdaptor( clazz );
  }
}