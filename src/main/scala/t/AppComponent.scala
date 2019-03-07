/*
 * Copyright 2019-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package t

import java.util

import org.onosproject.net.topology.{TopologyEvent, TopologyListener, TopologyService}
import org.osgi.service.component.annotations.Activate
//import org.osgi.service.component.annotations.Component
import org.apache.felix.scr.annotations.Component
import org.osgi.service.component.annotations.Deactivate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.ReferenceCardinality
import org.onosproject.cfg.ComponentConfigService
import org.onosproject.event.Event
import org.onosproject.net.link.LinkEvent
import org.osgi.service.component.ComponentContext

import scala.collection.JavaConverters


/**
  * Skeletal ONOS application component.
  */
@Component(immediate = true)
class AppComponent {
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    var topologyService: TopologyService = null
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY) var cfgService: ComponentConfigService = null

    final private val log = LoggerFactory.getLogger(getClass)

    @Activate def activate(context:ComponentContext): Unit = {
        cfgService.registerProperties(getClass)
        topologyService.addListener(new MyTopologyListener)
        log.info("scala Started")
    }

    @Deactivate def deactivate(): Unit = {
        log.info("Stopped")
    }
}

class MyTopologyListener extends TopologyListener {
    final private val log = LoggerFactory.getLogger(getClass)

    override def event(event: TopologyEvent): Unit = {
        val reasons = JavaConverters.asScalaBuffer(event.reasons())
        if (reasons != null) {
            reasons.foreach {
                case le: LinkEvent if le.`type`() == LinkEvent.Type.LINK_REMOVED => {
                    log.info("link removed src=={}", le.subject().src().deviceId())
                }
                case _ =>
            }
        }
    }
}