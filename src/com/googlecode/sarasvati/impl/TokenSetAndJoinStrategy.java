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
package com.googlecode.sarasvati.impl;

import java.util.List;

import com.googlecode.sarasvati.ArcToken;
import com.googlecode.sarasvati.GraphProcess;
import com.googlecode.sarasvati.JoinResult;
import com.googlecode.sarasvati.JoinStrategy;
import com.googlecode.sarasvati.JoinType;
import com.googlecode.sarasvati.Node;
import com.googlecode.sarasvati.TokenSet;

/**
 * Implements a join strategy in which nodes will wait for arc tokens to be
 * present on all incoming arcs before completing the join. If the incoming
 * arc token does not belong to a token set, the join strategy will behave
 * like the {@link OrJoinStrategy}.
 *
 * @author Paul Lorenz
 */
public class TokenSetAndJoinStrategy implements JoinStrategy
{
  public TokenSet getTokenSet (ArcToken token)
  {
    List<TokenSet> tokenSets = token.getTokenSets();
    return tokenSets.isEmpty() ? null : tokenSets.get( 0 );
  }

  @Override
  public JoinResult performJoin (GraphProcess process, ArcToken token)
  {
    TokenSet tokenSet = getTokenSet( token );

    if ( tokenSet == null )
    {
      return JoinType.OR.getJoinStrategy().performJoin( process, token );
    }

    if ( !tokenSet.getActiveNodeTokens().isEmpty() )
    {
      return JoinResult.INCOMPLETE_JOIN_RESULT;
    }

    Node targetNode = token.getArc().getEndNode();
    List<ArcToken> incompleteTokens = tokenSet.getActiveArcTokens();

    for ( ArcToken arcToken : incompleteTokens )
    {
      if ( !arcToken.getArc().getEndNode().equals( targetNode ) )
      {
        return JoinResult.INCOMPLETE_JOIN_RESULT;
      }
    }

    return new CompleteJoinResult( incompleteTokens );
  }
}