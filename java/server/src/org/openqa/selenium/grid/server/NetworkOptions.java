// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.grid.server;

import io.opentracing.Tracer;
import org.openqa.selenium.grid.config.Config;
import org.openqa.selenium.grid.log.LoggingOptions;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.tracing.TracedHttpClient;

import java.util.Objects;

public class NetworkOptions {

  private final Config config;

  public NetworkOptions(Config config) {
    this.config = Objects.requireNonNull(config, "Config to use must be set.");
  }

  public HttpClient.Factory getHttpClientFactory() {
    Tracer tracer = new LoggingOptions(config).getTracer();
    return new TracedHttpClient.Factory(tracer, HttpClient.Factory.createDefault());
  }
}
