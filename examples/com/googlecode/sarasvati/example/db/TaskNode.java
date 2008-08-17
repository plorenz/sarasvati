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
package com.googlecode.sarasvati.example.db;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.Session;

import com.googlecode.sarasvati.Engine;
import com.googlecode.sarasvati.Env;
import com.googlecode.sarasvati.NodeToken;
import com.googlecode.sarasvati.example.XmlTaskDef;
import com.googlecode.sarasvati.hib.HibEngine;
import com.googlecode.sarasvati.hib.HibNode;
import com.googlecode.sarasvati.hib.HibNodeToken;
import com.googlecode.sarasvati.load.LoadException;

@Entity
@DiscriminatorValue( "task" )
public class TaskNode extends HibNode
{
  @OneToOne (cascade = { CascadeType.REMOVE } )
  @PrimaryKeyJoinColumn
  protected NodeTaskDetail detail;

  public TaskNode() { /* Default constructor for Hibernate */ }

  public NodeTaskDetail getDetail ()
  {
    return detail;
  }

  public void setDetail (NodeTaskDetail detail)
  {
    this.detail = detail;
  }

  public String getTaskName ()
  {
    return detail.getTaskName();
  }

  public String getTaskDesc ()
  {
    return detail.getTaskDesc();
  }

  @Override
  public String getDisplayText ()
  {
    return detail.getTaskName();
  }

  @Override
  public void execute (Engine engine, NodeToken token)
  {
    HibEngine hibEngine = (HibEngine)engine;

    Session session = hibEngine.getSession();

    TaskState open = (TaskState)session.load( TaskState.class, 0 );
    Task newTask = new Task( (HibNodeToken)token, getTaskName(), getTaskDesc(), open );
    session.save( newTask );

    Env env = token.getEnv();
    env.setLongAttribute( newTask.getName(), env.getLongAttribute( newTask.getName() ) + 1 );

    env = token.getProcess().getEnv();
    env.setLongAttribute( newTask.getName(), env.getLongAttribute( newTask.getName() ) + 1 );
  }

  @Override
  public void loadCustom (Session session, Object custom)
    throws LoadException
  {
    if ( custom == null || !(custom instanceof XmlTaskDef) )
    {
      throw new LoadException( "Task node '" + getName() +
                                 "' in definition of '" + getGraph().getName() +
                                 "' contains no (or improperly specified) task-def element." );
    }

    XmlTaskDef taskDef = (XmlTaskDef)custom;

    session.save( this );

    detail = new NodeTaskDetail();
    detail.setId( getId() );
    detail.setTaskName( taskDef.getTaskName() );
    detail.setTaskDesc( taskDef.getDescription().trim() );
    session.save( detail );
  }
}