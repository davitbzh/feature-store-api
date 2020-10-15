#
#   Copyright 2020 Logical Clocks AB
#
#   Licensed under the Apache License, Version 2.0 (the "License");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an "AS IS" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.
#

import humps

from hsfs import feature_group


class HudiFeatureGroupAlias:
    def __init__(
        self,
        hudi_feature_group,
        alias,
        left_featuregroup_starttimestamp,
        left_featuregroup_endtimestamp,
    ):
        self._hudi_feature_group = feature_group.FeatureGroup.from_response_json(
            hudi_feature_group
        )
        self._alias = alias
        self._left_featuregroup_starttimestamp = left_featuregroup_starttimestamp
        self._left_featuregroup_endtimestamp = left_featuregroup_endtimestamp

    @classmethod
    def from_response_json(cls, json_dict):
        json_decamelized = humps.decamelize(json_dict)
        return cls(**json_decamelized)

    @property
    def on_demand_feature_group(self):
        return self._on_demand_feature_group

    @property
    def alias(self):
        return self._alias

    @property
    def left_featuregroup_starttimestamp(self):
        return self._left_featuregroup_starttimestamp

    @property
    def left_featuregroup_endtimestamp(self):
        return self._left_featuregroup_endtimestamp
