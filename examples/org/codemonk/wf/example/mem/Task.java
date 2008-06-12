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
package org.codemonk.wf.example.mem;

import org.codemonk.wf.NodeToken;
import org.codemonk.wf.mem.MemNode;

public class Task
{
  protected NodeToken nodeToken;

  protected TaskState state;

  public Task() { /* Default constructor for Hibernate */ }

  public Task( NodeToken nodeToken, TaskState state )
  {
    this.nodeToken = nodeToken;
    this.state = state;
  }

  public NodeToken getNodeToken()
  {
    return nodeToken;
  }

  public void setNodeToken( NodeToken nodeToken )
  {
    this.nodeToken = nodeToken;
  }

  public String getName()
  {
    return ((NodeTask)nodeToken.getNode()).getTaskName();
  }

  public String getDescription()
  {
    return ((NodeTask)nodeToken.getNode()).getTaskDesc();
  }

  public TaskState getState()
  {
    return state;
  }

  public void setState( TaskState state )
  {
    this.state = state;
  }

  public boolean isRejectable ()
  {
    MemNode node = (MemNode)getNodeToken().getNode();
    return !node.getGraph().getOutputArcs( node, "reject" ).isEmpty();
  }
}