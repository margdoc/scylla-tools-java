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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cassandra.tools.nodetool;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import io.airlift.command.Arguments;
import io.airlift.command.Command;
import io.airlift.command.Option;

import org.apache.cassandra.tools.NodeProbe;
import org.apache.cassandra.tools.NodeTool.NodeToolCmd;

@Command(name = "removenode", description = "Show status of current node removal, force completion of pending removal or remove provided ID")
public class RemoveNode extends NodeToolCmd
{
    @Arguments(title = "remove_operation", usage = "<status>|<force>|<ID>", description = "Show status of current node removal, force completion of pending removal, or remove provided ID", required = true)
    private String removeOperation = EMPTY;

    @Option(title = "ignore_dead_nodes", name = {"-ignore", "--ignore-nodes", "--ignore-dead-nodes"}, description = "Use -ignore to specify a comma-separated list of dead nodes to ignore during removenode")
    private String ignoreNodes = null;

    @Override
    public void execute(NodeProbe probe)
    {
        switch (removeOperation)
        {
            case "status":
                probe.output().out.println("RemovalStatus: " + probe.getRemovalStatus());
                break;
            case "force":
                probe.output().out.println("RemovalStatus: " + probe.getRemovalStatus());
                probe.forceRemoveCompletion();
                break;
            default:
                probe.removeNode(removeOperation, ignoreNodes);
                break;
        }
    }
}
