/*
 * Copyright (c) 2020 Logical Clocks AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.logicalclocks.hsfs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logicalclocks.hsfs.engine.OnDemandFeatureGroupEngine;
import com.logicalclocks.hsfs.metadata.FeatureGroupBase;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OnDemandFeatureGroup extends FeatureGroupBase {

  @Getter @Setter
  private StorageConnector storageConnector;

  @Getter @Setter
  private String query;

  @Getter @Setter
  private String type = "onDemandFeaturegroupDTO";

  private OnDemandFeatureGroupEngine onDemandFeatureGroupEngine = new OnDemandFeatureGroupEngine();

  @Builder
  public OnDemandFeatureGroup(FeatureStore featureStore, @NonNull String name, Integer version, String query,
                              @NonNull StorageConnector storageConnector, String description, List<Feature> features) {
    this.featureStore = featureStore;
    this.name = name;
    this.version = version;
    this.query = query;
    this.description = description;
    this.storageConnector = storageConnector;
    this.features = features;
  }

  public OnDemandFeatureGroup() {
  }

  public void save() throws FeatureStoreException, IOException {
    onDemandFeatureGroupEngine.saveFeatureGroup(this);
  }

  public Dataset<Row> read() throws FeatureStoreException, IOException {
    return selectAll().read();
  }

  public void show(int numRows) throws FeatureStoreException, IOException {
    read().show(numRows);
  }
}
