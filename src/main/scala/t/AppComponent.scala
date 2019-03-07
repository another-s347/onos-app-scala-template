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

import org.onosproject.net.topology.TopologyService
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Deactivate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.ReferenceCardinality


/**
  * Skeletal ONOS application component.
  */
@Component(immediate = true) class AppComponent {
  @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY) protected var topologyService: TopologyService = null
  final private val log = LoggerFactory.getLogger(getClass)

  @Activate def activate(): Unit = {
    log.info("scala Started")
  }

  @Deactivate def deactivate(): Unit = {
    log.info("Stopped")
  }
}