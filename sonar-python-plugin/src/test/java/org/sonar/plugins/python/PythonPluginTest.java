/*
 * SonarQube Python Plugin
 * Copyright (C) 2011-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.python;

import java.util.List;
import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.assertj.core.api.Assertions.assertThat;

public class PythonPluginTest {

  @Test
  public void testGetExtensions() {
    Version v60 = Version.create(6, 0);
    assertThat(extensions(SonarRuntimeImpl.forSonarQube(v60, SonarQubeSide.SERVER))).hasSize(19);
    assertThat(extensions(SonarRuntimeImpl.forSonarLint(v60))).hasSize(8);

    Version v72 = Version.create(7, 2);
    assertThat(extensions(SonarRuntimeImpl.forSonarQube(v72, SonarQubeSide.SERVER))).hasSize(21);
    assertThat(extensions(SonarRuntimeImpl.forSonarLint(v72))).hasSize(8);
  }

  private List extensions(SonarRuntime runtime) {
    Plugin.Context context = new Plugin.Context(runtime);
    new PythonPlugin().define(context);
    return context.getExtensions();
  }

}
