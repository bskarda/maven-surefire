package org.apache.maven.plugin.surefire.util;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.sun.istack.internal.NotNull;

import javax.annotation.Nullable;

/**
 * Relocates class names when running with relocated provider
 *
 * @author Kristian Rosenvold
 */
public class Relocator
{
    private final @Nullable String relocation;

    private static final String relocationBase = "org.apache.maven.surefire.";


    public Relocator( @Nullable String relocation )
    {
        this.relocation = relocation;
    }

    public Relocator()
    {
        relocation = "shadefire";
    }

    private @Nullable String getRelocation()
    {
        return relocation;
    }

    public @NotNull String relocate( @NotNull String className )
    {
        if ( relocation == null )
        {
            return className;
        }
        if ( className.contains( relocation ) )
        {
            return className;
        }
        String rest = className.substring( "org.apache.maven.surefire.".length() );
        final String s = relocationBase + getRelocation() + ".";
        return s + rest;
    }
}
