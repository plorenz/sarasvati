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

    Copyright 2009 Paul Lorenz/Vincent Kirsch
 */
package com.googlecode.sarasvati.load.definition;

import com.googlecode.sarasvati.Node;
import com.googlecode.sarasvati.load.GraphFactory;
import com.googlecode.sarasvati.load.GraphLoader;
import com.googlecode.sarasvati.load.ProcessDefinitionTranslator;

/**
 * An ExternalArcDefinition is the result of the translation of a certain external source containing
 * the definition of a process. That external source may contain necessary information to create
 * an {@link Arc} pointing to a {@link Node} defined in another definition,
 * and that information is translated into an ExternalArcDefinition through a
 * {@link ProcessDefinitionTranslator}. This ExternalArcDefinition will then in its turn be used
 * by the {@link GraphLoader} to create an {@link Arc} through the {@link GraphFactory}.
 * The default kind of external source is an XML file containing &lt;arc&gt; tags with <code>external</code> attribute.
 * This <code>external</code> attribute must reference an existing external definition within the same file.
 */
public interface ExternalArcDefinition
{

  public String getFrom ();

  public String getExternal ();

  public String getTo ();

  public String getName ();

  public boolean isToExternal ();
}